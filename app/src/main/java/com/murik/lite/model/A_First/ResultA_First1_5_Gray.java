package com.murik.lite.model.A_First;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First1_5_Gray extends BaseResultFirst {


    public ResultA_First1_5_Gray(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("1_5G");
    }

    public void setResult() {
        if (getA() <= 0 || getA() > 3) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_GRAY_1));
        } else if (getA() >= 0.1 && getA() <= 0.30) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_GRAY_2));
        } else if (getA() >= 0.35 && getA() <= 0.38) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_GRAY_3));
        } else if (getA() >= 0.40 && getA() <= 0.47) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_GRAY_4));
        } else if (getA() >= 0.50 && getA() <= 1.0) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_GRAY_5));
        } else if (getA() >= 1.1 && getA() <= 2.5) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_GRAY_6));
        }
    }
}
