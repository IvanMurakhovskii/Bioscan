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

  public static final String DATA = "result";
  public static final String PAGE = "page";


  public static final String TAG = "ResultRadarChartFragment";
  @InjectPresenter
  ResultRadarChartPresenter mResultRadarChartPresenter;

  private int mPage = 2;

  private RadarChart radarChart;
  private Button btnResult;

  public static Fragment newInstance(int mPage, SensorDataFullParcelable sensorDataFullParcelable) {
    ResultRadarChartFragment fragment = new ResultRadarChartFragment();

    Bundle args = new Bundle();
    args.putInt(PAGE, mPage);
    args.putParcelable(ResultRadarChartFragment.DATA, sensorDataFullParcelable);
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {
      Bundle bundle = getArguments();
      if(bundle != null){
        //sensorDataFullParcelable =  bundle.getParcelable(DATA);
        mPage = bundle.getInt(PAGE);
        mResultRadarChartPresenter.setData(bundle.getParcelable(DATA));
      }
    return inflater.inflate(R.layout.fragment_result_radar_chart, container, false);
  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    radarChart = view.findViewById(R.id.radarChart);
    btnResult = view.findViewById(R.id.btnResult);
    mResultRadarChartPresenter.createRadarChart(mPage);
  }

  public void initRadarChart(ArrayList<RadarEntry> entryLeftHand,  ArrayList<RadarEntry> entryRightHand,
      String description, int color){
    List<IRadarDataSet> DATA_SET = new ArrayList<>();

    //int defaultSquare = SquareChart(entries);
    //int sensSquare = SquareChart(entries1);

    //difference = (1 - ((float)defaultSquare/(float)sensSquare))*100;

    //tvInfo.setText(String.valueOf(difference));
    //setProgressBar

    ArrayList<String> lable = new ArrayList<>();

   lable.add("15");
   lable.add("30");
   lable.add("45");

   if(!entryLeftHand.isEmpty()){
     RadarDataSet dataSet_leftHand = new RadarDataSet(entryLeftHand, "left hand");
      dataSet_leftHand.setDrawFilled(true);
      dataSet_leftHand.setFillColor(color);
      dataSet_leftHand.setColor(Color.BLACK);
      DATA_SET.add(dataSet_leftHand);
    }
    if(!entryRightHand.isEmpty()){
      RadarDataSet dataSet_rightHand = new RadarDataSet(entryRightHand, "right hand");
      dataSet_rightHand.setDrawFilled(true);
      dataSet_rightHand.setFillColor(color);
      dataSet_rightHand.setColor(Color.BLUE);
      DATA_SET.add(dataSet_rightHand);
    }
    Description des = radarChart.getDescription();
    des.setText(description);
    RadarData radarData = new RadarData(DATA_SET);
    radarData.setLabels(lable);
    radarChart.setData(radarData);

    XAxis x = radarChart.getXAxis();
    YAxis y = radarChart.getYAxis();



    //int count = (entryLeftHand.size()/entryLeftHand.size()) - 1 ;

    /*int tmp = 0;
    for(int i = 0; i < maskArr.length; i++){
      lable.set(tmp, String.valueOf(maskArr[i]));
      tmp+=count + 1;
    }*/

    int count = 0;
    if(mPage == Const.PAGE_TOTAL){
      count = (int) x.getAxisMaximum()/Const.TOTAL.length - 1;
    } else if(mPage == Const.PAGE_HEALTH){
      count = (int) x.getAxisMaximum()/Const.HEALTH.length- 1;
    } else if(mPage == Const.PAGE_BAD){
      count = (int) x.getAxisMaximum()/Const.BAD.length- 1;
    } else if(mPage == Const.PAGE_ENERGY){
      count = (int) x.getAxisMaximum()/Const.ENERGY.length- 1;
    } else if(mPage == Const.PAGE_ENDOKRIN){
      count = (int) x.getAxisMaximum()/Const.ENDOKRIN.length- 1;
    }

    x.setLabelCount(count);
    x.setGranularityEnabled(true);
    y.setDrawTopYLabelEntry(false);
    radarData.setDrawValues(false);
    radarChart.setSkipWebLineCount(count);
    radarChart.invalidate();
  }
}
