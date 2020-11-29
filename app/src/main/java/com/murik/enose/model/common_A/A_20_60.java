package com.murik.enose.model.common_A;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultWithCoefficient;

public class A_20_60 extends BaseResultWithCoefficient {


    public A_20_60(double A, DataByMaxParcelable inputData, Context context, float coefficicent) {
        super(A, inputData, context, coefficicent);
        setLegend("IX");
    }

    public A_20_60(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 0);
        setLegend(legend);
    }

    public void setResult() {
        if (getA() >= 0.3 && getA() <= 0.35) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_20_60_YELLOW));
        } else if(getA() >= 0.4 && getA() <= 0.5) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_20_60_RED));
        }
    }
}
