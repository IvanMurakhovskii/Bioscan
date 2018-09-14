package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA6_7 extends BaseResult {


  public ResultA6_7(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A6_7");
  }

  @Override
  public void setResult() {
    if(getA() < 0.85){
      setColorGREEN();
    } else if(getA() >= 0.85 && getA() < 0.9){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.YELLOW) + "\n" + getResources(R.string.A6_7_RED));
    } else if(getA() >= 0.9){
      setColorRED();
      setPossibleReasons(getResources(R.string.A6_7_RED));
    }
  }
}
