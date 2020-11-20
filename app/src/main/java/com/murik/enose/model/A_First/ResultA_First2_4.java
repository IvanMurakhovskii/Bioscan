package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First2_4 extends BaseResultFirst {


    public ResultA_First2_4(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VIII");
    }

    public void setResult() {
        if (getA() < 0.20) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_BLUE));
        } else if(getA() >= 0.49 && getA() <= 0.54) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_YELLOW));
        }  else if(getA() >= 0.20 && getA() <= 0.48) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_RED));
        } else if(getA() >= 1) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_BURGUNDY));
        }
    }
}
