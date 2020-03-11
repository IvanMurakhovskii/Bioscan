package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_First2_5_Gray extends BaseResult {


    public ResultA_First2_5_Gray(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("2_5G");
    }

    public void setResult() {
        setColorYELLOW();
        setPossibleReasons("result");
       if (getA() >=2.7 && getA() <= 4.9) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_RED_2));
        } else if (getA() <= 0) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_1));
        } else if (getA() <= 1 && getA() >= 0) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_0));
        } else if (getA() >= 0.7 && getA() <= 0.9) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_2));
        } else if (getA() >= 1.0 && getA() <= 1.4) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_7));
        } else if (getA() == 1) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_3));
        } else if (getA() == 1.2) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_4));
        } else if (getA() >=1.5 && getA() <= 2) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_5));
        }else if (getA() >= 2.1 && getA() <= 2.7) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_8));
        } else if (getA() >= 2.8 && getA() <= 12) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_6));
        }
    }
}
