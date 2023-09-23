package com.murik.lite.model.A_First;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First2_4 extends BaseResultFirst {


    public ResultA_First2_4(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VIII");
    }

    public void setResult() {
        if (getA() >= 0.55 && getA() <= 0.66) {
           setColorGREEN();
        } else if (getA() < 0.25) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_BLUE));
        } else if(getA() >= 0.49 && getA() <= 0.54) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_YELLOW));
        }  else if(getA() >= 0.25 && getA() <= 0.48) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_RED));
        } else if(getA() >= 1) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_BURGUNDY));
        }
    }
}
