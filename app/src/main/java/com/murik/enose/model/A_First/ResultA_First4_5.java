package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_First4_5 extends BaseResult {


    public ResultA_First4_5(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("4_5");
    }

    public void setResult() {
        setColorYELLOW();
        setPossibleReasons("result");
    }
}
