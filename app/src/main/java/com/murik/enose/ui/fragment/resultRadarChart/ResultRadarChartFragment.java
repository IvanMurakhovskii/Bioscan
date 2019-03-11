package com.murik.enose.ui.fragment.resultRadarChart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
import com.murik.enose.presentation.presenter.resultRadarChart.ResultRadarChartPresenter;
import com.murik.enose.presentation.view.resultRadarChart.ResultRadarChartView;
import java.util.ArrayList;
import java.util.List;

public class ResultRadarChartFragment extends MvpAppCompatFragment implements ResultRadarChartView {

  public static final String DATA = "result";
  public static final String PAGE = "page";


  public static final String TAG = "ResultRadarChartFragment";
  @InjectPresenter
  ResultRadarChartPresenter mResultRadarChartPresenter;

  private int mPage = 2;
  final private float testChartSize = 18;
  private RadarChart radarChart;
  private TextView tvRadarAreaLeft;
  private TextView tvRadarAreaRight;
  private TextView tvDeltaLeft;
  private TextView tvDeltaRight;
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
        mPage = bundle.getInt(PAGE);
        mResultRadarChartPresenter.setData(bundle.getParcelable(DATA));
      }
    return inflater.inflate(R.layout.fragment_result_radar_chart, container, false);
  }


  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    tvRadarAreaLeft = view.findViewById(R.id.tv_radar_area_left);
    tvRadarAreaRight = view.findViewById(R.id.tv_radar_area_right);
    tvDeltaLeft = view.findViewById(R.id.tv_radar_delta_left);
    tvDeltaRight = view.findViewById(R.id.tv_radar_delta_right);
    radarChart = view.findViewById(R.id.radarChart);
    mResultRadarChartPresenter.createRadarChart(mPage);
  }

  public void initRadarChart(ArrayList<RadarEntry> entryLeftHand,  ArrayList<RadarEntry> entryRightHand,
      String description, int colorLeft, int colorRight){
    List<IRadarDataSet> DATA_SET = new ArrayList<>();

   if(!entryLeftHand.isEmpty()){
     RadarDataSet dataSet_leftHand = new RadarDataSet(entryLeftHand, "Левая");
      dataSet_leftHand.setDrawFilled(true);
      dataSet_leftHand.setFillColor(colorLeft);
      dataSet_leftHand.setColor(colorLeft);
     dataSet_leftHand.setHighlightCircleFillColor(Color.YELLOW);
      dataSet_leftHand.setValueTextSize(40);
      DATA_SET.add(dataSet_leftHand);
    }
    if(!entryRightHand.isEmpty()){
      RadarDataSet dataSet_rightHand = new RadarDataSet(entryRightHand,"Правая");
      dataSet_rightHand.setDrawFilled(true);
      dataSet_rightHand.setFillColor(colorRight);
      dataSet_rightHand.setColor(colorRight);
      dataSet_rightHand.setValueTextSize(40);
      DATA_SET.add(dataSet_rightHand);
    }
    Description des = radarChart.getDescription();
    des.setText("");
    RadarData radarData;
    if(!DATA_SET.isEmpty()){
      radarData = new RadarData(DATA_SET);
      radarChart.setData(radarData);
      radarData.setDrawValues(false);
    }

    radarChart.setRotationX(0);
    XAxis x = radarChart.getXAxis();
    x.setTextSize(testChartSize);
    YAxis y = radarChart.getYAxis();
    y.setTextSize(testChartSize);

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

    x.setEnabled(false);
    y.setGranularity(count);
    y.setAxisMinimum(0f);
    y.setDrawTopYLabelEntry(false);

    radarChart.setTouchEnabled(false);
    radarChart.setSkipWebLineCount(count);
    radarChart.invalidate();

  }
  @Override
  public void setTvRadarAreaLeft(String areaLeft) {
    this.tvRadarAreaLeft.setText(areaLeft);
  }

  @Override
  public void setTvRadarAreaRight(String areaRight) {
    this.tvRadarAreaRight.setText(areaRight);
  }

  @Override
  public void setTvDeltaLeft(String deltaLeft) {
    this.tvDeltaLeft.setText(deltaLeft);
  }

  @Override
  public void setTvDeltaRight(String deltaRight) {
    this.tvDeltaRight.setText(deltaRight);
  }
}
