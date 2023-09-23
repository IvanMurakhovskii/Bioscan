package com.murik.lite.model.A_animals;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class E_2_60_Animals extends BaseResult {


    public E_2_60_Animals(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("E2");
    }

    @Override
    public void setResult() {
        if(getA() >= 0.43 && getA() <= 0.52) {
            setColorGREEN();
        } else if (getA() >= 0.53 && getA() <= 0.56) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_2_YELLOW_1_ANIMALS));
        } else if (getA() >= 0.35 && getA() <= 0.42) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_2_YELLOW_2_ANIMALS));
        } else if (getA() >= 0.57 && getA() <= 0.64) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.E_2_ORANGE_1_ANIMALS));
        } else if (getA() >= 0.65 && getA() <= 0.71) {
            setColorRED();
            setPossibleReasons(getResources(R.string.E_2_RED_ANIMALS));
        } else if (getA() >= 0.20 && getA() <= 0.34) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_2_BURGUNDY_1_ANIMALS));
        } else if (getA() >= 0.72 && getA() <= 0.74) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_2_BURGUNDY_2_ANIMALS));
        } else if (getA() < 0.20 && getA() > 0.75) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_2_WHITE));
        }
    }
}
