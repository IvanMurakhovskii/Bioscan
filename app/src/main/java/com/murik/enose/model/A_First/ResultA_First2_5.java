package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_First2_5 extends BaseResult {


    public ResultA_First2_5(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("2_5");
    }

    public void setResult() {
        setColorYELLOW();
        setPossibleReasons("result");
        if (getA() >= 1 && getA() <= 1.3) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_RED));
        } else if (getA() >=2.7 && getA() <= 4.9) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_RED_2));
        } else if (getA() >=2 && getA() <= 2.6) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_YELLOW));
        } else if (getA() >=5 && getA() <= 12) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A_SHORT_2_5_BLUE));
        }else if (getA() <= 1) {
            if(getInputData().isPractice()) {
                setColorCRIMSON();
                setPossibleReasons(getResources(R.string.A_SHORT_2_5_CRIMSON));
            }
        }
    }
}
