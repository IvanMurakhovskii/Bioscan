package com.murik.lite.model.A_First;

import android.content.Context;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First2_3_Gray extends BaseResultFirst {


    public ResultA_First2_3_Gray(double A, DataByMaxParcelable inputData, Context context,float coefficient) {
        super(A, inputData, context, coefficient);
        setLegend("I_G");
    }

    public void setResult() {
        setColorGRAY();

        setColorGRAY();
        setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_3));

//        if (getA() >=0.1 && getA() <=0.2) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_3));
//        } else if (getA() >=0.75 && getA() <=0.89) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_4));
//        } else if (getA() >= 0.9 && getA() <= 1.7) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_2));
//        } else if (getA() >= 2.0 && getA() <= 2.4) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_5));
//        } else if (getA() >= 2.5 && getA() <= 6) {
//            setColorGRAY();
//            setPossibleReasons(getResources(R.string.A_SHORT_2_3_GRAY_6));
//        }
    }
}
