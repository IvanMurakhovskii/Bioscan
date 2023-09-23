package com.murik.lite.model.A_Second;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultSecond;

public class A_60_80 extends BaseResultSecond {


    public A_60_80(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("III");
    }

    public void setResult() {
        if (getA() >= 0.70 && getA() <= 0.72) {
            setColorGREEN();
        } else if ( getA() >= 0.73 && getA() <= 0.77) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_2_3_YELLOW));
        } else if (getA() >=0.78 && getA() <= 0.85) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG_2_3_RED_2));
        } else if (getA() >=0.48 && getA() <= 0.51) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_LONG_2_3_RED_1));
        }
    }
}
