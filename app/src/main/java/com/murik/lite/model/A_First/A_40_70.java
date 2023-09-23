package com.murik.lite.model.A_First;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class A_40_70 extends BaseResultFirst {


    public A_40_70(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("I");
    }

    public void setResult() {
       /* if (getA() >= 0.85 && getA() <= 1.45) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_RED_2));
        } else */
        if (getA() >= 0.76 && getA() <= 0.85) {
            setColorGREEN();
        } else if (getA() >= 0.40 && getA() <= 0.75) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW));
        } else if (getA() >= 0.86 && getA() <= 1) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW_2));
        } else if (getA() >= 1.1 && getA() <= 3) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_RED));
        } else if(getA() > 3) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_BURGUNDY));
        } else if (getA() < 0.40) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_WHITE));
        }
    }
}
