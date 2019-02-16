package com.murik.enose.presentation.presenter.resultRadarChart;


import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.RadarEntry;
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.dto.SensorDataFullParcelable;
import com.murik.enose.presentation.view.resultRadarChart.ResultRadarChartView;
import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class ResultRadarChartPresenter extends MvpPresenter<ResultRadarChartView>{


  public final static String TAG = "resultRadarPresenter";
  private SensorDataFullParcelable sensorDataFullParcelable;

  /*private final int[] TOTAL = {30, 45, 60, 80, 100, 120, 180};
  private final int[] HEALTH = {150, 160, 170};
  //todo energy sensor 1, 3, 7
  private final int[] ENERGY = {110, 120, 130, 140, 150};
  private final int[] BAD = {20, 30, 180};
  //todo sens 3, 4, 5, 7
  *//*todo k = f4(60)/f4(20) - расчитать
    если k >= 2.4 - Внимание кетоны*//*
  private final int[] ENDOKRIN = {10, 20, 30, 60};*/

  private List<Integer> x = new ArrayList<>();


  @Override
  public void attachView(ResultRadarChartView view) {
    super.attachView(view);
  }

  public void setData(SensorDataFullParcelable sensorDataFullParcelable){
    this.sensorDataFullParcelable = sensorDataFullParcelable;
  }

  public SensorDataFullParcelable getSensorDataFullParcelable() {
    return sensorDataFullParcelable;
  }


  public void createRadarChart(int mPage) {
    switch (mPage){
      case Const.PAGE_TOTAL:
        initRadarChart(Const.TOTAL, Color.RED);
        break;
      case Const.PAGE_HEALTH:
        initRadarChart(Const.HEALTH, ContextCompat.getColor(App.INSTANCE.getApplicationContext(), R.color.green));
        break;
      case Const.PAGE_ENERGY:
        initRadarChart(Const.ENERGY, Color.BLUE);
        break;
      case Const.PAGE_BAD:
        initRadarChart(Const.BAD, Color.LTGRAY);
        break;
      case Const.PAGE_ENDOKRIN:
        initRadarChart(Const.ENDOKRIN, Color.MAGENTA);
        break;
    }
  }
  public void initRadarChart(int[] mask, int color){


    ArrayList<Integer> leftHandData = new ArrayList<>();
    ArrayList<Integer> rightHandData = new ArrayList<>();


    if(mask.equals(Const.ENERGY)) {
      for (int key : mask) {

        if (!sensorDataFullParcelable.getDataSensorMapLeftHand().isEmpty()) {
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_1) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_1).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7).get((key)));
            x.add(key);
          }
        }
        if (!sensorDataFullParcelable.getDataSensorMapRightHand().isEmpty()) {
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_1) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_1).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7).get((key)));
            x.add(key);
          }
        }
      }
    } else if(mask.equals(Const.ENDOKRIN)) {
      for(int key: mask) {

        if (!sensorDataFullParcelable.getDataSensorMapLeftHand().isEmpty()) {

          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7).get((key)));
            x.add(key);
          }
        }
        if (!sensorDataFullParcelable.getDataSensorMapRightHand().isEmpty()) {
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7).get((key)));
            x.add(key);
          }
        }
      }
    } else {
      for(int key: mask) {

        if (!sensorDataFullParcelable.getDataSensorMapLeftHand().isEmpty()) {
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_1) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_1).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_2) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_2).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_6) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_6).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_8) != null){
            leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_8).get((key)));
            x.add(key);
          }
        }
        if (!sensorDataFullParcelable.getDataSensorMapRightHand().isEmpty()) {
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_1) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_1).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_2) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_2).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_6) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_6).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7).get((key)));
            x.add(key);
          }
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_8) != null){
            rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_8).get((key)));
            x.add(key);
          }
      }
    }
    }


    for(int i = 0; i < leftHandData.size(); i++){
      if(leftHandData.get(i) < 0){
        leftHandData.set(i, 0);
      }
    }
    for(int i = 0; i < rightHandData.size(); i++){
      if(rightHandData.get(i) < 0){
        rightHandData.set(i, 0);
      }
    }

    ArrayList<RadarEntry> entryLeftHand = new ArrayList<>();
    ArrayList<RadarEntry> entryRightHand = new ArrayList<>();

    for(int i = 0; i < leftHandData.size(); i++){
      entryLeftHand.add(new RadarEntry(leftHandData.get(i), i));
      //lable.add(String.valueOf(i));
    }
    for(int i = 0; i < rightHandData.size(); i++){
      entryRightHand.add(new RadarEntry(rightHandData.get(i), i));
    }
    float arealefr = calculateArea(leftHandData);
    float areaRight = calculateArea(rightHandData);

    getViewState().initRadarChart(entryLeftHand, entryRightHand,getSensorDataFullParcelable().getDescriptions(), color);
  }

  //todo откорректировать расчет площади
  public float calculateArea(ArrayList<Integer> data){
    if(data == null){
      return 0;
    }
    float area = 0f;
    float dx = 10f;
    for(int i = 0; i < data.size() - 1; i++){
      dx = x.get(i+1) - x.get(i);
      int y1 = data.get(i);
      int y2 = data.get(i+1);
      if(y1*y2 > 0){
        area += Math.abs((y1 + y2) / 2f * dx);
      } else {
        float dx1 = (1f + Math.abs(y2) * dx);
        area += Math.abs( (dx1 * Math.abs(y1) + (dx - dx1) * Math.abs(y2)) / 2f);
        }
    }
    return area;
  }

}