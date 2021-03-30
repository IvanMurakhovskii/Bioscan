package com.murik.enose.model.A_30;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class A_20_30_GRAY extends BaseResultFirst {


    public A_20_30_GRAY(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("IV_G");
    }

    public void setResult() {
        setColorGRAY();
        if (getA() >= 0.18 && getA() <= 0.22) {
            setPossibleReasons(getResources(R.string.A_20_30_GRAY_1));
        } else if (getA() >= 0.68 && getA() <= 0.72) {
            setPossibleReasons(getResources(R.string.A_20_30_GRAY_2));
        } else if (getA() >= 0.27 && getA() <= 0.32) {
            setPossibleReasons(getResources(R.string.A_20_30_GRAY_3));
        } else if (getA() >= 0.42 && getA() <= 0.44) {
            setPossibleReasons(getResources(R.string.A_20_30_GRAY_4));
        } else if (getA() >= 0.37 && getA() <= 0.41) {
            setPossibleReasons(getResources(R.string.A_20_30_GRAY_5));
        }
    }
}
