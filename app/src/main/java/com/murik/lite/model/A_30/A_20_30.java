package com.murik.lite.model.A_30;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class A_20_30 extends BaseResultFirst {


    public A_20_30(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("III");
    }

    public void setResult() {
        if (getA() >= 0.58 && getA() <= 0.65) {
            setColorGREEN();
        } else if (getA() >= 0.64 && getA() <= 0.69) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A30_20_30_YELLOW));
        } else if (getA() >= 0.70 && getA() <= 0.75) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A30_20_30_YELLOW_1));
        } else if (getA() >= 0.50 && getA() <= 0.57) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A30_20_30_YELLOW_2));
        } else if (getA() >= 0.27 && getA() <= 0.49) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A30_20_30_RED));
        } else if (getA() >= 0.76 && getA() <= 0.95) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A30_20_30_RED_1));
        } else if (getA() > 0.95) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A30_20_30_BURGUNDY));
        }
    }
}
