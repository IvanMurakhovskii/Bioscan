 package com.murik.enose.presentation.parserXml;


 import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.model.RealmController;
import com.murik.enose.model.dto.SensorDataFullParcelable;
import com.murik.enose.ui.fragment.parserXml.recycler.ParserXmlViewHolder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

 @InjectViewState
public class ParserXmlPresenter extends MvpPresenter<ParserXmlView> {

   public static final String NAME_TAG = "name";
   public static final String SENSOR_TAG = "sensor";
   public static final String POINT_TAG = "point";
   public static final String LOG_TAG = "MyLogs";

   SensorDataFullParcelable sensorDataFullParcelable = new SensorDataFullParcelable();
   private ArrayList<File> arrayList = null;
   private String descriptions = "";
   private String key = "";
   private int initial = 0;
   private File file = null;

   @Override
   public void attachView(ParserXmlView view) {
     super.attachView(view);
     arrayList = getListFiles(Environment.getExternalStorageDirectory());
     getViewState().initRecyclerView();
   }

   public void OnBindViewHolder(@NonNull ParserXmlViewHolder parserXmlViewHolder, int i){
    parserXmlViewHolder.setTvFileName(arrayList.get(i));
  }

   public int getItemRecyclerCount(){
    return arrayList.size();
   }

   public void onItemRecyclerClickListener(@NonNull ParserXmlViewHolder parserXmlViewHolder){
     file = parserXmlViewHolder.getTvFile();
     getViewState().setVisibilitySaveButton(View.VISIBLE);

   }

    public void onSaveButtonClick(int gender, boolean isLeftHand, boolean isPractice) {

     Map<String, ArrayList<Integer>> map = new HashMap<>();
     sensorDataFullParcelable.setGender(gender);
     sensorDataFullParcelable.setPractice(isPractice);

      try {
        map = passeXML(file);
      } catch (XmlPullParserException e) {
        e.printStackTrace();
      }


      if(map != null){
        if(isLeftHand){
          sensorDataFullParcelable.setDataSensorMapLeftHand(map);
        } else {
          sensorDataFullParcelable.setDataSensorMapRightHand(map);
        }
        RealmController controller = new RealmController();
        controller.addInfoFull(sensorDataFullParcelable);
      } else {
        //todo error loading data
      }
    }

   public ArrayList<File> getListFiles(File parentDir) {

     ArrayList<File> inFiles = new ArrayList<>();
     Queue<File> files = new LinkedList<>();
     if(parentDir.listFiles() != null) {
       files.addAll(Arrays.asList(parentDir.listFiles()));
     } else {
//       App.INSTANCE.getRouter().showSystemMessage("Подходящие файлы не обнаружены");
     }

     while (!files.isEmpty()) {
       File file = files.remove();
       if (file.isDirectory()) {
         files.addAll(Arrays.asList(file.listFiles()));
       } else if (file.getName().endsWith(".XML")) {
         inFiles.add(file);
       }
     }
  //todo
     /*DisposableObserver<Queue<File>> file1 = Observable.fromArray(files)
         .observeOn(Schedulers.io())
         .subscribeWith(new DisposableObserver<Queue<File>>() {
           @Override
           public void onNext(Queue<File> files) {
             while (!files.isEmpty()) {
               File file = files.remove();
               if (file.isDirectory()) {
                 files.addAll(Arrays.asList(file.listFiles()));
               } else if (file.getName().endsWith(".XML")) {
                 inFiles.add(file);
                 //Log.d("Tag", "OnNext: " + file.getName());
               }
             }
           }

           @Override
           public void onError(Throwable e) {
             Log.d("Tag", e.getMessage());
           }

           @Override
           public void onComplete() {

           }
         });

             /*(new Observer<Queue<File>>() {
           @Override
           public void onSubscribe(Disposable d) {

           }

           @Override
           public void onNext(Queue<File> files) {

             while (!files.isEmpty()) {
               File file = files.remove();
               if (file.isDirectory()) {
                 files.addAll(Arrays.asList(file.listFiles()));
               } else if (file.getName().endsWith(".XML")) {
                 inFiles.add(file);
                 Log.d("Tag", "OnNext: " + file.getName());
               }
             }
           }
           @Override
           public void onError(Throwable e) {
             Log.d("Tag", e.getMessage());
           }

           @Override
           public void onComplete() {
             Log.d("Tag", "complite");
           }
         });*/

     return inFiles;
   }

   public  Map<String, ArrayList<Integer>> passeXML(File file) throws XmlPullParserException {

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
       Log.d(LOG_TAG, e.getMessage().toString());
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

     sensorDataFullParcelable.setDescriptions(descriptions);

     return sensprMap;
   }
}
