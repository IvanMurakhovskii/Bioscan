package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

import lombok.val;
import lombok.var;

public class SI extends BaseResult {


    public SI(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
        setLegend("S.I");
    }

    public void setResult() {

        var result = getA();

        if (result >= 0.7 && result <= 0.77) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_YELLOW_1));
        } else if (result >= 1.23 && result <= 1.4) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_YELLOW_2));
        } else if (result >= 0.55 && result <= 0.69) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_RED_1));
        } else if (result >= 1.41 && result <= 1.9) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_RED_2));
        } else if (result >= 0.33 && result <= 0.54) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_BURGUNDY_1));
        } else if (result >= 0.15 && result <= 0.32) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_BURGUNDY_2));
        } else if (result <= 0.15) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_BURGUNDY_3));
        } else if (result == 0) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_CRIMSON_1));
        } else if (result > 2) {
            setColorCRIMSON();
            setPossibleReasons(getResources(R.string.A_SHORT_SI_CRIMSON_2));
        } else if (getA() >= 1.21 && getA() < 1.59) {
            setColorBLUE();
        }
    }
}
