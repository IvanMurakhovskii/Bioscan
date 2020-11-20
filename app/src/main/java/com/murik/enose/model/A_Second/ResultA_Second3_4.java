package com.murik.enose.model.A_Second;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultSecond;

public class ResultA_Second3_4 extends BaseResultSecond {


  public ResultA_Second3_4(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
    super(A, inputData, context, coefficient);
    setLegend("XI");
  }

    public void setResult() {
      if (getA() >= 0.35 && getA() <= 0.40) {
        setColorYELLOW();
        setPossibleReasons(getResources(R.string.A_LONG1_1_3_YELLOW_1));
      } else if (getA() >= 0.15 && getA() <= 0.22) {
        setColorYELLOW();
        setPossibleReasons(getResources(R.string.A_LONG1_1_3_YELLOW_2));
      } else if (getA() <= 0.15) {
        setColorRED();
        setPossibleReasons(getResources(R.string.A_LONG1_1_3_RED));
      }
    }
}
