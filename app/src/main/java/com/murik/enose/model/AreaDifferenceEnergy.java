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
        if (a >= 26 && a <= 40) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_YELLOW_ENERGY));
        } else if(a >= 41 && a <= 50) {
            setColorRED();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_RED_ENERGY));
        } else if (a >= 50) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_BURGUNDY_1_ENERGY));
        }
//        else if(a > 80) {
//            setColorCRIMSON();
//            setPossibleReasons(getResources(R.string.DIFFERENCE_AREA_CRIMSON));
//        }

    }
}
