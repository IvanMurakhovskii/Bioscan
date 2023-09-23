package com.murik.lite.model.A_First;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First2_5_Gray extends BaseResultFirst {


    public ResultA_First2_5_Gray(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("VII_G");
    }

    public void setResult() {
        setColorGRAY();
      if (getA() >= 0) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_1));
        } else if (getA() <= 1 && getA() >= 0) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_0));
        } else if (getA() >= 1.1 && getA() <= 1.4) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_2));
        } else if (getA() >= 0.66 && getA() <= 1.0) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_7));
        } else if (getA() >= 0.5 && getA() <= 0.65) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_8));
        } else if (getA() >= 0.08 && getA() <= 0.36) {
           setColorGRAY();
           setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_10));
       } else if (getA() >= 0.37 && getA() <= 0.49) {
           setColorGRAY();
           setPossibleReasons(getResources(R.string.A_SHORT_2_5_GRAY_11));
       }
    }
}
