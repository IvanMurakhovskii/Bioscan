package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First40_70 extends BaseResultFirst {


    public ResultA_First40_70(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("I");
    }

    public void setResult() {
       /* if (getA() >= 0.85 && getA() <= 1.45) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_RED_2));
        } else */
        if (getA() >= 0.4 && getA() <= 0.77) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW));
        } else if (getA() >= 0.84 && getA() <= 1.3) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW_2));
        } else if (getA() >= 1.4 && getA() <= 3) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_RED));
        } else if(getA() > 3) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_BURGUNDY));
        }
    }
}
