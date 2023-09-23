package com.murik.lite.model.TAU;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class T_80 extends BaseResult {

    public T_80(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("T_80");
    }

    public void setResult() {
        if (getA() > 76 && getA() <= 89) {
            setColorGREEN();
        } else if (getA() >= 70 && getA() <= 75) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.T_80_YELLOW));
        } else if (getA() >= 90 && getA() <= 100) {
            setColorRED();
            setPossibleReasons(getResources(R.string.T_80_RED));
        } else if (getA() >= 41 && getA() <= 70) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.T_80_BURGUNDY));
        } else if (getA() <= 40) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.T_80_WHITE));
        }
    }
}
