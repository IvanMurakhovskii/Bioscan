package com.murik.lite.model.A_30;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class S_15_30 extends BaseResultFirst {


    public S_15_30(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("S(15/30)");
    }

    public void setResult() {
        if (getA() >= 0.21 && getA() <= 0.26) {
            setColorGREEN();
        } else if (getA() >= 0.17 && getA() < 0.19) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.S30_15_30_YELLOW));
        } else if (getA() >= 0.19 && getA() < 0.21) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.S30_15_30_YELLOW_1));
        } else if (getA() >= 0.27 && getA() <= 0.30) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.S30_15_30_YELLOW_2));
        } else if (getA() >= 0.31 && getA() <= 0.43) {
            setColorRED();
            setPossibleReasons(getResources(R.string.S30_15_30_RED));
        } else if (getA() >= 0.1 && getA() <= 0.16) {
            setColorRED();
            setPossibleReasons(getResources(R.string.S30_15_30_RED_1));
        } else if (getA() >= 0.44 && getA() <= 0.65) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.S30_15_30_BURGUNDY));
        } else if (getA() < 0.1) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_30_60_WHITE_1));
        }
    }
}
