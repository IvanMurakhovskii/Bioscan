package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA7_8 extends BaseResult {


  public ResultA7_8(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A7_8");
  }

  @Override
  public void setResult() {
    if(getA() == 0.7){
      setColorBLUE();
      setPossibleReasons(R.string.A7_8_BLUE);
    } else if(getA() < 0.25){
      setColorGREEN();
    } else if(getA() >= 0.1 && getA() <= 0.2){
      setColorYELLOW();
      setPossibleReasons(R.string.A7_8_YELLOW);
    } else if(getA() >= 0.25 && getA() <= 0.35){
      setColorRED();
      setPossibleReasons(R.string.A7_8_RED);
    } else if(getA() >= 0.4){
      setColorBURGUNDY();
      setPossibleReasons(R.string.A7_8_BURGUNDY);
    }
  }
}
