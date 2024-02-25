package com.murik.lite.presentation.view.substances;

import com.arellomobile.mvp.MvpView;
import com.github.mikephil.charting.data.RadarEntry;
import com.murik.lite.model.substances.Substance;

import java.util.ArrayList;
import java.util.List;

public interface SubstancesView extends MvpView {
    void initRadarChart(List<Substance> substances);
}
