 package com.murik.enose.presentation.presenter.parserXml;


 import android.view.View;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.Screens;
import com.murik.enose.model.RealmController;
import com.murik.enose.model.dto.SensorDataFullParcelable;
import com.murik.enose.presentation.view.parserXML.ParserXmlView;
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

 @InjectViewState
public class ParserXmlPresenter extends MvpPresenter<ParserXmlView> {

   public static final String NAME_TAG = "name";
   public static final String SENSOR_TAG = "sensor";
   public static final String POINT_TAG = "point";
   public static final String LOG_TAG = "MyLogs";



   private SensorDataFullParcelable sensorDataFullParcelable = new SensorDataFullParcelable();
   private ArrayList<File> arrayList = new ArrayList<>();

   private File fileLeftHand = null;
   private File fileRightHand = null;

   @Override
   protected void onFirstViewAttach() {
     super.onFirstViewAttach();
   }

   @Override
   public void attachView(ParserXmlView view) {
     super.attachView(view);
     }


   public int getItemRecyclerCount(){
    return arrayList.size();
   }


    public void onSaveButtonClick(String descriptions, int gender, boolean isPractice) {
     Map<String, ArrayList<Integer>> mapLeftHand = new HashMap<>();
     Map<String, ArrayList<Integer>> mapRightHand = new HashMap<>();
     sensorDataFullParcelable.setDescriptions(descriptions);
     sensorDataFullParcelable.setGender(gender);
     sensorDataFullParcelable.setPractice(isPractice);

      try {
        if(getFileLeftHand() != null){
          mapLeftHand = passeXML(getFileLeftHand());
        }
        if(getFileRightHand() != null){
          mapRightHand = passeXML(getFileRightHand());
        }
      } catch (XmlPullParserException e) {
        e.printStackTrace();
      }


      if(mapLeftHand != null){
          sensorDataFullParcelable.setDataSensorMapLeftHand(mapLeftHand);
        }
      if(mapRightHand != null){
          sensorDataFullParcelable.setDataSensorMapRightHand(mapRightHand);
        }
        RealmController controller = new RealmController();
        controller.addInfoFull(sensorDataFullParcelable);
        App.INSTANCE.getRouter().replaceScreen(Screens.REALM_FRAGMENT);

    }

   public void setFileLeftHand(File fileLeftHand) {
     this.fileLeftHand = fileLeftHand;
     getViewState().setVisibilitySaveButton(View.VISIBLE);

   }

   public void setFileRightHand(File fileRightHand) {
     this.fileRightHand = fileRightHand;
     getViewState().setVisibilitySaveButton(View.VISIBLE);
   }

   public File getFileLeftHand() {
     return fileLeftHand;
   }

   public File getFileRightHand() {
     return fileRightHand;
   }

   public  Map<String, ArrayList<Integer>> passeXML(File file) throws XmlPullParserException {

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

     }
     parser.setInput(fis, null);

     while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {

       if (parser.getEventType() == XmlPullParser.START_TAG) {
         if (parser.getName().equals(NAME_TAG)) {
           flag = true;
         } else if (parser.getName().equals(SENSOR_TAG)) {
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
               key  = parser.getAttributeValue(i);
             }
           }
         } else if (parser.getName().equals(POINT_TAG)) {
           for (int i = 0; i < parser.getAttributeCount(); i++) {
             if (parser.getAttributeName(i).equals("value")) {
               tmp = parser.getAttributeValue(i)
                   .substring(0, parser.getAttributeValue(i).length() - 3);
               sensor.add(initial - Integer.parseInt(tmp));
             }
           }
         }
       }
       if(parser.getEventType() == XmlPullParser.TEXT){
         if(flag){

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

     return sensprMap;
   }

}
