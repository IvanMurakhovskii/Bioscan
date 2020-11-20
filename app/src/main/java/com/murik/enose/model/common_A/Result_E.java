package com.murik.enose.model.common_A;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class Result_E extends BaseResult {


    public Result_E(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("En");
    }

    public void setResult() {
        if (getA() >= 0.30 && getA() <= 0.70) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.E_GREEN_1));
        }
        if (getA() >= 0.71 && getA() <= 0.9) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.E_GREEN_2));
        }
        if (getA() >= 0.1 && getA() <= 0.29) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_YELLOW_1));
        }
        if (getA() >= 1.05 && getA() <= 2) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_YELLOW_2));
        }
        if (getA() >= 2.1 && getA() <= 5) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_YELLOW_2));
        }
        if (getA() <= 0.05) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_WHITE));
        }
    }
}
