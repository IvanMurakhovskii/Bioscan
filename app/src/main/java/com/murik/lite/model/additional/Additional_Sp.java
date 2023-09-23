package com.murik.lite.model.additional;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class Additional_Sp extends BaseResult {


    public Additional_Sp(double A, Context context) {
        super(A, null, context);
    }

    public Additional_Sp(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.8 && getA() <= 1.2) {
            setColorGREEN();
        } else if (getA() > 0.6 && getA() < 0.8) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.ADDITIONAL_SP_YELLOW_1));
        } else if(getA() > 1.2 && getA() <= 1.45) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.ADDITIONAL_SP_YELLOW_2));
        } else if(getA() > 1.45 && getA() <= 2) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.ADDITIONAL_SP_ORANGE_1));
        } else if(getA() > 0.5 && getA() <= 0.78) {
            setColorORANGE();
        } else if(getA() > 2 && getA() <= 5) {
            setColorRED();
            setPossibleReasons(getResources(R.string.ADDITIONAL_SP_RED_1));
        }
    }
}
