package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_First2_3_Gray extends BaseResult {


    public ResultA_First2_3_Gray(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("2_3G");
    }

    public void setResult() {
        if (getA() >= 0.30 && getA() <= 0.90) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_1));
        } else if (getA() >= 0.9 && getA() <= 1.7) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_2));
        } else if (getA() >= 2 && getA() <= 6) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_3));
        } else if (getA() >=0.1 && getA() <=0.2) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_3));
        }
    }
}
