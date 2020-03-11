package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_First1_5 extends BaseResult {


    public ResultA_First1_5(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("1_5");
    }

    public void setResult() {
        /*if (getA() <= 0 || getA() > 3) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_GRAY_1));
        } else if (getA() >= 0.1 && getA() <= 0.30) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_GRAY_2));
        }else if (getA() >= 0.13 && getA() <= 0.30) {
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
        }else */
        if (getA() >= 0.6 && getA() <= 0.8) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_YELLOW));
        } else if (getA() >= 1.4 && getA() <= 2.0) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_RED));
        } else if (getA() >= 2.1 && getA() <= 50.0) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_RED_2));
        } else if (getA() >=0.1 && getA() <= 0.59) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_1_5_BLUE));
        }else if (getA() >=0.4 && getA() <= 0.75) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.voter));
        }
    }
}
