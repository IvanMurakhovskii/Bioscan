package com.murik.lite.model.additional;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class Additional_Y extends BaseResult {


    public Additional_Y(double A, Context context) {
        super(A, null, context);
    }

    public Additional_Y(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.85 && getA() <= 1.2) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.ADDITIONAL_Y_GREEN));
        } else if (getA() > 0.6 && getA() < 0.85) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.ADDITIONAL_Y_YELLOW_1));
        } else if(getA() > 1.2 && getA() <= 1.45) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.ADDITIONAL_Y_YELLOW_2));
        } else if(getA() > 1.45 && getA() <= 2) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.ADDITIONAL_Y_ORANGE_1));
        } else if(getA() > 0.5 && getA() <= 0.78) {
            setColorORANGE();
        } else if(getA() > 2 && getA() <= 5) {
            setColorRED();
            setPossibleReasons(getResources(R.string.ADDITIONAL_Y_RED_1));
        }
    }
}
