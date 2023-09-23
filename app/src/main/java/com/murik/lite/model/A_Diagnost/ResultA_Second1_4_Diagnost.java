package com.murik.lite.model.A_Diagnost;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultSecond;

import lombok.val;

public class ResultA_Second1_4_Diagnost extends BaseResultSecond {


    public ResultA_Second1_4_Diagnost(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("X");
    }

    public void setResult() {
        val a = getA();

        if (a >= 0.80 && a <= 0.91) {
            setColorGREEN();
        } else if (a >= 0.55 && a <= 0.79) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_4_YELLOW_DIAGNOST));
        } else if (a >= 1 && a <= 1.67) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_4_YELLOW__2_DIAGNOST));
        } else if (a >= 0.25 && a <= 0.54) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG_1_4_RED_DIAGNOST));
        } else if (a < 0.25) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_LONG_1_4_BURGUNDY_DIAGNOST));
        }
    }
}
