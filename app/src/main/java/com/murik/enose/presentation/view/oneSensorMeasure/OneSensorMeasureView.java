package com.murik.enose.presentation.view.oneSensorMeasure;

import com.arellomobile.mvp.MvpView;
import com.github.mikephil.charting.data.RadarEntry;

import java.util.ArrayList;

public interface OneSensorMeasureView extends MvpView {
    void initRadarChart(ArrayList<RadarEntry> leftHandData, ArrayList<RadarEntry> rightHandData, String description, int colorLeft, int colorRight);

    void setLeftArea(float area);

    void setRightArea(float area);

    void setLeftDelta(float delta);

    void setRightDelta(float delta);

    void setAreaDifference(float difference);

    void setLeftPs_3425(float ps);

    void setLeftPs_2435(float ps);

    void setRightPs_3425(float ps);

    void setRightPs_2435(float ps);

    void setDeltaDangerOnLungsLeft(float delta);

    void setDeltaDangerOnLungsRight(float delta);
}
