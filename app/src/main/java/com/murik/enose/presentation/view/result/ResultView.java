package com.murik.enose.presentation.view.result;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.murik.enose.model.ResultBySens;
import java.util.ArrayList;

public interface ResultView extends MvpView {
  void initPieChart(ArrayList<ResultBySens> sensResult);
  void initRecyclerView();
  @StateStrategyType(value = SkipStrategy.class)
  void showDialog();
  void calculateResult();
}
