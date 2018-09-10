package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA2_3 extends BaseResult{


  public ResultA2_3(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A2_3");
  }

  @Override
  public void setResult() {
    if(getA() > 0.5){
      setColorGREEN();
    } else if(getA() > 0.5 && getA() <= 0.6){
      setColorYELLOW();
      setPossibleReasons(R.string.A2_3_YELLOW);
    } if (getA() == 0.5) {
      setColorBLUE();
      setPossibleReasons(R.string.A2_3_BLUE);
    }
  }

}
