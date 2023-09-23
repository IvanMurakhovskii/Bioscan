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
}
