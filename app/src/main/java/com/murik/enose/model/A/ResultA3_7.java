package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA3_7 extends BaseResult {


  public ResultA3_7(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A3_7");
  }

  @Override
  public void setResult() {
    if(getA() >= 1.8 && getA() <= 1.9){
      setColorBLUE();
    } else if(getA() < 1.8){
      setColorGREEN();
    } else if(getA() == 1){
      setColorRED();
      setPossibleReasons(R.string.A3_7_RED);
    } else if(getA() > 2.4){
      setColorBURGUNDY();
      setPossibleReasons(R.string.A3_7_BURGUNDY);
    }
  }
}
