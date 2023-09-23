package com.murik.lite.model.common_A;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class Result_E extends BaseResult {


    public Result_E(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("En");
    }

    public void setResult() {
        if (getA() >= 0.30 && getA() <= 0.70) {
            setColorGREEN();
//        } else if (getA() >= 0.71 && getA() <= 0.9) {
//            setColorGREEN();
//            setPossibleReasons(getResources(R.string.E_GREEN_2));
//        } else if (getA() >= 0.1 && getA() <= 0.29) {
//            setColorYELLOW();
//            setPossibleReasons(getResources(R.string.E_YELLOW_1));
//        } else if (getA() >= 1.6 && getA() <= 2) {
//            setColorYELLOW();
//            setPossibleReasons(getResources(R.string.E_YELLOW_2));
//        } else if (getA() >= 2.1 && getA() <= 5) {
//            setColorYELLOW();
//            setPossibleReasons(getResources(R.string.E_YELLOW_2));
//        } else if (getA() >= 1 && getA() <= 1.5) {
//            setColorYELLOW();
//            setPossibleReasons(getResources(R.string.E_YELLOW_3));
//        } else if (getA() <= 0.05) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_WHITE));
        }
    }
}
