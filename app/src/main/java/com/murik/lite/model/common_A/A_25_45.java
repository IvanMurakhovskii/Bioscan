package com.murik.lite.model.common_A;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultWithCoefficient;

public class A_25_45 extends BaseResultWithCoefficient {


    public A_25_45(double A, DataByMaxParcelable inputData, Context context, float coefficicent) {
        super(A, inputData, context, coefficicent);
        setLegend("VI_L");
    }

    public A_25_45(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.35 && getA() <= 0.56) {
            setColorGREEN();
        } else if (getA() >= 0.57 && getA() <= 0.65) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_25_45_YELLOW));
        } else if (getA() >= 0.66 && getA() <= 0.69) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_25_45_RED));
        } else if (getA() >= 0.7 && getA() <= 0.76) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_25_45_BURGUNDY));
        }
    }
    public void setStressResult() {
        if (getA() >= 0 && getA() < 0.57) {
            setColorGREEN();
            stressLevel = 0;
            setPossibleReasons(getResources(R.string.stress0));
        } else if (getA() >= 0.57 && getA() < 0.70) {
            setColorYELLOW();
            stressLevel = 1;
            setPossibleReasons(getResources(R.string.stress1));
        } else if (getA() >= 0.70 && getA() < 0.77) {
            setColorORANGE();
            stressLevel = 2;
            setPossibleReasons(getResources(R.string.stress2));
        } else if (getA() >= 0.77 && getA() < 0.81) {
            setColorRED();
            stressLevel = 3;
            setPossibleReasons(getResources(R.string.stress3));
        } else if (getA() >= 0.81 && getA() <= 100) {
            setColorBURGUNDY();
            stressLevel = 4;
            setPossibleReasons(getResources(R.string.stress4));
        } else if (getA() >= 100) {
            setColorBLUE();
            stressLevel = 5;
            setPossibleReasons(getResources(R.string.stress5));
        }
    }
}
