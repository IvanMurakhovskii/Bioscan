package com.murik.lite.model.common_A;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultWithCoefficient;

public class A_15_20 extends BaseResultWithCoefficient {


    public A_15_20(double A, DataByMaxParcelable inputData, Context context, float coefficicent) {
        super(A, inputData, context, coefficicent);
        setLegend("VII_L");
    }

    public A_15_20(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.35 && getA() <= 0.75) {
            setColorGREEN();
        } else if (getA() >= 0.76 && getA() <= 0.85) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_15_20_YELLOW));
        } else if(getA() >= 0.20 && getA() <= 0.34) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_15_20_RED));
        }
    }
    public void setStressResult() {
        if (getA() >= 0 && getA() < 0.60) {
            setColorGREEN();
            stressLevel = 0;
            setPossibleReasons(getResources(R.string.stress0));
        } else if (getA() >= 0.60 && getA() < 0.76) {
            setColorYELLOW();
            stressLevel = 1;
            setPossibleReasons(getResources(R.string.stress1));
        } else if (getA() >= 0.76 && getA() < 0.82) {
            stressLevel = 2;
            setColorORANGE();
            setPossibleReasons(getResources(R.string.stress2));
        } else if (getA() >= 0.82 && getA() < 100) {
            stressLevel = 3;
            setColorRED();
            setPossibleReasons(getResources(R.string.stress3));
        } else if (getA() >= 100 && getA() < 200) {
            stressLevel = 4;
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.stress4));
        } else if (getA() >= 200) {
            setColorBLUE();
            stressLevel = 5;
            setPossibleReasons(getResources(R.string.stress5));
        }
    }
}
