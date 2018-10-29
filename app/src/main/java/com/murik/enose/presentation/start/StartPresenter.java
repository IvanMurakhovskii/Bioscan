package com.murik.enose.presentation.start;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.Screens;

@InjectViewState
public class StartPresenter extends MvpPresenter<StartView> {
  @Override
  protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    App.INSTANCE.getRouter().navigateTo(Screens.REALM_FRAGMENT);
  }


}
