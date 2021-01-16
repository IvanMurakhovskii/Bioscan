package com.murik.enose.model.A_30;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class A_15_30_GRAY extends BaseResultFirst {


    public A_15_30_GRAY(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("II");
    }

    public void setResult() {
        setColorGRAY();
        if (getA() >= 0.11 && getA() <= 0.24) {
        } else if (getA() >= 0.45 && getA() <= 0.47) {
            setPossibleReasons(getResources(R.string.A_15_30_GRAY_1));
        } else if (getA() >= 0.26 && getA() <= 0.38) {
            setPossibleReasons(getResources(R.string.A_15_30_GRAY_2));
        } else if (getA() >= 0.40 && getA() <= 0.50) {
            setPossibleReasons(getResources(R.string.A_15_30_GRAY_3));
        } else if (getA() >= 0.30 && getA() <= 0.38) {
            setPossibleReasons(getResources(R.string.A_15_30_GRAY_4));
        } else if (getA() >= 0.39 && getA() <= 0.44) {
            setPossibleReasons(getResources(R.string.A_15_30_GRAY_5));
        } else if (getA() >= 0.45 && getA() <= 0.59) {
            setPossibleReasons(getResources(R.string.A_15_30_GRAY_6));
        } else if (getA() >= 0.60 && getA() <= 0.70) {
            setPossibleReasons(getResources(R.string.A_15_30_GRAY_7));
        } else if (getA() >= 0.83 && getA() <= 0.88) {
            setPossibleReasons(getResources(R.string.A_15_30_GRAY_8));
        }
    }
}
