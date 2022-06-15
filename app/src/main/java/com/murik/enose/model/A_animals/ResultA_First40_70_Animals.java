package com.murik.enose.model.A_animals;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First40_70_Animals extends BaseResultFirst {


    public ResultA_First40_70_Animals(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("I");
    }

    public void setResult() {

        if (getA() >= 0.60 && getA() <= 0.83) {
            setColorGREEN();
        } else if (getA() >= 0.40 && getA() <= 0.59) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW_ANIMALS));
        } else if (getA() >= 0.84 && getA() <= 1.3) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW_2_ANIMALS));
        } else if (getA() >= 1.4 && getA() <= 3) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_RED_ANIMALS));
        } else if(getA() > 3) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_BURGUNDY_ANIMALS));
        }
    }
}
