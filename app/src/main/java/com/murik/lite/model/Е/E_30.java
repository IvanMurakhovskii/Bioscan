package com.murik.lite.model.Е;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class E_30 extends BaseResult {


    public E_30(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("En");
    }

    public void setResult() {
        if (getA() >= 0.50 && getA() <= 0.80) {
            setColorGREEN();
        } else if (getA() >= 0.41 && getA() <= 0.49) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_YELLOW));
        } else if (getA() >= 0.30 && getA() <= 0.40) {
            setColorRED();
            setPossibleReasons(getResources(R.string.E_RED));
        } else if (getA() >= 0.15 && getA() <= 0.29) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_BURGUNDY_1));
        } else if (getA() >= 0.8) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_BURGUNDY_2));
        } else if (getA() <= 0.0389) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_WHITE));
        }
    }
}
