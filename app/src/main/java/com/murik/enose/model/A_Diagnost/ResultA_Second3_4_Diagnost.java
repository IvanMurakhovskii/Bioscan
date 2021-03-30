package com.murik.enose.model.A_Diagnost;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResultSecond;

public class ResultA_Second3_4_Diagnost extends BaseResultSecond {


  public ResultA_Second3_4_Diagnost(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
    super(A, inputData, context, coefficient);
    setLegend("XI");
  }

    public void setResult() {
      if (getA() >= 0.33 && getA() <= 0.39) {
        setColorGREEN();
      } else if (getA() >= 0.40 && getA() <= 0.50) {
        setColorYELLOW();
        setPossibleReasons(getResources(R.string.A_LONG_3_4_YELLOW_1_DIAGNOST));
      } else if (getA() >= 0.17 && getA() <= 0.32) {
        setColorYELLOW();
        setPossibleReasons(getResources(R.string.A_LONG_3_4_YELLOW_2_DIAGNOST));
      } else if (getA() <= 0.15) {
        setColorRED();
        setPossibleReasons(getResources(R.string.A_LONG_3_4_RED_DIAGNOST));
      } else if (getA() >= 0.51 && getA() <= 0.67) {
        setColorBLUE();
        setPossibleReasons(getResources(R.string.A2_5_BLUE));
      }
    }
}
