package com.murik.enose.model;

import android.content.Context;

import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.AreaDifference;

public class AreaDifferenceTotal extends AreaDifference {

    public AreaDifferenceTotal(double areaDifference, int gender, Context context) {
        super(areaDifference, gender, context);
    }

    @Override
    public void setResult() {
        double a = Math.abs(getAreaDifference());
        if (a >= 26 && a <= 40) {
            setColorYELLOW();
        } else if (a >= 41 && a <= 50) {
            setColorRED();
        } else if (a > 50) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_BURGUNDY_ENERGY));
        }
    }
}
