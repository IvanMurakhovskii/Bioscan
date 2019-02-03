package com.murik.enose.presentation.view.liveBluetoothChart;

import com.arellomobile.mvp.MvpView;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.List;

public interface LiveBluetoothChartView extends MvpView {
  void setLineChart(List<ILineDataSet> dataSets);
  void notifyLineChart1();
  void notifyLineChart2();
  void notifyLineChart3();
  void notifyLineChart4();
  void notifyLineChart5();
  void notifyLineChart6();
  void notifyLineChart7();
  void notifyLineChart8();
}
