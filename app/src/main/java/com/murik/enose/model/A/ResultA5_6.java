package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA5_6 extends BaseResult {


  public ResultA5_6(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A5_6");
  }

  @Override
  public void setResult() {
    if(getA() < 1.2){
      setColorYELLOW();
    } else if(getA() >= 1.2 && getA() <= 1.5){
      setColorRED();
      setPossibleReasons(R.string.A5_6_RED);
    } else if(getA() > 1.5 && getA() < 1.8){
      setColorBURGUNDY();
      setPossibleReasons(R.string.A5_6_BURGUNDY);
    } else if(getA() > 1.8 && getA() <2.2){
      setColorCRIMSON();
      setPossibleReasons(R.string.A5_6_CRIMSON);
    }
  }
}
