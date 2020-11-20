package com.murik.enose.model.resultbyMaxValue;

import android.content.Context;

import com.murik.enose.dto.DataByMaxParcelable;

public abstract class BaseResultSecond extends BaseResultWithCoefficient{


    public BaseResultSecond(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
    }

   /* @Override
    public String getLegend() {
        return super.getLegend() + "д";
    }*/
}
