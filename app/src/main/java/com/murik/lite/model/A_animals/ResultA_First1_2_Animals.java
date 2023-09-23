package com.murik.lite.model.A_animals;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First1_2_Animals extends BaseResultFirst {


    public ResultA_First1_2_Animals(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("IV");
    }

    public ResultA_First1_2_Animals(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.340 && getA() <= 0.49) {
            setColorGREEN();
        } else if (getA() >= 0.50 && getA() <= 0.55) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_YELLOW_1_ANIMALS));
        } else if (getA() >= 0.30 && getA() <= 0.33) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_YELLOW_2_ANIMALS));
        } else if (getA() >= 0.20 && getA() <= 0.29) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_RED_1_ANIMALS));
        } else if (getA() >= 0.100 && getA() <= 0.200) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BURGUNDY_1_ANIMALS));
        } else if (getA() >= 0.56 && getA() <= 0.700) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BURGUNDY_2_ANIMALS));
        } else if (getA() >= 0.7) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BURGUNDY_3_ANIMALS));
        }

    }
}
