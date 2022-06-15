package com.murik.enose.db;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Date;
import java.util.List;

import static com.murik.enose.db.DBHelper.*;

public class ExportToCsv {
    public void export(List<List<String>> data, String[] headers){
        try{
//            Date now = new Date();
//            Date from = new Date(now.getTime());
//            Log.d("export", "time " + now.getTime());
//            Log.d("export", "date " + DateFormat.format("yyyy.MM.dd HH:mm:ss", from));

            String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
            String date = (String) DateFormat.format("yyyy.MM.dd HH:mm:ss", new Date());
            String fileName = date + "_MeasureData" + ".csv";
            String dir = baseDir + File.separator + "ENose Export";
            File exportDir = new File(dir);
            if(!exportDir.exists()){
                exportDir.mkdir();
            }
            String filePath = dir + File.separator + fileName;
            Log.d("export", "filePath = " + filePath);
            File f = new File(filePath);
            CSVWriter writer;
            FileWriter mFileWriter;

            // File exist
            if(f.exists()&&!f.isDirectory())
            {
                mFileWriter = new FileWriter(filePath, true);
                writer = new CSVWriter(mFileWriter);
            }
            else
            {
                writer = new CSVWriter(new FileWriter(filePath));
            }

            writer.writeNext(headers);

            for(List<String> row : data) {
                writer.writeNext(row.toArray(new String[0]));
            }

            writer.close();

            Log.d("export", "done!");
        } catch (Exception ex) {
            Log.e("export", ex.getMessage(), ex);
        }
    }
}
