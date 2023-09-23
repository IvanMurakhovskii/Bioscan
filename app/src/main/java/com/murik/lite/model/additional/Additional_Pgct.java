package com.murik.lite.model.additional;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class Additional_Pgct extends BaseResult {


    public Additional_Pgct(double A, Context context) {
        super(A, null, context);
    }

    public Additional_Pgct(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 3 && getA() <= 3.8) {
            setColorGREEN();
        } else if (getA() > 3.80 && getA() <= 5) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.ADDITIONAL_PGCT_YELLOW_1));
        } else if(getA() > 2.36 && getA() < 3) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.ADDITIONAL_PGCT_YELLOW_2));
        } else if(getA() > 5 && getA() <= 8) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.ADDITIONAL_PGCT_ORANGE_1));
        } else if(getA() >= 2 && getA() <= 2.35) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.ADDITIONAL_PGCT_ORANGE_2));
        } else if(getA() > 8) {
            setColorRED();
            setPossibleReasons(getResources(R.string.ADDITIONAL_PGCT_RED_1));
        } else if(getA() < 2) {
            setColorRED();
            setPossibleReasons(getResources(R.string.ADDITIONAL_PS_RED_2));
        }
    }
}
