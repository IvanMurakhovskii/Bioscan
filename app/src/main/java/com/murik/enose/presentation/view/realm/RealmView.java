package com.murik.enose.presentation.view.realm;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface RealmView extends MvpView {
    @StateStrategyType(value = SkipStrategy.class)
    void showDialog();
}
