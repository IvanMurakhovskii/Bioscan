package com.murik.lite.model.A_First;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First2_5 extends BaseResultFirst {


    public ResultA_First2_5(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VII");
    }

    public void setResult() {
        if (getA() >= 0.19 && getA() <= 0.26) {
            setColorGREEN();
        } else if (getA() >= 0.04 && getA() <= 0.09) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_BLUE));
        } else if (getA() >= 0.16 && getA() <= 0.18) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_YELLOW));
        } else if (getA() >= 0.31 && getA() <= 0.43) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_RED));
        } else if (getA() >= 0.10 && getA() <= 0.15) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_RED_2));
        } else if (getA() >= 0.51 && getInputData().isPractice()) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_CRIMSON));
        }
    }
}
