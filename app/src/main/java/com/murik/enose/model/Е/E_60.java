package com.murik.enose.model.Е;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class E_60 extends BaseResult {


    public E_60(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("En");
    }

    public void setResult() {
        if (getA() >= 0.19 && getA() <= 0.40) {
            setColorGREEN();
        } else if (getA() >= 0.14 && getA() <= 0.18) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_YELLOW));
        } else if (getA() >= 0.10 && getA() <= 0.13) {
            setColorRED();
            setPossibleReasons(getResources(R.string.E_RED));
        } else if (getA() >= 0.039 && getA() <= 0.095) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_BURGUNDY_1));
        } else if (getA() >= 0.40) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_BURGUNDY_2));
        } else if (getA() <= 0.038) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_WHITE));
        }
    }
}
