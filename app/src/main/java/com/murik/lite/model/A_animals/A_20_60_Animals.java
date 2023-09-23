package com.murik.lite.model.A_animals;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultWithCoefficient;

public class A_20_60_Animals extends BaseResultWithCoefficient {


    public A_20_60_Animals(double A, DataByMaxParcelable inputData, Context context, float coefficicent) {
        super(A, inputData, context, coefficicent);
        setLegend("IX");
    }

    public A_20_60_Animals(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.20 && getA() <= 0.32) {
            setColorGREEN();
        } else if (getA() >= 0.040 && getA() <= 0.055) {
            setColorBLUE();
        } else if (getA() >= 0.33 && getA() <= 0.35) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_60_YELLOW_ANIMALS));
        } else if(getA() >= 0.40 && getA() <= 0.50) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_60_RED_1_ANIMALS));
        } else if(getA() <= 0.20) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_20_60_WHITE));
        }
    }
}
