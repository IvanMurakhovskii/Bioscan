package com.murik.enose.model.A_Second;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultSecond;

public class ResultA_Second1_2 extends BaseResultSecond {


    public ResultA_Second1_2(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("II");
    }

    public void setResult() {
        if (getA() >= 0.57 && getA() <= 0.67) {
            setColorGREEN();
        } else if (getA() >= 0.68 && getA() <= 0.72) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_YELLOW_1));
        } else if (getA() >= 0.45 && getA() <= 0.55) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_YELLOW_2));
        } else if (getA() >= 0.56 && getA() <= 0.58) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_RED_1));
        }
    }
}
