package com.murik.enose.presentation.presenter.parserXml;


import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.Screens;
import com.murik.enose.model.RealmController;
import com.murik.enose.dto.SensorDataFullParcelable;
import com.murik.enose.presentation.view.parserXML.ParserXmlView;
import com.murik.enose.utils.ListUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

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




        if (mapLeftHand != null) {
            sensorDataFullParcelable.setDataSensorMapLeftHand(mapLeftHand);
        }
        if (mapRightHand != null) {
            sensorDataFullParcelable.setDataSensorMapRightHand(mapRightHand);
        }
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
        FileInputStream fis = null;
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
                if (parser.getName().equals(NAME_TAG)) {
                    flag = true;
                } else if (parser.getName().equals(SENSOR_TAG)) {
                    if (!sensor.isEmpty()) {
                        sensorMap.put(key, sensor);
                        sensor = new ArrayList<>();

                    }
                    for (int i = 0; i < parser.getAttributeCount(); i++) {
                        if (parser.getAttributeName(i).equals("initial")) {
                            String str = parser.getAttributeValue(i);
                            str = (str.indexOf(',') == -1) ? str : str.substring(0, str.indexOf(','));

                            initial = Integer.parseInt(str);
                        } else if (parser.getAttributeName(i).equals("sid")) {

                            key = Const.allSens.get(count);
                            count++;
                        }
                    }
                } else if (parser.getName().equals(POINT_TAG)) {
                    for (int i = 0; i < parser.getAttributeCount(); i++) {
                        if (parser.getAttributeName(i).equals("value")) {
                            tmp = parser.getAttributeValue(i);
                            tmp = (tmp.indexOf(',') == -1) ? tmp : tmp.substring(0, tmp.indexOf(','));

                            if (initial == -999) {
                                sensor.add(Integer.parseInt(tmp));
                            } else {
                                sensor.add(initial - Integer.parseInt(tmp));
                            }
                        }
                    }
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

}
