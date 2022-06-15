package com.murik.enose.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

import com.murik.enose.db.model.Measure;
import com.murik.enose.db.model.MeasureForCsv;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "enose_db";

    public static final String MEASURE_SESSION_TABLE = "measure_session";
    public static final String MEASURE_TABLE = "measure";

    public static final String DEVICE_TYPE = "device_type"; //0 - one sensor scanner, 1 - dual sensors
    public static final String GENDER_COLUMN = "gender";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String IS_PRACTICE_COLUMN = "is_practice";
    public static final String MEASURE_TIME_COLUMN = "measure_time";

    public static final String ID_COLUMN = "id";
    public static final String CREATED_WHEN_COLUMN = "created_when";
    public static final String SESSION_ID_COLUMN = "session_id";
    public static final String SENSOR_INDEX_COLUMN = "sensor_index";
    public static final String HAND_COLUMN = "hand";
    public static final String SENSOR_VALUE_COLUMN = "sensor_value";
    public static final String SENSOR_DIFF_VALUE_COLUMN = "sensor_diff_value";
    public static final String TIME_FROM_START_OF_MEASURE_COLUMN = "time_from_start_of_measure";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + MEASURE_SESSION_TABLE + "(" + ID_COLUMN
                + " integer primary key autoincrement, "
                + CREATED_WHEN_COLUMN + " bigint, "
                + GENDER_COLUMN + " integer,"
                + DESCRIPTION_COLUMN + " text,"
                + IS_PRACTICE_COLUMN + " integer default 0,"
                + MEASURE_TIME_COLUMN + " integer default 0,"
                + DEVICE_TYPE + " integer default -1)"
        );

        db.execSQL("create table if not exists " + MEASURE_TABLE + "(" +
                ID_COLUMN + " integer primary key autoincrement, " +
                CREATED_WHEN_COLUMN + " bigint," +
                SESSION_ID_COLUMN + " integer, " +
                SENSOR_INDEX_COLUMN + " integer, " +
                HAND_COLUMN + " integer, " +
                SENSOR_VALUE_COLUMN + " bigint, " +
                SENSOR_DIFF_VALUE_COLUMN + " bigint," +
                TIME_FROM_START_OF_MEASURE_COLUMN + " bigint" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void dropTables(){
        SQLiteDatabase db = getWritableDatabase();

        List<String> tables = Arrays.asList(MEASURE_TABLE, MEASURE_SESSION_TABLE);

        for (String table : tables) {
            String dropQuery = "DROP TABLE IF EXISTS " + table;
            db.execSQL(dropQuery);
        }
    }

    public int createNewMeasureSession(int gender, int isPractice, int measureTime, String description,
                                       int deviceType){
        SQLiteDatabase db = getWritableDatabase();

        if(StringUtils.isBlank(description)){
            description = "не заполнен";
        }

        db.execSQL("insert into " + MEASURE_SESSION_TABLE + " (" + CREATED_WHEN_COLUMN +
                "," + GENDER_COLUMN + "," + IS_PRACTICE_COLUMN + "," + MEASURE_TIME_COLUMN + "," +
                DESCRIPTION_COLUMN + "," + DEVICE_TYPE +
                ") values (" + new Date().getTime() + "," + gender + "," + isPractice + "," +
                measureTime + ", \"" + description + "\"," + deviceType + ")");

        Cursor c = db.rawQuery("select max(id) from " + MEASURE_SESSION_TABLE, null);
        c.moveToNext();
        return c.getInt(0);
    }

    public void saveMeasures(List<Measure> measures){

        for (int i = 0; i < measures.size(); i++) {
            String query = "insert into " + MEASURE_TABLE +
                    "(" + CREATED_WHEN_COLUMN + "," + SESSION_ID_COLUMN + "," + SENSOR_INDEX_COLUMN +
                    "," + HAND_COLUMN + "," + SENSOR_VALUE_COLUMN + "," + SENSOR_DIFF_VALUE_COLUMN +
                    "," + TIME_FROM_START_OF_MEASURE_COLUMN +
                    ")\n values\n" + measures.get(i).getSqlValueForInsertion();
            getWritableDatabase().execSQL(query);
        }
    }

    public int getMeasuresCount(){

        Cursor c = getReadableDatabase().rawQuery("select count(1) from " + MEASURE_TABLE, null);
        c.moveToNext();
        return c.getInt(0);
    }

    public String[] getExportHeaders(){
//       return new String[]{ ID_COLUMN, CREATED_WHEN_COLUMN, SESSION_ID_COLUMN,
//                    SENSOR_INDEX_COLUMN, HAND_COLUMN, SENSOR_VALUE_COLUMN, SENSOR_DIFF_VALUE_COLUMN,
//                    GENDER_COLUMN, IS_PRACTICE_COLUMN, MEASURE_TIME_COLUMN, DESCRIPTION_COLUMN  }
        return new String[]{ ID_COLUMN, SESSION_ID_COLUMN, DEVICE_TYPE,
                MEASURE_TIME_COLUMN, DESCRIPTION_COLUMN, TIME_FROM_START_OF_MEASURE_COLUMN,
                SENSOR_DIFF_VALUE_COLUMN, SENSOR_VALUE_COLUMN, TIME_FROM_START_OF_MEASURE_COLUMN,
                SENSOR_DIFF_VALUE_COLUMN, SENSOR_VALUE_COLUMN  };
    }


    public List<List<String>> getAll(){

        List<List<String>> data = new ArrayList<>();

        Cursor c = getReadableDatabase().rawQuery("select m." + ID_COLUMN + ", ms." + ID_COLUMN +
                ", ms." + DEVICE_TYPE + ", ms." + MEASURE_TIME_COLUMN + ", ms." + DESCRIPTION_COLUMN +
                ", m." + SENSOR_INDEX_COLUMN + ", m." + TIME_FROM_START_OF_MEASURE_COLUMN +
                ", m." + SENSOR_DIFF_VALUE_COLUMN + ", m." + SENSOR_VALUE_COLUMN +
                " from " + MEASURE_TABLE + " m, " +
                        MEASURE_SESSION_TABLE + " ms " +
                " where m." + SESSION_ID_COLUMN + " = ms." + ID_COLUMN +
                " order by m." + SESSION_ID_COLUMN + ", m." + CREATED_WHEN_COLUMN, null);

        Log.d("getAll", "count = " + c.getCount());

        List<MeasureForCsv> allMeasures = new ArrayList<>();

        while(c.moveToNext()){
            MeasureForCsv measure = new MeasureForCsv(c.getInt(0), c.getInt(1),
                    c.getInt(2), c.getInt(3), c.getString(4), c.getInt(5), c.getInt(6),
                    c.getLong(7), c.getLong(8));
            allMeasures.add(measure);
        }

        boolean isFirst = true;
        for(int i = 0; i < allMeasures.size(); i++){
            if (isFirst){
                data.add(allMeasures.get(i).getRowForCsv());
                isFirst = false;
            } else if (allMeasures.get(i).getSensorIndex() == allMeasures.get(i - 1).getSensorIndex()) {
                data.add(allMeasures.get(i).getRowForCsv());
            } else {
                data.get(data.size() - 1).add(allMeasures.get(i).getTimeFromStartOfMeasure() + "");
                data.get(data.size() - 1).add(allMeasures.get(i).getDiffValue() + "");
                data.get(data.size() - 1).add(allMeasures.get(i).getValue() + "");
                isFirst = true;
            }
        }

        Log.d("getAll", "real count = " + allMeasures.size());

        return data;
    }

    private String getFormattedDate(long timestamp){
        return (String) DateFormat.format("dd.MM.yyyy HH:mm:ss", new Date(timestamp));
    }
}
