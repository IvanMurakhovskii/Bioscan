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
        double a = getAreaDifference();
        if (Math.abs(a) >= 21 && Math.abs(a) <= 49) {
            setColorYELLOW();
            if (a > 0) {
                setResultIfDifferenceGrateZero();
            } else {
                setResultIfDifferenceLessZero();
            }
        } else if (a > 50) {
            setColorRED();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_RED_TOTAL));
        } else if (a < -50) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_BURGUNDY_TOTAL));
        }
    }

    private void setResultIfDifferenceGrateZero() {
        if (getGender() == Const.GENDER_MALE) {
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_ABOVE_YELLOW_MALE));
        } else {
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_ABOVE_YELLOW_FEMALE));
        }
    }

    private void setResultIfDifferenceLessZero() {
        if (getGender() == Const.GENDER_MALE) {
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_LESS_YELLOW_MALE));
        } else {
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_LESS_YELLOW_FEMALE));
        }
    }
}
