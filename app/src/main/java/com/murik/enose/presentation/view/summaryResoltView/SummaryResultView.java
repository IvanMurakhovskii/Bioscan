package com.murik.enose.presentation.view.summaryResoltView;

import com.arellomobile.mvp.MvpView;

public interface SummaryResultView extends MvpView {
    void setProgress(Double progress, int color);
    void setDescription(String description, int color);
}
