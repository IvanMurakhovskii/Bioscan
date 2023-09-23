package com.murik.lite.model.Е;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

import lombok.val;

public class E_2_DIAGNOST extends BaseResult {


    public E_2_DIAGNOST(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("E2");
    }

    @Override
    public void setResult() {
        val a = getA();

        if (a >= 0.28 && a <= 0.33) {
            setColorGREEN();
        } else if (a >= 0.34 && a <= 0.38) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_YELLOW));
        } else if (a >= 0.20 && a <= 0.27) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_ORANGE_1));
        } else if (a >= 0.39 && a <= 0.46) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_ORANGE_2));
        } else if (a >= 0.46 && a <= 0.48) {
            setColorRED();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_RED));
        } else if (a >= 0.16 && a <= 0.19) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_BURGUNDY_1));
        } else if (a >= 0.49 && a <= 0.57) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_BURGUNDY_2));
        } else if (a < 0.16) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_WHITE_1));
        } else if (a > 0.58) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_2_DIAGNOST_WHITE_2));
        }
    }
}
