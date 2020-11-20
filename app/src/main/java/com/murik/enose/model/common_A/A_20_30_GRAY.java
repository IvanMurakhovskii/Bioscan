package com.murik.enose.model.common_A;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultWithCoefficient;

public class A_20_30_GRAY extends BaseResultWithCoefficient {


    public A_20_30_GRAY(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("V-G");
    }

    public A_20_30_GRAY(double A, DataByMaxParcelable inputData, Context context, String legend) {
        super(A, inputData, context, 1);
        setLegend(legend);
    }

    public void setResult() {
        setColorGRAY();

        if (getA() >= 0.18 && getA() <= 0.22) {
            setPossibleReasons("этанол");
        } else if(getA() >= 0.68 && getA() <= 0.72) {
            setPossibleReasons("ацетон");
        } else if(getA() >= 0.27 && getA() <= 0.32) {
            setPossibleReasons("вода");
        } else if(getA() >= 0.42 && getA() <= 0.44) {
            setPossibleReasons("толуол");
        } else if(getA() >= 0.37 && getA() <= 0.41) {
            setPossibleReasons("хлороформ");
        } else if (getA() == 0.5) {
            setPossibleReasons("проверить прибор, сенсор");
        }
    }
}
