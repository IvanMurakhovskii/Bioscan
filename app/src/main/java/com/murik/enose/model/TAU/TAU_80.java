package com.murik.enose.model.TAU;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class TAU_80 extends BaseResult {

    public TAU_80(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("Δτ");
    }

    public void setResult() {
        if (getA() >= 76 && getA() <= 84) {
            setColorGREEN();
        } else if (getA() >= 70 && getA() <= 75) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.TAU_80_YELLOW));
        } else if (getA() >= 85 && getA() <= 90) {
            setColorRED();
            setPossibleReasons(getResources(R.string.TAU_80_RED));
        } else if (getA() <= 70 && getA() > 40) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.TAU_80_BURGUNDY));
        } else if (getA() <= 40) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.TAU_80_WHITE));
        }
    }
}
