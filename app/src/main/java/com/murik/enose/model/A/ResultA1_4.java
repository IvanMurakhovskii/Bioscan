package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA1_4 extends BaseResult {


  public ResultA1_4(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A1_4");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.43){
      setColorGREEN();
    } else if(getA() >= 0.38 && getA() < 0.43){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_4_RED));
    } else if(getA() <= 0.3){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_4_RED_EXTRA));
    } else if(getA() >= 0.7){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A1_4_BURGUNDY));
    }
  }
}
