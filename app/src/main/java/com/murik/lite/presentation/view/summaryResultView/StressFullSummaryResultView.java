package com.murik.lite.presentation.view.summaryResultView;

import com.arellomobile.mvp.MvpView;

public interface StressFullSummaryResultView extends MvpView {
    void setProgressRun(Double progress, int color);
    void setDescriptionRun(String description, int color);
    void setResultImageRun(int imageResId);

    void setProgressStop(Double progress, int color);
    void setDescriptionStop(String description, int color);
    void setResultImageStop(int imageResId);
}
