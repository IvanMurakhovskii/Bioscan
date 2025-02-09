package com.murik.lite.model.A_30;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class A_15_30 extends BaseResultFirst {

    public A_15_30(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("II");
    }

    public A_15_30(double A, DataByMaxParcelable inputData, Context context, float coefficient, String legend) {
        super(A, inputData, context, coefficient);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.33 && getA() < 0.45) {
            setColorGREEN();
        } else if (getA() >= 0.45 && getA() <= 0.470) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_15_30_YELLOW));
        } else if (getA() >= 0.28 && getA() <= 0.32) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_15_30_YELLOW_2));
        } else if (getA() >= 0.20 && getA() <= 0.27) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_15_30_RED));
        } else if (getA() >= 0.48 && getA() <= 0.55) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_15_30_RED_1));
        } else if (getA() >= 0.100 && getA() <= 0.19) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_15_30_BURGUNDY));
        } else if (getA() >= 0.56 && getA() <= 0.700) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_15_30_BURGUNDY_1));
        } else if (getA() >= 0.71) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_15_30_BURGUNDY_2));
        }  else if (getA() <= 0.1) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_15_30_ERROR));
        }
    }
    public void setStressResult() {
        if (getA() >= 0.45 && getA() <= 0.49) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.stress2));
        } else if (getA() >= 0.49 && getA() < 0.55) {
            setColorRED();
            setPossibleReasons(getResources(R.string.stress3));
        } else if (getA() >= 0.55 && getA() <= 0.60) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.stress4));
        } else if (getA() >= 0.60) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.stress5));
        }
    }
}
