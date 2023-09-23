package com.murik.lite.presentation.view.resultRadarChart;

import com.arellomobile.mvp.MvpView;
import com.github.mikephil.charting.data.RadarEntry;

import java.util.ArrayList;

public interface ResultRadarChartView extends MvpView {
    void initRadarChart(ArrayList<RadarEntry> leftHandData, ArrayList<RadarEntry> rightHandData, String description, int colorLeft, int colorRight);

    void setTvRadarAreaLeft(String areaLeft);

    void setTvRadarAreaRight(String areaRight);

    void setTvDeltaLeft(String deltaLeft);

    void setTvDeltaRight(String deltaRight);

    void setTvDeltaLeft180(String deltaLeft180);

    void setTvDeltaRight180(String deltaRight180);

    void setTvDifference(String difference);
}
