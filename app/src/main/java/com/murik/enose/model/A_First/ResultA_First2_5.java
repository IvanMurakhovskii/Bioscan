package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First2_5 extends BaseResultFirst {


    public ResultA_First2_5(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VII");
    }

    public void setResult() {

        if (getA() >= 0.04 && getA() <= 0.09) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_BLUE));
        } else if (getA() >= 0.19 && getA() <= 0.23) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_YELLOW));
        } else if (getA() >= 0.36 && getA() <= 0.50) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_RED));
        } else if (getA() >= 0.1 && getA() <= 0.18) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_RED_2));
        } else if (getA() >= 0.51 && getInputData().isPractice()) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_CRIMSON));
        }
    }
}
