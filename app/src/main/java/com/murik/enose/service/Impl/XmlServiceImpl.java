package com.murik.enose.service.Impl;

import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.Xml;

import com.murik.enose.dto.SensorDataFullParcelable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import lombok.val;

public class XmlServiceImpl {

    public static final String NAME_TAG = "name";
    public static final String SENSOR_TAG = "sensor";
    public static final String POINT_TAG = "point";
    public static final String MEASURE_TAG = "measure";
    public static final String START_TAG = "start";
    public static final String LOG_TAG = "MyLogs";

    public static void createXMLWithMeasurement(SensorDataFullParcelable measurement, final long time) {
        val description = measurement.getDescriptions();

        val dataSensorMapLeftHand = measurement.getDataSensorMapLeftHand();
        val dataSensorMapRightHand = measurement.getDataSensorMapRightHand();

        if (dataSensorMapLeftHand != null) {
            createMeasurementFile(dataSensorMapLeftHand, description, "_левая", time);
        }

        if (dataSensorMapRightHand != null) {
            createMeasurementFile(dataSensorMapRightHand, description, "_правая", time);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createMeasurementFile(Map<String, ArrayList<Integer>> values, final String description, final String bodyType, long time) {

        val fileName = new StringBuilder().append(description).append(bodyType).append(".xml");

        File directory = new File(
                Environment.getExternalStorageDirectory().toString() + File.separator + "Enose/измерения");
        directory.mkdirs();
        String mPath = Environment.getExternalStorageDirectory().toString() + "/Enose/измерения/" + fileName;

        val resultXML = new File(mPath);

        val date = new Date(time);
        val dateInFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(date);

        try {
            val fos = new FileOutputStream(resultXML);

            val serializer = Xml.newSerializer();

            serializer.setOutput(fos, "UTF-8");


            serializer.startDocument(null, Boolean.TRUE);
            serializer.startTag(null, MEASURE_TAG);
            serializer.startTag(null, NAME_TAG);
            serializer.text(description);
            serializer.endTag(null, NAME_TAG);
            serializer.startTag(null, START_TAG);
            serializer.text(dateInFormat);
            serializer.endTag(null, START_TAG);
            for (val entry : values.entrySet()) {

                val sensorId = entry.getKey();

                serializer.startTag(null, SENSOR_TAG);
                serializer.attribute(null, "sid", sensorId);
                serializer.attribute(null, "initial", "-999");

                for (val value : entry.getValue()) {
                    serializer.startTag(null, POINT_TAG);
                    serializer.attribute(null, "value", value.toString());
                    serializer.endTag(null, POINT_TAG);
                }

                serializer.endTag(null, SENSOR_TAG);
            }

            serializer.endTag(null, MEASURE_TAG);
            serializer.endDocument();
            serializer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
