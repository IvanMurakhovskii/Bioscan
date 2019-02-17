package com.murik.enose.presentation.view.resultRadarChart;

import com.arellomobile.mvp.MvpView;
import com.github.mikephil.charting.data.RadarEntry;
import java.util.ArrayList;

public interface ResultRadarChartView extends MvpView {
    void initRadarChart(ArrayList<RadarEntry> leftHandData, ArrayList<RadarEntry> rightHandData,String description, int color);
    void setTvRadarAreaLeft(String areaLeft);
    void setTvRadarAreaRight(String areaRight);
}
