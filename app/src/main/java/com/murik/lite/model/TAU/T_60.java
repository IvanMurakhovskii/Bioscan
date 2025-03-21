package com.murik.lite.model.TAU;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class T_60 extends BaseResult {

    public T_60(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("T");
    }

    public void setResult() {
        if (getA() > 55 && getA() <= 66) {
            setColorGREEN();
        } else if (getA() >= 40 && getA() <= 54) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.TAU_60_YELLOW));
        } else if (getA() >= 67 && getA() <= 70) {
            setColorRED();
            setPossibleReasons(getResources(R.string.TAU_60_RED));
        } else if (getA() < 40 && getA() > 30) {
            setColorRED();
            setPossibleReasons(getResources(R.string.TAU_60_RED_2));
        } else if (getA() > 70) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.TAU_60_BURGUNDY));
        } else if (getA() <= 30) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.TAU_60_WHITE));
        }
    }
    public void setStressResult() {
        if (getA() >= 0 && getA() < 69) {
            setColorGREEN();
            stressLevel = 0;
            setPossibleReasons(getResources(R.string.stress0));
        } else if (getA() >= 69 && getA() < 74) {
            stressLevel = 2;
            setColorORANGE();
            setPossibleReasons(getResources(R.string.stress2));
        } else if (getA() >= 74 && getA() < 83) {
            stressLevel = 3;
            setColorRED();
            setPossibleReasons(getResources(R.string.stress3));
        } else if (getA() >= 83 && getA() < 91) {
            stressLevel = 4;
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.stress4));
        } else if (getA() >= 91) {
            stressLevel = 5;
            setColorBLUE();
            setPossibleReasons(getResources(R.string.stress5));
        }
    }
    public double Normalise() { return 100.0; }
}
