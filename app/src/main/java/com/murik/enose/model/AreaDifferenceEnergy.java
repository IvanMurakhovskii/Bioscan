package com.murik.enose.model;

import android.content.Context;

import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.AreaDifference;

public class AreaDifferenceEnergy extends AreaDifference {

    public AreaDifferenceEnergy(double areaDifference, int gender, Context context) {
        super(areaDifference, gender, context);
    }

    @Override
    public void setResult() {
        double a = Math.abs(getAreaDifference());
        if (a >= 21 && a <= 40) {
            if (getAreaDifference() > 0) {
                setResultIfDifferenceGrateZero();
            } else {
                setResultIfDifferenceLessZero();
            }
        } else if(a >= 41 && a <= 60) {
            setColorRED();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_RED));
        } else if (a >= 61 && a <= 81) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_BURGUNDY));
        } else if(a > 80) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_CRIMSON));
        }

    }

    private void setResultIfDifferenceGrateZero() {
        setColorYELLOW();
        if (getGender() == Const.GENDER_MALE) {
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_ABOVE_YELLOW_MALE));
        } else {
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_ABOVE_YELLOW_FEMALE));
        }
    }

    private void setResultIfDifferenceLessZero() {
        setColorYELLOW();
        if (getGender() == Const.GENDER_MALE) {
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_LESS_YELLOW_MALE));
        } else {
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_LESS_YELLOW_FEMALE));
        }
    }
}
