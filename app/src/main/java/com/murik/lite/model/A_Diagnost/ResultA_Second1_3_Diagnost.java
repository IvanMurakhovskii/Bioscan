package com.murik.lite.model.A_Diagnost;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultSecond;

public class ResultA_Second1_3_Diagnost extends BaseResultSecond {


    public ResultA_Second1_3_Diagnost(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VI");
    }

    public void setResult() {
        if (getA() >= 0.49 && getA() <= 0.58) {
            setColorYELLOW();
        } else if (getA() >= 0.40 && getA() <= 0.48) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_3_YELLOW_1_DIAGNOST));
        } else if (getA() >= 0.49 && getA() <= 0.55) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_3_YELLOW_2));
        } else if (getA() >= 0.25 && getA() <= 0.39) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG_1_3_RED_DIAGNOST));
        } else if (getA() <= 0.25) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_LONG_1_3_BURGUNDY_DIAGNOST));
        } else if (getA() >= 0.55 && getA() <= 0.65 && getInputData().isPractice()) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_LONG_1_3_CRIMSON_DIAGNOST));
        }
    }
}
