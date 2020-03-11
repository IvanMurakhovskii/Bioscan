package com.murik.enose.model.A_Second;

import android.content.Context;

import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA_Second5_4 extends BaseResult {


  public ResultA_Second5_4(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("5_4");
  }

    public void setResult() {
    setColorYELLOW();
    setPossibleReasons("result");
    }
}
