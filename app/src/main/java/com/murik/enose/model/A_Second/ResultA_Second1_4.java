package com.murik.enose.model.A_Second;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_Second1_4 extends BaseResult {


    public ResultA_Second1_4(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("1_4");
    }

    public void setResult() {
        setColorYELLOW();
        setPossibleReasons("result");
        if (getA() >= 0.25 && getA() <= 0.55 && getInputData().isPractice()) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG_1_4_RED));
        } else if (getA() >= 0.56 && getA() <= 0.79) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_4_YELLOW));
        } else if(getA() >= 0.8 && getA() <= 0.9) {
            setColorGREEN();
            setPossibleReasons("Норма");
        }
    }
}
