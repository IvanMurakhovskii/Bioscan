package com.murik.lite.presentation.view.liveBluetoothChart;

import com.arellomobile.mvp.MvpView;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.List;

public interface LiveBluetoothChartView extends MvpView {
  void setLineChart(List<ILineDataSet> dataSets);
  void notifyLineChart1();
}
