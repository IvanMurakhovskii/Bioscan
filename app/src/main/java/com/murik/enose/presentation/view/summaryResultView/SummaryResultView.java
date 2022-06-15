package com.murik.enose.presentation.view.summaryResultView;

import com.arellomobile.mvp.MvpView;

public interface SummaryResultView extends MvpView {
    void setProgress(Double progress, int color);
    void setDescription(String description, int color);
}
