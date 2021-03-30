package com.murik.enose.model.resultbyMaxValue;

import android.content.Context;

import com.murik.enose.Const;
import com.murik.enose.dto.DataByMaxParcelable;

public abstract class BaseResultWithCoefficient extends BaseResult{

    private float coefficient;

    public BaseResultWithCoefficient(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context);
        this.coefficient = coefficient;
    }

    @Override
    public double getA() {
//        if (getInputData().getSensorType() == Const.BIOSCANER) {
//            return super.getA() * coefficient;
//        }
        return super.getA();
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }
}
