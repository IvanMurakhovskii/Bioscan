package com.murik.lite.presentation.view.oneSensorMeasure;

import com.arellomobile.mvp.MvpView;
import com.github.mikephil.charting.data.RadarEntry;
import com.murik.lite.model.resultbyMaxValue.AreaDifference;

import java.util.ArrayList;

public interface OneSensorMeasureView extends MvpView {
    void initRadarChart(ArrayList<RadarEntry> leftHandData, ArrayList<RadarEntry> rightHandData, String description, int colorLeft, int colorRight);

    void setLeftArea(double area);

    void setRightArea(double area);

    void setLeftDelta(double delta);

    void setRightDelta(double delta);

    void setAreaDifference(AreaDifference difference);

    void setLeftPs_3425(double ps);

    void setLeftPs_2435(double ps);

    void setRightPs_3425(double ps);

    void setRightPs_2435(double ps);

    void setDeltaDangerOnLungsLeft(double delta);

    void setDeltaDangerOnLungsRight(double delta);

    void setMaxRight(Integer delta);

    void setMaxLeft(Integer delta);
}
