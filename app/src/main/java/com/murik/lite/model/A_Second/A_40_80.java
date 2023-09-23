package com.murik.lite.model.A_Second;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultSecond;

public class A_40_80 extends BaseResultSecond {


    public A_40_80(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VI");
    }

    public void setResult() {
        if (getA() >= 0.42 && getA() <= 0.48) {
            setColorGREEN();
        } else if (getA() >= 0.38 && getA() <= 0.41) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_3_YELLOW_1));
        } else if (getA() >= 0.49 && getA() <= 0.55) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_3_YELLOW_2));
        } else if (getA() >= 0.25 && getA() <= 0.37) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG_1_3_RED));
        } else if (getA() <= 0.25) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_LONG_1_3_BURGUNDY));
        } else if (getA() >= 0.55 && getA() <= 0.65 && getInputData().isPractice()) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_LONG_1_3_CRIMSON));
        }
    }
}
