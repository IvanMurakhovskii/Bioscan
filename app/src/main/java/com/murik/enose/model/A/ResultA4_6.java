package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA4_6 extends BaseResult {


  public ResultA4_6(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A4_6");
  }

  @Override
  public void setResult() {
    if(getA() < 4.8){
      setColorYELLOW();
    } else if(getA() >= 4.8 && getA() <= 5.8){
      if(isPractice()){
        setColorCRIMSON();
        setPossibleReasons(R.string.A4_6_CRIMSON);
      } else {
        setColorBURGUNDY();
        setPossibleReasons(R.string.A4_6_BURGUNDY);
      }

    }
  }

}
