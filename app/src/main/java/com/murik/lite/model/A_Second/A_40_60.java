package com.murik.lite.model.A_Second;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultSecond;

public class A_40_60 extends BaseResultSecond {


    public A_40_60(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("II");
    }

    public void setResult() {
        if (getA() >= 0.58 && getA() <= 0.67) {
            setColorGREEN();
        } else if (getA() >= 0.68 && getA() <= 0.75) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_YELLOW_1));
        } else if (getA() >= 0.45 && getA() < 0.539) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_YELLOW_2_1));
        } else if (getA() >= 0.54 && getA() <= 0.57) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_YELLOW_2_2));
        } else if (getA() >= 0.27 && getA() <= 0.34) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_YELLOW_3));
        } else if (getA() >= 0.35 && getA() <= 0.44) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_RED_2));
        } else if (getA() >= 0.76 && getA() <= 0.95) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_RED_3));
        } else if (getA() >= 0.95) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_WHITE));
        }
    }
    public void setStressResult() {
        if (getA() >= 0 && getA() < 0.70) {
            setColorGREEN();
            stressLevel = 0;
            setPossibleReasons(getResources(R.string.stress0));
        } else if (getA() >= 0.70 && getA() < 0.76) {
            setColorYELLOW();
            stressLevel = 1;
            setPossibleReasons(getResources(R.string.stress1));
        } else if (getA() >= 0.76 && getA() < 0.98) {
            setColorORANGE();
            stressLevel = 2;
            setPossibleReasons(getResources(R.string.stress2));
        } else if (getA() >= 0.98 && getA() < 1.21) {
            setColorRED();
            stressLevel = 3;
            setPossibleReasons(getResources(R.string.stress3));
        } else if (getA() >= 1.21 && getA() <= 200) {
            setColorBURGUNDY();
            stressLevel = 4;
            setPossibleReasons(getResources(R.string.stress4));
        } else {
            setColorBLUE();
            stressLevel = 5;
            setPossibleReasons(getResources(R.string.stress5));
        }
    }
}
