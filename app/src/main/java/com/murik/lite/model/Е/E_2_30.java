package com.murik.lite.model.Е;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

import lombok.val;

public class E_2_30 extends BaseResult {


    public E_2_30(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("E2");
    }

    @Override
    public void setResult() {
        val a = getA();
        if (a >= 0.34 && a <= 0.37) {
            setColorGREEN();
            setPossibleReasons("Жарко");
        } else if (a >= 0.38 && a <= 0.43) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.E_2_30_YELLOW));
        }
    }
}
