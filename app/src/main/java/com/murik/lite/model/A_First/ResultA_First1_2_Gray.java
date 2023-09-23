package com.murik.lite.model.A_First;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First1_2_Gray extends BaseResultFirst {


    public ResultA_First1_2_Gray(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("IV_G");
    }

    public ResultA_First1_2_Gray(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        setColorGRAY();

        if (getA() > 0.11 && getA() < 0.25) {
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_GREY_1));
        } else if (getA() >= 0.26 && getA() <= 0.38) {
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_GREY_2));
        } else if (getA() >= 0.39 && getA() <= 0.44) {
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_GREY_3));
        } else if (getA() >= 0.45 && getA() <= 0.59) {
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_GREY_4));
        } else if (getA() >= 0.61 && getA() <= 0.70) {
           setPossibleReasons(getResources(R.string.A_SHORT_1_2_GREY_5));
        } else if (getA() >= 0.83 && getA() <= 0.88) {
            setPossibleReasons(getResources(R.string.A_SHORT_1_2_GREY_7));
        }
    }
}
