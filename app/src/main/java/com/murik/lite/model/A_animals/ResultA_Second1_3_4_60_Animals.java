package com.murik.lite.model.A_animals;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultSecond;

public class ResultA_Second1_3_4_60_Animals extends BaseResultSecond {


    public ResultA_Second1_3_4_60_Animals(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VI");
    }

    public void setResult() {
        if (getA() >= 0.22 && getA() <= 0.41) {
            setColorGREEN();
        } else if (getA() >= 0.42 && getA() <= 0.49) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG1_1_3_YELLOW_1_ANIMALS));
        } else if (getA() <= 0.15) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG1_1_3_RED_ANIMALS));
        } else if (getA() > 0.15 && getA() <= 0.21) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_LONG1_1_3_RED_1_ANIMALS));
        } else if (getA() >= 0.50) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_LONG1_1_3_BURGUNDY_1_ANIMALS));
        }
    }
}
