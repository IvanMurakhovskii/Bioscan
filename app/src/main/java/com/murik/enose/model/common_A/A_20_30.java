package com.murik.enose.model.common_A;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultWithCoefficient;

import lombok.var;

public class A_20_30 extends BaseResultWithCoefficient {


    public A_20_30(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("V");
    }

    public A_20_30(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.64 && getA() <= 0.75) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_30_YELLOW));
        } else if(getA() >= 0.76 && getA() <= 0.95) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_30_RED));
        } else if(getA() >= 0.95) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_20_30_BURGUNDY));
        } else if(getA() >= 0.27 && getA() <= 0.32) {
            setColorBLUE();
        }
    }
}
