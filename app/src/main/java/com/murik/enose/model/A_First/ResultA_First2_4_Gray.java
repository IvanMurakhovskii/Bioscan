package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_First2_4_Gray extends BaseResult {


    public ResultA_First2_4_Gray(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("2_4G");
    }

    public void setResult() {
        if (getA() >= 0.4 && getA() <= 0.5) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_1));
        }else if (getA() >= 0.6 && getA() <= 0.9) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_2));
        } else if (getA() == 1) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_3));
        } else if (getA() == 1.2) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_4));
        } else if (getA() >= 1.1 && getA() <= 1.19) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_5));
        } else if (getA() >= 2.5 && getA() <= 10) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_6));
        } else if (getA() >= 0.95 && getA() <= 1.04) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_7));
        } else if (getA() >= 1.05 && getA() <= 1.2) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_8));
        } else if (getA() >= 1.4 && getA() <= 1.5) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_9));
        } else if (getA() >= 1.6 && getA() <= 2.4) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_4_GRAY_10));
        }
    }
}
