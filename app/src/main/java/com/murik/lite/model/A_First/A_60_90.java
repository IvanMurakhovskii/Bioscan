package com.murik.lite.model.A_First;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class A_60_90 extends BaseResultFirst {


    public A_60_90(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("I");
    }

    public void setResult() {
        if (getA() >= 0.86 && getA() <= 1.4) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT80_2_3_RED));
        } else if (getA() >= 0.4 && getA() <= 0.77) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW));
        } else if (getA() >= 0.83 && getA() <= 0.85) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT80_2_3_YELLOW));
        } else if (getA() >= 1.3 && getA() <= 3) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT80_2_3_BURGUNDY));
        } else if (getA() < 0) {
            setColor(Color.WHITE);
            setPossibleReasons("Ошибка в режиме измерения!");
        }
    }
}
