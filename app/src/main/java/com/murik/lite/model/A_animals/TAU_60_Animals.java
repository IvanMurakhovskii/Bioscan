package com.murik.lite.model.A_animals;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class TAU_60_Animals extends BaseResult {

    public TAU_60_Animals(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("Δτ");
    }

    public void setResult() {
        if (getA() > 53 && getA() <= 70) {
            setColorGREEN();
        } else if (getA() >= 40 && getA() <= 52) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.TAU_60_YELLOW_ANIMALS));
        } else if (getA() >= 71 && getA() <= 74) {
            setColorRED();
            setPossibleReasons(getResources(R.string.TAU_60_RED_ANIMALS));
        } else if (getA() < 40) {
            setColorRED();
            setPossibleReasons(getResources(R.string.TAU_60_RED_2_ANIMALS));
        } else if (getA() > 70) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.TAU_60_BURGUNDY_ANIMALS));
        } else if (getA() <= 30) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.TAU_60_WHITE));
        }
    }
}
