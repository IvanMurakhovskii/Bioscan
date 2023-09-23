package com.murik.lite.model.A_Second;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultSecond;

public class A_90_60 extends BaseResultSecond {


    public A_90_60(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VI");
    }

    public void setResult() {
        if (getA() >= 0.22 && getA() <= 0.37) {
            setColorGREEN();
        } else if (getA() >= 0.38 && getA() <= 0.41) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG1_1_3_YELLOW_1));
        } else if (getA() >= 0.15 && getA() <= 0.21) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG1_1_3_YELLOW_2));
        } else if (getA() <= 0.15) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG1_1_3_RED));
        } else if (getA() > 0.42 && getA() <= 0.49) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_LONG1_1_3_BURGUNDY_1));
        } else if (getA() >= 0.50 && getA() <= 0.59) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_LONG1_1_3_BURGUNDY_2));
        } else if (getA() >= 0.60 && getA() <= 0.70) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_LONG1_1_3_BURGUNDY_3));
        }
    }
}
