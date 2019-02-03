package com.murik.enose.presentation.view.dimension;

import com.anychart.chart.common.dataentry.DataEntry;
import com.arellomobile.mvp.MvpView;
import java.util.List;

public interface BluetoothDimensionView extends MvpView {
 void initLineChart(List<DataEntry> seriesData);
 void notifyLineChart();
}
