package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA2_6 extends BaseResult {


  public ResultA2_6(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A2_6");
  }

  @Override
  public void setResult() {
    if(getA() < 1.4){
      setColorGREEN();
    } else if(getA() == 1.4){
      setColorYELLOW();
    } else if(getA() > 1.4 && getA() < 1.7){
      setColorRED();
      setPossibleReasons(R.string.A2_6_RED);
    } else if(getA() >= 1.7){
      if(isPractice()){
        setColorCRIMSON();
        setPossibleReasons(R.string.A2_6_CRIMSON);
      } else {
        setColorBURGUNDY();
        setPossibleReasons(R.string.A2_6_BURGUNDY);
      }

    }
  }
}
