package com.murik.lite.presentation.view.result_simple;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.murik.lite.model.ResultBySens;

import java.util.ArrayList;

public interface ResultSimpleView extends MvpView {
  void initPieChartLeft(ArrayList<ResultBySens> sensResult);
  void initPieChartRight(ArrayList<ResultBySens> sensResult);
  void initRecyclerView();
  @StateStrategyType(value = SkipStrategy.class)
  void showDialog();
  void calculateResult();
  void showSummaryButton();
}
