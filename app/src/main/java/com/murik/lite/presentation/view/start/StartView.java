package com.murik.lite.presentation.view.start;

import com.arellomobile.mvp.MvpView;

public interface StartView extends MvpView {
    void setBleConnectionStateColor(int color);
    void visibleAdminMenuItems(boolean visible);
}
