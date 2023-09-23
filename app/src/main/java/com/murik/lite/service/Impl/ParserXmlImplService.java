package com.murik.lite.service.Impl;

import com.murik.lite.Const;
import com.murik.lite.service.ParserXmlService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ParserXmlImplService implements ParserXmlService {

  public static final String NAME_TAG = "name";
  public static final String SENSOR_TAG = "sensor";
  public static final String POINT_TAG = "point";
  public static final String LOG_TAG = "MyLogs";
  public static Map<String, ArrayList<Integer>> passeXML(File file) throws XmlPullParserException {

    String key = "";
    String descriptions = "";
    int initial = 0;
    Map<String, ArrayList<Integer>> sensprMap = new HashMap<>();
    ArrayList<Integer> sensor = new ArrayList<>();

    boolean flag = false;
    String tmp;

    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    XmlPullParser parser = factory.newPullParser();
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      //Log.d(LOG_TAG, e.getMessage().toString());
    }
    parser.setInput(fis, null);
    int count = 0;

    while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {

      if (parser.getEventType() == XmlPullParser.START_TAG) {
        switch (parser.getName()) {
          case NAME_TAG:
            flag = true;
            break;
          case SENSOR_TAG:
            if (!sensor.isEmpty()) {
              sensprMap.put(key, sensor);
              sensor = new ArrayList<>();

            }
            for (int i = 0; i < parser.getAttributeCount(); i++) {
              if (parser.getAttributeName(i).equals("initial")) {
                String str = parser.getAttributeValue(i)
                        .substring(0, parser.getAttributeValue(i).length() - 3);
                initial = Integer.parseInt(str);
              } else if (parser.getAttributeName(i).equals("sid")) {

                //key  = parser.getAttributeValue(i);
                key = Const.allSens.get(count);
                count++;
              }
            }
            break;
          case POINT_TAG:
            for (int i = 0; i < parser.getAttributeCount(); i++) {
              if (parser.getAttributeName(i).equals("value")) {
                tmp = parser.getAttributeValue(i)
                        .substring(0, parser.getAttributeValue(i).length() - 3);
                sensor.add(initial - Integer.parseInt(tmp));
              }
            }
            break;
        }
      }
      if(parser.getEventType() == XmlPullParser.TEXT){
        if(flag){
          descriptions = parser.getText();
          flag = false;
        }
      }

      try {
        parser.next();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    sensprMap.put(key, sensor);

    //sensorDataFullParcelable.setDescriptions(descriptions);

    return sensprMap;
  }

}
