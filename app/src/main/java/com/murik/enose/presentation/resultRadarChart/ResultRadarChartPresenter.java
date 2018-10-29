package com.murik.enose.presentation.resultRadarChart;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ResultRadarChartPresenter extends MvpPresenter<ResultRadarChartView> {

  @Override
  public void attachView(ResultRadarChartView view) {
    super.attachView(view);
    //getViewState().createRadarChart();
  }
}
