package com.murik.lite.model.A_animals;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultSecond;


public class ResultA_Second1_2_Animals extends BaseResultSecond {


    public ResultA_Second1_2_Animals(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("II");
    }

    public void setResult() {
        if (getA() >= 0.57 && getA() <= 0.72) {
            setColorGREEN();
        } else if (getA() >= 0.73 && getA() <= 0.76) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_YELLOW_1_ANIMALS));
        } else if (getA() >= 0.45 && getA() <= 0.58) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_LONG_1_2_YELLOW_2_ANIMALS));
        }
    }
}
