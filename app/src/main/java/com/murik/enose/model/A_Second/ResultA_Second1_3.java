package com.murik.enose.model.A_Second;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_Second1_3 extends BaseResult {


    public ResultA_Second1_3(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("1_3");
    }

    public void setResult() {
        if (getA() >= 0.4 && getA() <= 0.48) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_YELLOW));
        } else if (getA() >= 0.25 && getA() <= 0.39) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_RED));
        } else if (getA() <= 0.25) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BURGUNDY));
        } else if (getA() >= 0.58 && getA() <= 0.65) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_CRIMSON));
        }
    }
}
