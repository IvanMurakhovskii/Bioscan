package com.murik.lite.model.resultbyMaxValue;

import android.content.Context;

import com.murik.lite.dto.DataByMaxParcelable;

public abstract class BaseResultFirst extends BaseResultWithCoefficient{

    public BaseResultFirst(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
    }
}
