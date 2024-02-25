package com.murik.lite.model.Е;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class E_2_60_GREY extends BaseResult {


    public E_2_60_GREY(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("E2_G");
    }

    @Override
    public void setResult() {
        setColorGRAY();
        setPossibleReasons(getResources(R.string.E_2_GRAY_1));
//
//        if (getA() < 0.20 && getA() > 0.75) {
//            setColor(Color.WHITE);
//            setPossibleReasons(getResources(R.string.E_2_WHITE));
//        } else if (getA() < 0.47 && getA() > 0.51) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.E_2_GRAY_1));
//        } else if (getA() == 1.1) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.E_2_GRAY_2));
//        } else if (getA() >= 0.22 && getA() <= 0.26) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.E_2_GRAY_3));
//        } else if (getA() >= 0.60 && getA() <= 0.64) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.E_2_GRAY_4));
//        } else if (getA() >= 0.43 && getA() <= 0.52) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.E_2_GRAY_5));
//        } else if (getA() >= 0.37 && getA() <= 0.40) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.E_2_GRAY_6));
//        }

    }
}
