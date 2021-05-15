package com.murik.enose.model.TAU;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class TAU_60 extends BaseResult {

    public TAU_60(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("Δτ");
    }

    public void setResult() {
        if (getA() > 53 && getA() <= 64) {
            setColorGREEN();
        } else if (getA() >= 40 && getA() <= 53) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.TAU_60_YELLOW));
        } else if (getA() >= 65 && getA() <= 70) {
            setColorRED();
            setPossibleReasons(getResources(R.string.TAU_60_RED));
        } else if (getA() < 40 && getA() > 30) {
            setColorRED();
            setPossibleReasons(getResources(R.string.TAU_60_RED_2));
        } else if (getA() > 70) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.TAU_60_BURGUNDY));
        } else if (getA() <= 30) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.TAU_60_WHITE));
        }
    }
}
