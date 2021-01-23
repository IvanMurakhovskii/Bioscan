package com.murik.enose.model.A_30;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class A_20_40 extends BaseResultFirst {


    public A_20_40(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("V");
    }

    public void setResult() {
        if (getA() >= 0.86 && getA() <= 0.90) {
            setColorGREEN();
        } else if (getA() >= 0.69 && getA() <= 0.76) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_40_YELLOW));
        } else if (getA() >= 0.65 && getA() <= 0.68) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_40_YELLOW_2));
        } else if (getA() >= 0.91 && getA() <= 0.95) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_40_RED));
        } else if (getA() > 1) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_20_40_WHITE_1));
        }
    }
}
