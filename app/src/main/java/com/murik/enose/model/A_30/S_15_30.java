package com.murik.enose.model.A_30;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class S_15_30 extends BaseResultFirst {


    public S_15_30(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("S(15/30)");
    }

    public void setResult() {
        if (getA() >= 0.22 && getA() <= 0.26) {
            setColorGREEN();
        } else if (getA() >= 0.17 && getA() <= 0.21) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.S30_15_30_YELLOW));
        } else if (getA() >= 0.27 && getA() <= 0.30) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.S30_15_30_YELLOW_2 ));
        } else if (getA() >= 0.31 && getA() <= 0.43) {
            setColorRED();
            setPossibleReasons(getResources(R.string.S30_15_30_RED));
        } else if (getA() >= 0.10 && getA() <= 0.16) {
            setColorRED();
            setPossibleReasons(getResources(R.string.S30_15_30_RED_1));
        } else if (getA() >= 0.44 && getA() <= 0.55) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.S30_15_30_BURGUNDY));
        }
    }
}
