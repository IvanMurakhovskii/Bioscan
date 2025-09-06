package com.murik.lite.model.common_A;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultWithCoefficient;

public class S_30_60 extends BaseResultWithCoefficient {


    public S_30_60(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("S(30/60)");
    }

    public S_30_60(double A, DataByMaxParcelable inputData, Context context, float coefficient, String legend) {
        super(A, inputData, context, coefficient);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.19 && getA() <= 0.26) {
            setColorGREEN();
        } else if (getA() >= 0.16 && getA() < 0.19) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_30_60_YELLOW_1));
        } else if (getA() > 0.26 && getA() <= 0.30) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_30_60_YELLOW_2));
        } else if (getA() >= 0.31 && getA() <= 0.43) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_30_60_RED));
        } else if (getA() >= 0.44 && getA() <= 0.55) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_2));
        } else if (getA() >= 0.10 && getA() < 0.16) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_1));
        } /*else if (getA() >= 0.44 && getA() <= 0.56) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_30_60_BURGUNDY_2));
        }*/ else if (getA() < 0.1) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_30_60_WHITE_1));
        } else if (getA() > 0.6) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_30_60_WHITE_2));
        }
    }
    public void setSecondStressResult() {
        if ((getA() >= 0.19 && getA() <= 1000) || (getA() < 0.06)) {
            setColorGREEN();
            secondStressLevel = 0;
            setPossibleReasons(getResources(R.string.second_stress0));
        } else if (getA() >= 0.15 && getA() < 0.19) {
            secondStressLevel = 1;
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.second_stress1));
        } else if (getA() >= 0.12 && getA() < 0.15) {
            secondStressLevel = 2;
            setColorORANGE();
            setPossibleReasons(getResources(R.string.second_stress2));
        } else if (getA() >= 0.10 && getA() < 0.12) {
            secondStressLevel = 3;
            setColorRED();
            setPossibleReasons(getResources(R.string.second_stress3));
        } else {
            secondStressLevel = 5;
            setColorBLUE();
            setPossibleReasons(getResources(R.string.second_stress5));
        }
    }
}
