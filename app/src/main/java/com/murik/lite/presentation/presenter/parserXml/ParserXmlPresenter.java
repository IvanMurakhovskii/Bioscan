package com.murik.lite.presentation.presenter.parserXml;


import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.App;
import com.murik.lite.Const;
import com.murik.lite.Screens;
import com.murik.lite.dto.SensorDataFullParcelable;
import com.murik.lite.model.RealmController;
import com.murik.lite.presentation.view.parserXML.ParserXmlView;
import com.murik.lite.utils.ListUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@InjectViewState
public class ParserXmlPresenter extends MvpPresenter<ParserXmlView> {

    public static final String NAME_TAG = "name";
    public static final String SENSOR_TAG = "sensor";
    public static final String POINT_TAG = "point";
    public static final String LOG_TAG = "MyLogs";


    private SensorDataFullParcelable sensorDataFullParcelable = new SensorDataFullParcelable();
    private ArrayList<File> arrayList = new ArrayList<>();

    private Uri fileLeftHand = null;
    private Uri fileRightHand = null;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void attachView(ParserXmlView view) {
        super.attachView(view);
    }


    public int getItemRecyclerCount() {
        return arrayList.size();
    }


    public void onSaveButtonClick(String descriptions, int gender, boolean isPractice, Context context) {
        Map<String, ArrayList<Integer>> mapLeftHand = new HashMap<>();
        Map<String, ArrayList<Integer>> mapRightHand = new HashMap<>();
        sensorDataFullParcelable.setDescriptions(descriptions);
        sensorDataFullParcelable.setGender(gender);
        sensorDataFullParcelable.setPractice(isPractice);

        try {
            if (getFileLeftHand() != null) {
                mapLeftHand = passeXML(getFileLeftHand(), context);

                for (Map.Entry<String, ArrayList<Integer>> entry : mapLeftHand.entrySet()) {
                    ListUtils.inverseListValueIfMiddleValueBelowZero(entry.getValue());
                }
            }
            if (getFileRightHand() != null) {
                mapRightHand = passeXML(getFileRightHand(), context);

                for (Map.Entry<String, ArrayList<Integer>> entry : mapRightHand.entrySet()) {
                    ListUtils.inverseListValueIfMiddleValueBelowZero(entry.getValue());
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        sensorDataFullParcelable.setDataSensorMapLeftHand(mapLeftHand);
        sensorDataFullParcelable.setDataSensorMapRightHand(mapRightHand);
        RealmController controller = new RealmController();
        controller.addInfoFull(sensorDataFullParcelable);
        App.INSTANCE.getRouter().replaceScreen(Screens.REALM_FRAGMENT);

    }

    public void setFileLeftHand(Uri fileLeftHand) {
        this.fileLeftHand = fileLeftHand;
        getViewState().setVisibilitySaveButton(View.VISIBLE);

    }

    public void setFileRightHand(Uri fileRightHand) {
        this.fileRightHand = fileRightHand;
        getViewState().setVisibilitySaveButton(View.VISIBLE);
    }

    public Uri getFileLeftHand() {
        return fileLeftHand;
    }

    public Uri getFileRightHand() {
        return fileRightHand;
    }

    public Map<String, ArrayList<Integer>> passeXML(Uri file, Context context) throws XmlPullParserException {

        String key = "";
        String descriptions = "";
        int initial = 0;
        Map<String, ArrayList<Integer>> sensorMap = new HashMap<>();
        ArrayList<Integer> sensor = new ArrayList<>();

        boolean flag = false;
        String tmp;

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        parser.setInput(is, null);
        int count = 0;
        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {

            if (parser.getEventType() == XmlPullParser.START_TAG) {
                switch (parser.getName()) {
                    case NAME_TAG:
                        flag = true;
                        break;
                    case SENSOR_TAG:
                        if (!sensor.isEmpty()) {
                            sensorMap.put(key, sensor);
                            sensor = new ArrayList<>();

                        }
                        for (int i = 0; i < parser.getAttributeCount(); i++) {
                            if (parser.getAttributeName(i).equals("initial")) {
                                initial = parseNumber(parser.getAttributeValue(i));

                            } else if (parser.getAttributeName(i).equals("sid")) {

                                key = Const.allSens.get(count);
                                count++;
                            }
                        }
                        break;
                    case POINT_TAG:
                        for (int i = 0; i < parser.getAttributeCount(); i++) {
                            if (parser.getAttributeName(i).equals("value")) {
                                Integer value = parseNumber(parser.getAttributeValue(i));

                                if (initial == -999) {
                                    sensor.add(value);
                                } else {
                                    sensor.add(initial - value);
                                }
                            }
                        }
                        break;
                }
            }
            if (parser.getEventType() == XmlPullParser.TEXT) {
                if (flag) {
                    flag = false;
                }
            }

            try {
                parser.next();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sensorMap.put(key, sensor);

        return sensorMap;
    }

    private Integer parseNumber(String value) {
        if (value.indexOf(',') != -1) {
            return Integer.parseInt(value.substring(0, value.indexOf(',')));
        }

        if (value.indexOf('.') != -1) {
           return Integer.parseInt(value.substring(0, value.indexOf('.')));
        }

        return Integer.parseInt(value);
    }

}
