package com.murik.enose.presentation.realm;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.Screens;
import com.murik.enose.model.dto.InputDataParcelable;

@InjectViewState
public class RealmPresenter extends MvpPresenter<RealmView> {
  public void onItemRecyclerClick(InputDataParcelable dataSens){

    App.INSTANCE.getRouter().navigateTo(Screens.RESULT_FRAGMENT, dataSens);
  }
}
