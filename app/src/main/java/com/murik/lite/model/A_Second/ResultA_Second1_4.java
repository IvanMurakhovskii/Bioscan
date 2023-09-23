package com.murik.lite.model.A_Second;

import android.content.Context;

import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultSecond;

public class ResultA_Second1_4 extends BaseResultSecond {


    public ResultA_Second1_4(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("X");
    }

    public void setResult() {
    }
}
