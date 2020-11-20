package com.murik.enose.model.A_Second;

import android.content.Context;

import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultSecond;

public class ResultA_Second1_5 extends BaseResultSecond {


  public ResultA_Second1_5(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
    super(A, inputData, context, coefficient);
    setLegend("1_5");
  }

    public void setResult() {
    setColorYELLOW();
    setPossibleReasons("result");
       /* if (getA() >= 0.9 && getA() <= 1.14 && getInputData().isPractice()) {
                setColorCRIMSON();
                setPossibleReasons("Измерениееее");
        } else if (getA() >= 0.9 && getA() <= 1.14) {
            setColorYELLOW();
            setPossibleReasons("Измерениееее");
        } else if (getA() > 1.3 && getA() < 1.7) {
            setColorBLUE();
            setPossibleReasons("");
        } else if (getA() > 1.1 && getA() <= 2) {
            setColorGREEN();
        } else if (getA() < 0.9) {
            setColorRED();
            setPossibleReasons("");
        } else if (getA() > 2) {
            setColorBURGUNDY();
            setPossibleReasons("");
        } else if (getA() > 1.5 && getA() <= 1.9) {
            setColorRED();
            setPossibleReasons("");
        } else if (getA() > 0.30 && getA() <= 0.40) {
            setColorGRAY();
            setPossibleReasons("");
        }*/
    }
}
