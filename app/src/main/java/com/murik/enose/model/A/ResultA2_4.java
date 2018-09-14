package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA2_4 extends BaseResult {


  public ResultA2_4(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A2_4");
  }

  @Override
  public void setResult() {
    if(getA() > 0.3){
      setColorGREEN();
    } else if(getA() >= 0.25 && getA() <= 0.3){
      setColorRED();
      setPossibleReasons(getResources(R.string.A2_4_RED));
    } else if(getA() >= 0.16 && getA() <= 0.24){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A2_4_BURGUNDY));
    }
  }
}
