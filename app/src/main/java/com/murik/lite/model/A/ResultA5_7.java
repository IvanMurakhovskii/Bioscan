package com.murik.lite.model.A;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA5_7 extends BaseResult {


    public ResultA5_7(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("5_7");
    }

    @Override
    public void setResult() {
        if (getA() < 0.4) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.A4_6_WHITE));
        } else if (getA() >= 1.8) {
            setColorGREEN();
        } else if(getA() > 1.8) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A5_7_YELLOW));
        } else if(getA() > 0.40 && getA() < 0.47){
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A5_7_GRAY2));
        } else if(getA() < 0.8){
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A5_7_GRAY));
        }
    }
}
