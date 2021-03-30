package com.murik.enose.model.A_Diagnost;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultWithCoefficient;

public class S_30_60_Diagnost extends BaseResultWithCoefficient {


    public S_30_60_Diagnost(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("XII");
    }

    public void setResult() {
        if (getA() > 0.245 && getA() <= 0.255) {
            setColorGREEN();
        } else if (getA() >= 0.256 && getA() <= 0.265) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_30_60_YELLOW_1_DIAGNOST));
        } else if(getA() >= 0.200 && getA() <= 0.234) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_30_60_YELLOW_2_DIAGNOST));
        } else if(getA() >= 0.235 && getA() <= 0.244) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_30_60_YELLOW_3_DIAGNOST));
        } else if(getA() >= 0.266 && getA() <= 0.310) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_30_60_RED_1_DIAGNOST));
        } else if(getA() >= 0.100 && getA() <= 0.199) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_DIAGNOST));
        } else if(getA() < 0.100) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_2_DIAGNOST));
        }
    }
}
