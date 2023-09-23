package com.murik.lite.model;

import android.content.Context;

import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class R1_2 extends BaseResult {

    public R1_2(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("R1_2");
    }

    @Override
    public void setResult() {
        setColorGRAY();
    }
}
