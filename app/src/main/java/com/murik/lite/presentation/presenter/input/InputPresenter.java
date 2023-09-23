package com.murik.lite.presentation.presenter.input;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.App;
import com.murik.lite.Screens;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.presentation.view.input.InputView;

@InjectViewState
public class InputPresenter extends MvpPresenter<InputView> {
  public void onSubmitButtonClick(DataByMaxParcelable input){
    App.INSTANCE.getRouter().navigateTo(Screens.RESULT_FRAGMENT, input);
  }
}
