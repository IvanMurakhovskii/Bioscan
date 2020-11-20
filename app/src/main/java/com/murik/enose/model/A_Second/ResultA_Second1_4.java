package com.murik.enose.model.A_Second;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultSecond;

public class ResultA_Second1_4 extends BaseResultSecond {


    public ResultA_Second1_4(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("X");
    }

    public void setResult() {
        if (getA() >= 0.25 && getA() <= 0.48) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG_1_4_RED));
        } else if (getA() >= 0.49 && getA() <= 0.55) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_4_YELLOW));
        } else if (getA() >= 0.84 && getA() <= 1.1) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_4_YELLOW_2));
        } else if (getA() <= 0.25) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_LONG_1_4_BURGUNDY));
        }
    }
}
