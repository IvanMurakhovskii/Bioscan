package com.murik.enose.model.common_A;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultWithCoefficient;

public class A_30_60 extends BaseResultWithCoefficient {


    public A_30_60(double A, DataByMaxParcelable inputData, Context context, float coefficicent) {
        super(A, inputData, context, coefficicent);
        setLegend("S(30/60)");
    }

    public A_30_60(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.27 && getA() <= 0.28) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_30_60_YELLOW_1));
        } else if (getA() >= 0.12 && getA() <= 0.234) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_30_60_YELLOW_2));
        } else if (getA() >= 0.266 && getA() <= 0.430) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_30_60_RED));
        } else if (getA() >= 0.100 && getA() <= 0.17) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY));
        } else if (getA() <= 0.100) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_2));
        } else if (getA() > 0.310) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_30_60_CRIMSON));
        }
    }
}
