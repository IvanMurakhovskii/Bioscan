package com.murik.lite.model.A_Diagnost;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First1_2_Diagnost extends BaseResultFirst {


    public ResultA_First1_2_Diagnost(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("IV");
    }

    public ResultA_First1_2_Diagnost(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if(getA() >= 0.450 && getA() <= 0.95) {
            setColorGREEN();
      /*  } else if(getA() >= 0.500 && getA() <= 0.510) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BLUE_1_DIAGNOST));
*/      } else if(getA() >= 0.496 && getA() <= 0.539) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_YELLOW_1_DIAGNOST));
        } else if(getA() >= 0.300 && getA() <= 0.440) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_YELLOW_2_DIAGNOST));
        } else if (getA() >= 0.200 && getA() <= 0.299) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_RED_1_DIAGNOST));
        } else if (getA() >= 0.540 && getA() <= 0.599) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_RED_2_DIAGNOST));
        } else if (getA() >= 0.100 && getA() <= 0.200) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BURGUNDY_1_DIAGNOST));
        } else if (getA() >= 0.660 && getA() <= 0.700) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BURGUNDY_2_DIAGNOST));
        } else if (getA() >= 0.70) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_BURGUNDY_3_DIAGNOST));
        } else if (getA() >= 0.660 && getA() <= 0.695 && getInputData().isPractice()) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_CRIMSON_DIAGNOST));
        } else if (getA() <= 0.100) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_WHITE));
        }

    }
}
