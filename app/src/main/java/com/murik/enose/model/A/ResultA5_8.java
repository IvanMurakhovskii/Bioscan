package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA5_8 extends BaseResult {


  public ResultA5_8(double A, boolean isPractice, Context context) {
    super(A, isPractice, context);
    setLegend("A5_8");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.6 && getA() <= 0.7){
      setColorBLUE();
    } else if(getA() == 1){
      setColorGREEN();
    } else if(getA() < 0.35){
      setColorYELLOW();
    } else if(getA() > 0.35 && getA() < 0.39){
      if(isPractice()){
        setColorCRIMSON();
        setPossibleReasons(R.string.A5_8_CRIMSOM);
      } else {
        setColorRED();
        setPossibleReasons(R.string.A5_8_RED);
      }

    }
  }
}
