package com.murik.lite.model.common_A;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultWithCoefficient;

public class    A_20_30_for_80 extends BaseResultWithCoefficient {


    public A_20_30_for_80(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("V");
    }

    public void setResult() {
        if (getA() > 0.56 && getA() <= 0.66) {
            setColorGREEN();
        } else if (getA() >= 0.67 && getA() <= 0.75) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_30_80_YELLOW_1));
        }  else if(getA() >= 0.50 && getA() <= 0.55) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_30_YELLOW_3));
        } else if(getA() >= 0.27 && getA() <= 0.49) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_30_RED_1));
        } else if(getA() >= 0.76 && getA() <= 0.95) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_30_RED_2));
        } else if(getA() >= 0.96) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_20_30_BURGUNDY));
        } else {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_20_30_IF_NOT_IN_RANGE));
        }
    }
}
