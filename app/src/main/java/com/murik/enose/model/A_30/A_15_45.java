package com.murik.enose.model.A_30;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class A_15_45 extends BaseResultFirst {


    public A_15_45(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("III");
    }

    public void setResult() {
        if(getA() >= 0.70 && getA() <= 0.76){
            setColorGREEN();
        } else if(getA() >= 0.35 && getA() <= 0.55) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.S30_15_45_YELLOW));
        } else if(getA() >= 0.56 && getA() <= 0.66) {
            setColorRED();
            setPossibleReasons(getResources(R.string.S30_15_45_RED_1));
        } else if (getA() <= 0) {
            setColor(Color.WHITE);
            setPossibleReasons("Сенсор не готов!");
        }
    }
}
