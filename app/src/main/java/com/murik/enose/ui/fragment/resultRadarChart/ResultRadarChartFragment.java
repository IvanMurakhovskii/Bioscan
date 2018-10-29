package com.murik.enose.ui.fragment.resultRadarChart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.dto.SensorDataFullParcelable;
import com.murik.enose.presentation.resultRadarChart.ResultRadarChartPresenter;
import com.murik.enose.presentation.resultRadarChart.ResultRadarChartView;
import java.util.ArrayList;
import java.util.List;

public class ResultRadarChartFragment extends MvpAppCompatFragment implements ResultRadarChartView {

  private final int[] TOTAL = {20, 30, 60, 100, 120, 170};
  private final int[] HEALTH = {150, 160, 170, 170};
  private final int[] ENERGY = {115, 120, 130, 140, 150};
  private final int[] BAD = {20, 30, 40, 80, 100};

  public static final int PAGE_TOTAL = 0;
  public static final int PAGE_HEALTH = 1;
  public static final int PAGE_ENERGY = 2;
  public static final int PAGE_BAD = 3;

  public static final String DATA = "result";
  public static final String PAGE = "page";


  public static final String TAG = "ResultRadarChartFragment";
  @InjectPresenter
  ResultRadarChartPresenter mResultRadarChartPresenter;

  private SensorDataFullParcelable sensorDataFullParcelable;
  private int mPage = 2;

  private RadarChart radarChart;
  private Button btnResult;

  public static Fragment newInstance(SensorDataFullParcelable resultBySens, int mPage) {
    ResultRadarChartFragment fragment = new ResultRadarChartFragment();

    Bundle args = new Bundle();
    args.putParcelable(DATA, resultBySens);
    args.putInt(PAGE, mPage);
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {
      Bundle bundle = getArguments();
      if(bundle != null){
        sensorDataFullParcelable =  bundle.getParcelable(DATA);
        mPage = bundle.getInt(PAGE);
      }

    return inflater.inflate(R.layout.fragment_result_radar_chart, container, false);
  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    radarChart = view.findViewById(R.id.radarChart);
    btnResult = view.findViewById(R.id.btnResult);
    createRadarChart();
  }

  public void initRadarChart(int[] maskArr, int color){

    ArrayList<Integer> leftHandData = new ArrayList<>();
    ArrayList<Integer> rightHandData = new ArrayList<>();
    List<IRadarDataSet> DATA_SET = new ArrayList<>();

    for(int key: maskArr){

      sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_1).get(key);
      sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_2).get(key);
      sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3).get(key);
      sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4).get(key);
      sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5).get(key);
      sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_6).get(key);
      sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7).get(key);
      sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_8).get(key);

      if(sensorDataFullParcelable.getDataSensorMapLeftHand() != null){
        leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_1).get(key));
        leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_2).get(key));
        leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3).get(key));
        leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4).get(key));
        leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5).get(key));
        leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_6).get(key));
        leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7).get(key));
        leftHandData.add(sensorDataFullParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_8).get(key));
      }
      if(sensorDataFullParcelable.getDataSensorMapRightHand() != null){
        if(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_1) != null){
          rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_1).get(key));
          rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_2).get(key));
          rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3).get(key));
          rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4).get(key));
          rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5).get(key));
          rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_6).get(key));
          rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7).get(key));
          rightHandData.add(sensorDataFullParcelable.getDataSensorMapRightHand().get(Const.SENSOR_8).get(key));
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

    //int defaultSquare = SquareChart(entries);
    //int sensSquare = SquareChart(entries1);

    //difference = (1 - ((float)defaultSquare/(float)sensSquare))*100;

    //tvInfo.setText(String.valueOf(difference));
    //setProgressBar

    ArrayList<RadarEntry> entryLeftHand = new ArrayList<>();
    ArrayList<RadarEntry> entryRightHand = new ArrayList<>();

    ArrayList<String> lable = new ArrayList<>();

    for(int i = 0; i < leftHandData.size(); i++){
        entryLeftHand.add(new RadarEntry(leftHandData.get(i), i));
        lable.add(String.valueOf(i));
    }
    for(int i = 0; i < entryRightHand.size(); i++){
        entryRightHand.add(new RadarEntry(rightHandData.get(i), i));
    }
    RadarDataSet dataSet_leftHand = new RadarDataSet(entryLeftHand, "left hand");
    if(!entryLeftHand.isEmpty()){

      dataSet_leftHand.setDrawFilled(true);
      dataSet_leftHand.setFillColor(color);
      dataSet_leftHand.setColor(Color.BLACK);
      DATA_SET.add(dataSet_leftHand);
    }


    if(!entryRightHand.isEmpty()){
      RadarDataSet dataSet_rightHand = new RadarDataSet(entryRightHand, "left hand");
      dataSet_rightHand.setDrawFilled(false);
      dataSet_rightHand.setColor(Color.GREEN);
      DATA_SET.add(dataSet_rightHand);
    }




    Description des = radarChart.getDescription();
    des.setText(sensorDataFullParcelable.getDescriptions());
    RadarData radarData = new RadarData(DATA_SET);
    radarData.setLabels(lable);
    radarChart.setData(radarData);


    XAxis x = radarChart.getXAxis();
    YAxis y = radarChart.getYAxis();

    int count = (entryLeftHand.size()/maskArr.length) - 1 ;

    int tmp = 0;
    for(int i = 0; i < maskArr.length; i++){
      lable.set(tmp, String.valueOf(maskArr[i]));
      tmp+=count + 1;
    }


    y.setDrawTopYLabelEntry(false);
    radarData.setDrawValues(false);


    radarChart.invalidate();


  }

  @Override
  public void createRadarChart() {
    switch (mPage){
      case PAGE_TOTAL:
        initRadarChart(TOTAL, Color.RED);
        break;

      case PAGE_HEALTH:
        initRadarChart(HEALTH, Color.GREEN);
        break;

      case PAGE_ENERGY:
        initRadarChart(ENERGY, Color.BLUE);
        break;

      case PAGE_BAD:
        initRadarChart(BAD, Color.MAGENTA);
        break;
    }
  }
}
