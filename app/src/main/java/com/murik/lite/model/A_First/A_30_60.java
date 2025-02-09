package com.murik.lite.model.A_First;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class A_30_60 extends BaseResultFirst {


    public A_30_60(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("IV");
    }

    public A_30_60(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.340 && getA() <= 0.47) {
            setColorGREEN();
        } else if (getA() >= 0.48 && getA() <= 0.55) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_YELLOW_80));
        } else if (getA() >= 0.28 && getA() <= 0.32) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_YELLOW_2));
        } else if (getA() >= 0.20 && getA() <= 0.27) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_RED_1));
        } else if (getA() >= 0.48 && getA() <= 0.55) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_RED_2));
        } else if (getA() >= 0.10 && getA() <= 0.20) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BURGUNDY_1));
        } else if (getA() >= 0.56 && getA() <= 0.700) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BURGUNDY_2));
        } else if (getA() >= 0.70) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BURGUNDY_3));
        } else if (getA() <= 0.100) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_WHITE));
        }
    }
    public void setStressResult() {
        if (getA() >= 0.44 && getA() <= 0.56) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.stress1));
        } else if (getA() >= 0.56 && getA() <= 0.64) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.stress2));
        } else if (getA() >= 0.64 && getA() < 0.70) {
            setColorRED();
            setPossibleReasons(getResources(R.string.stress3));
        } else if (getA() >= 0.70 && getA() <= 0.76) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.stress4));
        } else if (getA() >= 0.76) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.stress5));
        }
    }
}
