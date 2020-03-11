package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

import lombok.var;

public class SI_Gray extends BaseResult {


    public SI_Gray(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("S.I_G");
    }

    public void setResult() {

        var result = getA();

        if (result >= 0.55 && result <= 0.80) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_PINK_1));
        } else if (result >= 0.9 && result <= 1.25) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_PINK_2));
        } else if (result >= 1.3 && result <= 1.49) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_PINK_3));
        } else if (result >= 1.6 && result <= 1.7) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_PINK_4));
        } else if (result >= 1.8 && result <= 2) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_PINK_5));
        }
    }
}
