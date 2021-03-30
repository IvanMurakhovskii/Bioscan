package com.murik.enose.model.A_Diagnost;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First2_5_Diagnost extends BaseResultFirst {


    public ResultA_First2_5_Diagnost(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VII");
    }

    public void setResult() {

        if (getA() >= 0.06 && getA() <= 0.2) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_BLUE_DIAGNOST));
        } else if (getA() >= 0.38 && getA() <= 0.50) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_YELLOW_DIAGNOST));
        } else if (getA() >= 0.71 && getA() <= 1.0) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_RED_DIAGNOST));
        } else if (getA() >= 0.2 && getA() <= 0.37) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_RED_2_DIAGNOST));
        } else if (getA() >= 1 && getInputData().isPractice()) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_CRIMSON_DIAGNOST));
        }
    }
}
