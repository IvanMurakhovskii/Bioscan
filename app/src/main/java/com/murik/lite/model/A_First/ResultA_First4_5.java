package com.murik.lite.model.A_First;

import android.content.Context;

import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First4_5 extends BaseResultFirst {


    public ResultA_First4_5(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("4_5");
    }

    public void setResult() {
        setColorYELLOW();
        setPossibleReasons("result");
    }
}
