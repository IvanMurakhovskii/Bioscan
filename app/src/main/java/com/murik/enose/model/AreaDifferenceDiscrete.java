package com.murik.enose.model;

import android.content.Context;

import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.AreaDifference;

public class AreaDifferenceDiscrete extends AreaDifference {

    public AreaDifferenceDiscrete(double areaDifference, int gender, Context context) {
        super(areaDifference, gender, context);
    }

    @Override
    public void setResult() {
        double a = getAreaDifference();
        if (Math.abs(a) >= 21 && Math.abs(a) <= 40) {
            setColorYELLOW();
            if (a > 0) {
                setResultIfDifferenceGrateZero();
            } else {
                setResultIfDifferenceLessZero();
            }
        } else if(Math.abs(a) >= 41 && Math.abs(a) <= 60) {
            setColorRED();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_RED));
        } else if (Math.abs(a) >= 61 && Math.abs(a) <= 81) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_BURGUNDY));
        } else if(Math.abs(a) > 80) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_CRIMSON));
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
