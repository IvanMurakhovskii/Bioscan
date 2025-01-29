package com.murik.lite.model.common_A;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultWithCoefficient;

public class A_20_60 extends BaseResultWithCoefficient {


    public A_20_60(double A, DataByMaxParcelable inputData, Context context, float coefficicent) {
        super(A, inputData, context, coefficicent);
        setLegend("IX");
    }

    public A_20_60(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.18 && getA() <= 0.31) {
            setColorGREEN();
        } else if (getA() >= 0.32 && getA() <= 0.35) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_60_YELLOW));
        } else if (getA() >= 0.15 && getA() <= 0.17) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_60_YELLOW_2));
        } else if (getA() >= 0.35 && getA() <= 0.39) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_60_YELLOW_3));
        } else if(getA() >= 0.40 && getA() <= 0.50) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_60_RED_1));
        } else if(getA() >= 0.11 && getA() <= 0.14) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_60_RED_2));
        } else if(getA() >= 0.05 && getA() <= 0.1) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_20_60_BURGUNDY_1));
        } else if(getA() > 0.5) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_20_60_BURGUNDY_2));
        } else if(getA() <= 0.05) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_20_60_WHITE));
        }
    }
}
