package com.murik.lite.model.common_A;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultWithCoefficient;

public class A_20_30 extends BaseResultWithCoefficient {


    public A_20_30(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("V");
    }

    public A_20_30(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.56 && getA() <= 0.66) {
            setColorGREEN();
        } else if (getA() >= 0.67 && getA() < 0.69) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_30_YELLOW_1_1));
        } else if (getA() >= 0.7 && getA() <= 0.75) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_30_YELLOW_1_2));
        } else if (getA() >= 0.50 && getA() <= 0.549) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_30_YELLOW_3));
        } else if (getA() >= 0.549 && getA() < 0.57) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_30_YELLOW_3_1));
        } else if (getA() >= 0.27 && getA() <= 0.339) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_30_YELLOW_3_2));
        } else if (getA() >= 0.34 && getA() <= 0.49) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_30_RED_1));
        } else if (getA() >= 0.76 && getA() <= 0.95) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_30_RED_2));
        } else if (getA() >= 0.95) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_20_30_BURGUNDY));
        } else {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_20_30_IF_NOT_IN_RANGE));
        }
    }
    public void setStressResult() {
        if (getA() >= 0 && getA() < 0.65) {
            setColorGREEN();
            stressLevel = 0;
            setPossibleReasons(getResources(R.string.stress0));
        } else if (getA() >= 0.65 && getA() < 0.70) {
            setColorYELLOW();
            stressLevel = 1;
            setPossibleReasons(getResources(R.string.stress1));
        } else if (getA() >= 0.70 && getA() < 0.76) {
            setColorORANGE();
            stressLevel = 2;
            setPossibleReasons(getResources(R.string.stress2));
        } else if (getA() >= 0.76 && getA() < 0.80) {
            setColorRED();
            stressLevel = 3;
            setPossibleReasons(getResources(R.string.stress3));
        } else if (getA() >= 0.80 && getA() < 0.96) {
            setColorBURGUNDY();
            stressLevel = 4;
            setPossibleReasons(getResources(R.string.stress4));
        } else if (getA() >= 0.96) {
            setColorBLUE();
            stressLevel = 5;
            setPossibleReasons(getResources(R.string.stress5));
        }
    }
}
