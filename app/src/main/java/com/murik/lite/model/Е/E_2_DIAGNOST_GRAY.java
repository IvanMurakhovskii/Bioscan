package com.murik.lite.model.Е;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

import lombok.val;

public class E_2_DIAGNOST_GRAY extends BaseResult {


    public E_2_DIAGNOST_GRAY(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("E2_GRAY");
    }

    @Override
    public void setResult() {
        val a = getA();

        if (a >= 0.16 && a <= 0.21) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_GREY_1));
        } else if (a >= 0.22 && a <= 0.29) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_GREY_2));
        } else if (a >= 0.32 && a <= 0.39) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_GREY_3));
        } else if (a >= 0.40 && a <= 0.44) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_GREY_4));
        } else if (a >= 0.45 && a <= 0.53) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_GREY_5));
        }
    }
}
