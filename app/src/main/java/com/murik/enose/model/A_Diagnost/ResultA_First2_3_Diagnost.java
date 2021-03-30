package com.murik.enose.model.A_Diagnost;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First2_3_Diagnost extends BaseResultFirst {


    public ResultA_First2_3_Diagnost(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("I");
    }

    public void setResult() {
        if (getA() >= 0.1 && getA() <= 1.2) {
            setColorGREEN();
//            setPossibleReasons(getResources(R.string.A_SHORT80_2_3_RED));
        } else if (getA() >= 0.60 && getA() <= 0.90) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW_DIAGNOST));
        } else if (getA() >= 1.3 && getA() <= 1.6) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW_2_DIAGNOST));
        } else if (getA() >= 1.8 && getA() <= 2.6) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_RED_DIAGNOST));
        } else if (getA() >= 2.6 && getA() <= 5.5) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_BURGUNDY_DIAGNOSTY));
        }
    }
}
