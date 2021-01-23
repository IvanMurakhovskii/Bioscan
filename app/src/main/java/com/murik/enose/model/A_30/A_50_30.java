package com.murik.enose.model.A_30;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class A_50_30 extends BaseResultFirst {


    public A_50_30(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VI");
    }

    public void setResult() {
        if (getA() >= 0.42 && getA() <= 0.50) {
            setColorGREEN();
        } else if (getA() >= 0.51 && getA() <= 0.55) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_50_30_YELLOW));
        } else if (getA() >= 0.1 && getA() <= 0.15) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_50_30_RED));
        } else if(getA() < 0) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_50_30_WHITE));
        }
    }
}
