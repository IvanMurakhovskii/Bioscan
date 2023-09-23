package com.murik.lite.model.A_First;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

import lombok.var;

public class B extends BaseResult {


    public B(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("B");
    }

    public void setResult() {

        var result = getA();

        if (result >= 1.6 && result <= 2) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_B_YELLOW_1));
        } else if (result >= 0.51 && result <= 0.79) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_B_YELLOW_2));
        } else if (result >= 0.4 && result <= 0.5) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_B_RED_1));
        } else if (result >= 2.1 && result <= 4.0) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_B_RED_2));
        } else if (result >= 4.1 && result <= 6.9) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_B_RED_3));
        } else if (result >= 7 && result <= 8) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_B_BURGUNDY_1));
        } else if (result >= 9 && result <= 15) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_B_BURGUNDY_2));
        } else if (result >= 50) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_B_BURGUNDY_3));
        } else if (result >= 85 && result <= 100) {
            setColorPINK();
            setPossibleReasons(getResources(R.string.A_SHORT_B_PINK));
        }
    }
}
