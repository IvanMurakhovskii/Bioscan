package com.murik.enose.model.Е;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

import lombok.val;

public class E_2_80 extends BaseResult {


    public E_2_80(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("E2");
    }

    @Override
    public void setResult() {
        val a = Math.abs(getA());

        if (a >= 0.24 && a <= 0.31) {
            setColorGREEN();
        } else if (a >= 0.32 && a <= 0.38) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_2_80_YELLOW));
        } else if (a >= 0.20 && a <= 0.23) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.E_2_80_ORANGE_1));
        } else if (a >= 0.45 && a <= 0.53) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.E_2_80_ORANGE_2));
        } else if (a >= 0.39 && a <= 0.46) {
            setColorRED();
            setPossibleReasons(getResources(R.string.E_2_80_RED));
        } else if (a >= 0.16 && a <= 0.19) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.E_2_80_BURGUNDY));
        } else if (a < 0.16) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_2_80_WHITE_1));
        } else if (a > 0.75) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.E_2_80_WHITE_2));
        }
    }
}
