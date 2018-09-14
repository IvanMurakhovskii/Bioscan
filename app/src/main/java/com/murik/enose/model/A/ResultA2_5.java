package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA2_5 extends BaseResult {


  public ResultA2_5(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A2_5");
  }

  @Override
  public void setResult() {
    if(getA() > 0.52){
      setColorGREEN();
    } else if(getA() <= 0.52 && getA() >= 0.48){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.YELLOW) + "\n" + getResources(R.string.A2_5_RED));
    }else if(getA() < 0.48){
      setColorRED();
      setPossibleReasons(getResources(R.string.A2_5_RED));
    }
  }
}
