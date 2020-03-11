package com.murik.enose.presentation.presenter.input;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.Screens;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.presentation.view.input.InputView;

@InjectViewState
public class InputPresenter extends MvpPresenter<InputView> {
  public void onSubmitButtonClick(DataByMaxParcelable input){
    App.INSTANCE.getRouter().navigateTo(Screens.RESULT_FRAGMENT, input);
  }
}
