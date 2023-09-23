package com.murik.lite.model.A_30;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class A_15_45 extends BaseResultFirst {


    public A_15_45(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("III");
    }

    public void setResult() {
        if(getA() >= 0.66 && getA() <= 0.76){
            setColorGREEN();
        } else if(getA() >= 0.35 && getA() <= 0.55) {
            setColorRED();
            setPossibleReasons(getResources(R.string.S30_15_45_RED_1));
        } else if(getA() >= 0.56 && getA() <= 0.65) {
            setColorRED();
            setPossibleReasons(getResources(R.string.S30_15_45_YELLOW));
        } else if(getA() >= 0.1 && getA() <= 0.34) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.S30_15_45_YELLOW));
        } else if (getA() <= 0) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.S30_15_45_WHITE));

        }
    }
}
