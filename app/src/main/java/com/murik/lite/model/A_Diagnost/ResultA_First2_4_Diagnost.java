package com.murik.lite.model.A_Diagnost;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First2_4_Diagnost extends BaseResultFirst {


    public ResultA_First2_4_Diagnost(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VIII");
    }

    public void setResult() {
        if (getA() < 0.25) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_BLUE_DIAGNOST));
        } else if(getA() >= 0.58 && getA() <= 0.90) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_YELLOW_DIAGNOST));
        }  else if(getA() >= 0.25 && getA() <= 0.48) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_RED_DIAGNOST));
        } else if(getA() >= 1) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_BURGUNDY_DIAGNOST));
        }
    }
}
