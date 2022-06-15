package com.murik.enose.model.A_animals;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultWithCoefficient;

public class A_20_30_Animals extends BaseResultWithCoefficient {


    public A_20_30_Animals(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("V");
    }

    public A_20_30_Animals(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
         if (getA() >= 0.56 && getA() <= 0.63) {
            setColorGREEN();
        } else if (getA() >= 0.64 && getA() <= 0.75) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_30_YELLOW_1_1_ANIMALS));
        }  else if(getA() >= 0.50 && getA() <= 0.57) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_30_YELLOW_3_ANIMALS));
        } else if(getA() >= 0.27 && getA() <= 0.49) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_30_RED_1_ANIMALS));
        } else if(getA() >= 0.76 && getA() <= 0.95) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_30_RED_2_ANIMALS));
        } else if(getA() >= 0.95) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_20_30_BURGUNDY_ANIMALS));
        } else {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A_20_30_IF_NOT_IN_RANGE));
        }
    }
}
