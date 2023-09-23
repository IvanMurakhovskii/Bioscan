package com.murik.lite.presentation.view.dimension;

import com.arellomobile.mvp.MvpView;

public interface BluetoothDimensionView extends MvpView {
    void stopChartRender();
    void showContinueDialog();
    void setProgressBarColor(int color);
}
