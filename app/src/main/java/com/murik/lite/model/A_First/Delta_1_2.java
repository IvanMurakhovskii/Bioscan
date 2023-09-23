package com.murik.lite.model.A_First;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

import lombok.var;

public class Delta_1_2 extends BaseResult {


    public Delta_1_2(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("Δ1/2");
    }

    public void setResult() {

        var result = getA();

        if (result >= 16 && result <= 40) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.DELTA_1_2_YELLOW));
        } else if (result > 41 && result <= 60) {
            setColorRED();
            setPossibleReasons(getResources(R.string.DELTA_1_2_RED));
        }  else if (result > 61 && result <= 50) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.DELTA_1_2_BURGUNDY_1));
        } else if (result > 50 && result <= 80) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.DELTA_1_2_BURGUNDY_2));
        } else if (result > 80) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.DELTA_1_2_CRIMSON));
        }
    }
}
