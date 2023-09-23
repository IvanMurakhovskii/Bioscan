package com.murik.lite.presentation.view.summary;

import com.arellomobile.mvp.MvpView;

public interface SummaryView extends MvpView {
    void setProgressLeft(Double progress, int color);
    void setProgressRight(Double progress, int color);
    void setDescriptionLeft(String description, int color);
    void setDescriptionRight(String description, int color);
    void setResultImageLeft(int imageResId);
    void setResultImageRight(int imageResId);
}
