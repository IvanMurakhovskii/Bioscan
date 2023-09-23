package com.murik.lite.model.A_First;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

import lombok.var;

public class K extends BaseResult {


    public K(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("K");
    }

    public void setResult() {
        var result = getA();

        if (result >= 0.5 && result <= 0.79) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_K_YELLOW_1));
        } else if (result >= 1.25 && result <= 1.55) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_K_YELLOW_2));
        } else if (result >= 0.3 && result <= 0.45) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_K_RED_1));
        } else if (result >= 1.6 && result <= 2.7) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_K_RED_2));
        } else if (result <= 0.3) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_K_BURGUNDY));
        }else if (result > 5) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_K_BLUE));
        } else if (result >= 2.8 && result <= 5) {
            if(getInputData().isPractice()) {
                setColorCRIMSON();
                setPossibleReasons(getResources(R.string.A_SHORT_K_CRIMSON));
            }
        }
    }
}
