package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_First2_4 extends BaseResult {


    public ResultA_First2_4(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("2_4");
    }

    public void setResult() {
        if (getA() >= 1.1 && getA() <= 1.7) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_YELLOW));
        } else if(getA() >= 2.1 && getA() <= 3.9) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_RED));
        } else if(getA() <= 1) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_BURGUNDY));
        } else if(getA() >4) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_BLUE));
        }
    }
}
