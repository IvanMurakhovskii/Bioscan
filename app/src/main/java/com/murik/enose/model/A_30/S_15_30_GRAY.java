package com.murik.enose.model.A_30;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class S_15_30_GRAY extends BaseResultFirst {


    public S_15_30_GRAY(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("S15_30_G");
    }

    public void setResult() {
        setColorGRAY();
        if (getA() >= 0.050 && getA() <= 0.114) {
            setPossibleReasons(getResources(R.string.S_15_30_GRAY_1));
        } else if (getA() >= 0.14 && getA() <= 0.18) {
            setPossibleReasons(getResources(R.string.S_15_30_GRAY_2));
        } else if (getA() >= 0.37 && getA() <= 0.41) {
            setPossibleReasons(getResources(R.string.S_15_30_GRAY_3));
        }
    }
}
