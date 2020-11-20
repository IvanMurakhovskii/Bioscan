package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First2_4_Gray extends BaseResultFirst {


    public ResultA_First2_4_Gray(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VIII_G");
    }

    public void setResult() {
        setColorGRAY();
        if (getA() >= 2 && getA() <= 2.5) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_1));
        }else if (getA() >= 1.1 && getA() <= 1.7) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_2));
        } else if (getA() >= 0.96 && getA() <= 1) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_3));
        } else if (getA() >= 0.83 && getA() <= 0.95) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_4));
        } else if (getA() >= 0.66 && getA() <= 0.71) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_5));
        } else if (getA() >= 0.41 && getA() <= 0.62) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_6));
        } else if (getA() >= 0.1 && getA() <= 0.4) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_7));
        }
    }
}
