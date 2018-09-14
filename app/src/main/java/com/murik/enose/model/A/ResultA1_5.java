package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA1_5 extends BaseResult {


  public ResultA1_5(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A1_5");
  }

  @Override
  public void setResult() {
    if(getA() > 0.95){
      setColorGREEN();
    } else if((getA() >= 0.90 && getA() <= 0.94)){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A1_5_YELLOW));
    } else if(getA() < 0.9 && getA() > 0.81){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_5_RED));
    } else if(getA() <= 0.6){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A1_5_BURGUNDY));
    }
  }
}
