package com.murik.lite.model.A_animals;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;


public class E_60_Animals extends BaseResult {


    public E_60_Animals(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("En");
    }

    public void setResult() {
        if (getA() >= 0.19 && getA() <= 0.40) {
            setColorGREEN();
        } else if (getA() >= 0.14 && getA() <= 0.18) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_YELLOW_ANIMALS));
        } else if (getA() >= 0.090 && getA() <= 0.13) {
            setColorRED();
            setPossibleReasons(getResources(R.string.E_RED_ANIMALS));
        } else if (getA() >= 0.039 && getA() <= 0.089) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_BURGUNDY_1_ANIMALS));
        } else if (getA() >= 0.40) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_BURGUNDY_2_ANIMALS));
        } else if (getA() <= 0.038) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_WHITE));
        }
    }
}
