package com.murik.enose.model;

import android.content.Context;

import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.AreaDifference;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

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
