package com.murik.lite.model.A_30;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class A_50_30 extends BaseResultFirst {


    public A_50_30(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VI");
    }

    public void setResult() {
        if (getA() >= 0.42 && getA() <= 0.50) {
            setColorGREEN();
        } else if (getA() >= 0.51 && getA() <= 0.65) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_50_30_YELLOW));
        } else if (getA() >= 0.10 && getA() <= 0.15) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_50_30_RED));
        } else if(getA() <= 0) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_50_30_WHITE));
        } else if (getA() > 1) {
            setColor(Color.WHITE);
            setPossibleReasons("Нарушен алгоритм измерения!");
        }
    }
}
