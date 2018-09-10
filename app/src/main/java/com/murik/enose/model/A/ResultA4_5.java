package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA4_5 extends BaseResult {


  public ResultA4_5(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A4_5");
  }

  @Override
  public void setResult() {
    if(getA() >= 1.4 && getA() <= 1.9){
      setColorBLUE();
    } else if(getA() >= 2 && getA() < 2.5){
      setColorGREEN();
    } else if(getA() >= 2.5 && getA() <= 2.8){
      setColorYELLOW();
      setPossibleReasons(R.string.A4_5_YELLOW);
    } else if(getA() > 2.8){
      setColorRED();
      setPossibleReasons(R.string.A4_5_RED);
    } else if(getA() >= 1.4 && getA() <= 1.9){
      setColorCRIMSON();
      setPossibleReasons(R.string.A4_5_CRIMSON);
    }
  }

}
