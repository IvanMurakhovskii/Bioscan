package com.murik.lite.model.resultbyMaxValue;

import android.content.Context;

import com.murik.lite.dto.DataByMaxParcelable;

public abstract class BaseResultWithCoefficient extends BaseResult {

    private float coefficient;

    public BaseResultWithCoefficient(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context);
        this.coefficient = coefficient;
    }

    @Override
    public double getA() {
        return super.getA();
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }
}
