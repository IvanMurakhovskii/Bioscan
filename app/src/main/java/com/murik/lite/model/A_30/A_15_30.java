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
        if (getA() >= 0 && getA() < 0.47) {
            setColorGREEN();
            stressLevel = 0;
            setPossibleReasons(getResources(R.string.stress0));
        } else if (getA() >= 0.47 && getA() < 0.56) {
            stressLevel = 2;
            setColorORANGE();
            setPossibleReasons(getResources(R.string.stress2));
        } else if (getA() >= 0.56 && getA() < 0.61) {
            stressLevel = 3;
            setColorRED();
            setPossibleReasons(getResources(R.string.stress3));
        } else if (getA() >= 0.61 && getA() < 0.72) {
            stressLevel = 4;
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.stress4));
        } else if (getA() >= 0.72) {
            stressLevel = 5;
            setColorBLUE();
            setPossibleReasons(getResources(R.string.stress5));
        }
    }
    public void setSecondStressResult() {
        if ((getA() >= 0.28 && getA() <= 1000) || (getA() >= 0 && getA() < 0.11)) {
            setColorGREEN();
            secondStressLevel = 0;
            setPossibleReasons(getResources(R.string.second_stress0));
        } else if (getA() >= 0.26 && getA() < 0.28) {
            secondStressLevel = 1;
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.second_stress1));
        } else if (getA() >= 0.18 && getA() < 0.22) {
            secondStressLevel = 2;
            setColorORANGE();
            setPossibleReasons(getResources(R.string.second_stress2));
        } else if (getA() >= 0.22 && getA() < 0.26) {
            secondStressLevel = 3;
            setColorRED();
            setPossibleReasons(getResources(R.string.second_stress3));
        } else if (getA() >= 0.11 && getA() < 0.18) {
            secondStressLevel = 4;
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.second_stress4));
        } else {
            secondStressLevel = 5;
            setColorBLUE();
            setPossibleReasons(getResources(R.string.second_stress5));
        }
    }
}
