package com.murik.lite.presentation.presenter.additionalResult;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.dto.AdditionalParcelable;
import com.murik.lite.model.additional.Additional_Pgct;
import com.murik.lite.model.additional.Additional_Ps;
import com.murik.lite.model.additional.Additional_Sp;
import com.murik.lite.model.additional.Additional_Y;
import com.murik.lite.presentation.view.additionalResult.AdditionalResultView;

import static com.murik.lite.utils.MathUtils.divide;

@InjectViewState
public class AdditionalResultPresenter extends MvpPresenter<AdditionalResultView> {

    private final String TAG = "BluetoothConnectionPresenter";

    public void calculateAdditional(AdditionalParcelable additionalData, Context context) {
        Double ps = divide(additionalData.getHeart(), additionalData.getForeArmLeft());
        Double pgct = divide(additionalData.getLiver(), additionalData.getForeArmRight());;
        Double sp = divide(additionalData.getHeart(), additionalData.getLiver());;
        Double y = divide(ps, pgct);;

        Additional_Ps additional_ps = new Additional_Ps(ps, context);
        Additional_Pgct additional_pgct = new Additional_Pgct(pgct, context);
        Additional_Sp additional_sp = new Additional_Sp(sp, context);
        Additional_Y additional_y = new Additional_Y(y, context);

        getViewState().setPs(additional_ps);
        getViewState().setPgct(additional_pgct);
        getViewState().setSp(additional_sp);
        getViewState().setY(additional_y);
    }

}
