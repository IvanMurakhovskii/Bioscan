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

    public void setResult() {
        if (getA() >= 0.27 && getA() <= 0.28) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.A_30_60_GREEN_1));
        } else if (getA() >= 0.16 && getA() <= 0.20) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_30_60_YELLOW_1));
        } else if (getA() >= 0.31 && getA() <= 0.43) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_30_60_RED));
        } else if (getA() >= 0.44 && getA() <= 0.55) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_WHITE));
        } else if (getA() <= 0.14) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_WHITE_2));
        } else if (getA() > 0.14 && getA() <= 0.15) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_30_60_BLUE));
        }
    }
}
