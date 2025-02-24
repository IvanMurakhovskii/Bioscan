package com.murik.lite.model.A_30;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class A_10_20 extends BaseResultFirst {


    public A_10_20(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("I");
    }

    public A_10_20(double A, DataByMaxParcelable inputData, Context context, float coefficient, String legend) {
        super(A, inputData, context, coefficient);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.26 && getA() <= 0.39) {
            setColorGREEN();
        } else if (getA() >= 0.18 && getA() <= 0.25) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_10_20_YELLOW));
        } else if (getA() >= 0.67 && getA() <= 0.85) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_10_20_YELLOW_1));
        } else if (getA() >= 0.15 && getA() <= 0.17) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_10_20_BURGUNDY));
        } else if (getA() >= 0.40 && getA() <= 0.54) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_10_20_YELLOW_2));
//       } else if (getA() >= 0.44 && getA() <= 0.5) {
//            setColorRED();
//            setPossibleReasons(getResources(R.string.A_10_20_RED));
       } else if (getA() <= 0.14) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_10_20_WHITE));
        }
    }
    public void setStressResult() {
        if (getA() >= 0.2 && getA() <= 0.41) {
            stressLevel = 2;
            setColorORANGE();
            setPossibleReasons(getResources(R.string.stress2));
        } else if (getA() >= 0.41 && getA() < 0.48) {
            stressLevel = 3;
            setColorRED();
            setPossibleReasons(getResources(R.string.stress3));
        } else if (getA() >= 0.48 && getA() <= 0.54) {
            stressLevel = 4;
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.stress4));
        } else if (getA() >= 0.54) {
            stressLevel = 5;
            setColorBLUE();
            setPossibleReasons(getResources(R.string.stress5));
        }
    }
}
