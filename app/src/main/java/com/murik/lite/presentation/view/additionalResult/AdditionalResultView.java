package com.murik.lite.presentation.view.additionalResult;

import com.arellomobile.mvp.MvpView;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public interface AdditionalResultView extends MvpView {
    void setPs(BaseResult result);
    void setPgct(BaseResult result);
    void setSp(BaseResult result);
    void setY(BaseResult result);
}