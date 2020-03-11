package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_First2_3 extends BaseResult {


    public ResultA_First2_3(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("2_3");
    }

    public void setResult() {
        if (getA() >= 0.87 && getA() <= 0.9) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_RED));
        } else if (getA() >= 0.6 && getA() <= 0.8) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW));
        } else if (getA() >= 1.3 && getA() <= 1.6) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_YELLOW_2));
        } else if (getA() >= 1.8 && getA() <= 2.6) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_RED_2));
        } else if (getA() >= 2.6 && getA() <= 5.5) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_BURGUNDY));
        } else if (getA() >= 0.10 && getA() <= 0.20) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_1));
        }
    }
}
