package com.murik.enose.model.A_Second;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultSecond;

public class ResultA_Second2_3 extends BaseResultSecond {


    public ResultA_Second2_3(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("III");
    }

    public void setResult() {
        if ( getA() >= 0.73 && getA() <= 0.77) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_2_3_YELLOW));
        } else if (getA() >=0.78 && getA() <= 0.85) {
            setColorRED();
        }
    }
}
