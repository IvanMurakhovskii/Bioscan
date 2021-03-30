package com.murik.enose.model.A_30;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class A_15_30 extends BaseResultFirst {


    public A_15_30(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("II");
    }

    public void setResult() {
        if (getA() >= 0.340 && getA() <= 0.454) {
            setColorGREEN();
        } else if (getA() >= 0.455 && getA() <= 0.470) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_15_30_YELLOW));
        } else if (getA() >= 0.30 && getA() <= 0.334) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_15_30_YELLOW_2));
        } else if (getA() >= 0.20 && getA() <= 0.29) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_15_30_RED));
        } else if (getA() >= 0.48 && getA() <= 0.55) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_15_30_RED_1));
        } else if (getA() >= 0.100 && getA() <= 0.200) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_15_30_BURGUNDY));
        } else if (getA() >= 0.56 && getA() <= 0.700) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_15_30_BURGUNDY_1));
        } else if (getA() >= 0.70 && getA() < 0.100) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_15_30_BURGUNDY_2));
        }  else if (getA() <= 0.100 && getInputData().isPractice()) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_15_30_CRIMSON));
        }
    }
}
