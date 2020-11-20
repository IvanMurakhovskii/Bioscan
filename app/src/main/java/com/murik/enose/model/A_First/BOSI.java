package com.murik.enose.model.A_First;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

import lombok.var;

public class BOSI extends BaseResult {


    public BOSI(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("B.O.S.I");
    }

    public void setResult() {

        var result = getA();

        setColor(Color.WHITE);

        if (result >= 0.55 && result <= 0.8) {
            setColorPINK();
            setPossibleReasons(getResources(R.string.A_SHORT_BOSI_1));
        } else if (result >= 0.9 && result <= 1.25) {
            setColorPINK();
            setPossibleReasons(getResources(R.string.A_SHORT_BOSI_2));
        } else if (result >= 1.3 && result <= 1.49) {
            setColorPINK();
            setPossibleReasons(getResources(R.string.A_SHORT_BOSI_3));
        } else if (result >= 1.6 && result <= 1.7) {
            setColorPINK();
            setPossibleReasons(getResources(R.string.A_SHORT_BOSI_4));
        } else if (result >= 1.8 && result <= 2) {
            setColorPINK();
            setPossibleReasons(getResources(R.string.A_SHORT_BOSI_5));
        }
    }
}
