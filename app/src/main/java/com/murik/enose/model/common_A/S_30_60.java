package com.murik.enose.model.common_A;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultWithCoefficient;

public class S_30_60 extends BaseResultWithCoefficient {


    public S_30_60(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("S(30/60)");
    }

    public void setResult() {
        if (getA() >= 0.22 && getA() <= 0.26) {
            setColorGREEN();
        } else if (getA() >= 0.17 && getA() <= 0.21) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_30_60_YELLOW_1));
        } else if (getA() >= 0.27 && getA() <= 0.30) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_30_60_YELLOW_2));
        } else if (getA() >= 0.31 && getA() <= 0.43) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_30_60_RED));
        } else if (getA() >= 0.44 && getA() <= 0.55) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_2));
        } else if (getA() >= 0.10 && getA() <= 0.16) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_1));
        } else if (getA() >= 0.44 && getA() <= 0.55) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_2));
        } else if (getA() < 0.1) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_1));
        } else if (getA() > 0.6) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_2));
        }
    }
}
