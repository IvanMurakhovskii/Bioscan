package com.murik.enose.model.A_30;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class A_10_20 extends BaseResultFirst {


    public A_10_20(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("I");
    }

    public void setResult() {
        if(getA() >= 0.26 && getA() <= 0.38){
            setColorGREEN();
        } else if(getA() >= 0.18 && getA() <= 0.25) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_10_20_YELLOW));
        } else if(getA() >= 0.15 && getA() <= 0.17) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_10_20_WHITE));
        }
    }
}
