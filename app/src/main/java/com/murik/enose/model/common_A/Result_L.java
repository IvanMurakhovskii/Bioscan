package com.murik.enose.model.common_A;

import android.content.Context;

import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class Result_L extends BaseResult {


    public Result_L(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("L");
    }

    public void setResult() {
        if (getA() >= 0.080 && getA() <= 0.14) {
            setColorGREEN();
        }
//        if (getA() >= 0.15 && getA() <= 0.21) {
//            setColorYELLOW();
//            setPossibleReasons(getResources(R.string.L_YELLOW));
//        }
//        if (getA() >= 0.22 && getA() <= 0.28) {
//            setColorRED();
//            setPossibleReasons(getResources(R.string.L_RED_1));
//        }
//        if (getA() >= 0.050 && getA() <= 0.079) {
//            setColorRED();
//            setPossibleReasons(getResources(R.string.L_RED_2));
//        }
//        if (getA() >= 0.050 && getA() <= 0.079) {
//            setColorBURGUNDY();
//            setPossibleReasons(getResources(R.string.L_BURGUNDY));
//        }
//        if (getA() >= 0.35) {
//            setColorPRIMARY_DARK();
//            setPossibleReasons(getResources(R.string.L_DARK_BLUE));
//        }
    }
}
