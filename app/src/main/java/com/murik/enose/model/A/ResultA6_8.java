package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA6_8 extends BaseResult {

  public ResultA6_8(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("6_8");
  }

  @Override
  public void setResult() {
    if(getA() > 0.29 && getA() <= 0.32){
      setColorBLUE();
      setPossibleReasons(getResources(R.string.GIGROMETR) + "/n" + getResources(R.string.A7_8_BURGUNDY));
    } else if(getA() < 0.28){
      setColorGREEN();
    } else if(getA() >  0.28 && getA() < 0.294){
      setColorRED();
      setPossibleReasons(getResources(R.string.A6_8_RED));
    }
  }
}
