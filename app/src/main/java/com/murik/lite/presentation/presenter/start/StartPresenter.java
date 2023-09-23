package com.murik.lite.presentation.presenter.start;


import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.App;
import com.murik.lite.Screens;
import com.murik.lite.presentation.view.start.StartView;


public class StartPresenter extends MvpPresenter<StartView> {
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.INSTANCE.getRouter().navigateTo(Screens.REALM_FRAGMENT);
    }

}
