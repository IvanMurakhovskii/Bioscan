package com.murik.enose.model.Е;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class E_2_60 extends BaseResult {


    public E_2_60(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("E2");
    }

    @Override
    public void setResult() {
        if(getA() >= 0.43 && getA() <= 0.49) {
            setColorGREEN();
        } else if (getA() >= 0.50 && getA() <= 0.52) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.E_2_GREEN));
        } else if (getA() >= 0.53 && getA() <= 0.56) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_2_YELLOW_1));
        } else if (getA() >= 0.35 && getA() <= 0.42) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_2_YELLOW_2));
        } else if (getA() >= 0.57 && getA() <= 0.62) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.E_2_ORANGE_1));
        } else if (getA() >= 0.63 && getA() <= 0.71) {
            setColorRED();
            setPossibleReasons(getResources(R.string.E_2_RED));
        } else if (getA() >= 0.20 && getA() <= 0.34) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_2_BURGUNDY_1));
        } else if (getA() >= 0.72 && getA() <= 0.74) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_2_BURGUNDY_2));
        } else if (getA() < 0.20 && getA() > 0.75) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_2_WHITE));
        }
    }
}
