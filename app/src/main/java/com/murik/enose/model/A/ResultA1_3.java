package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA1_3 extends BaseResult {


  public ResultA1_3(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A1_3");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.7 && getA() <= 0.84){
      setColorBLUE();
    } else if(getA() >= 0.85 && getA() <= 1.3){
      setColorGREEN();
    } else if(getA() >= 0.65 && getA() <= 0.75){
      setColorYELLOW();
    } else if(getA() >= 0.6 && getA() <= 0.64){
      setColorRED();
      setPossibleReasons(R.string.A1_3_RED);
    } else if(getA() < 0.6){
      setColorBURGUNDY();
      setPossibleReasons(R.string.A1_3_BURGUNDY);
    } else if(getA() >= 1.23 && getA() <= 1.35){
      setColorCRIMSON();
      setPossibleReasons(R.string.A1_3_CRIMSON);
    }
  }

}
