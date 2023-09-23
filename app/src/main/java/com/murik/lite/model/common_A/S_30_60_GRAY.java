package com.murik.lite.model.common_A;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultWithCoefficient;

public class S_30_60_GRAY extends BaseResultWithCoefficient {


    public S_30_60_GRAY(double A, DataByMaxParcelable inputData, Context context, float coefficicent) {
        super(A, inputData, context, coefficicent);
        setLegend("S(30/60)G");
    }

    public void setResult() {
        setColorGRAY();
        if (getA() >= 0.050 && getA() <= 0.114) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_30_60_GRAY_1));
        } else if (getA() >= 0.114 && getA() <= 0.18) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_30_60_GRAY_2));
        }  else if (getA() >= 0.37 && getA() <= 0.41) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_30_60_GRAY_3));
        }

    }
}
