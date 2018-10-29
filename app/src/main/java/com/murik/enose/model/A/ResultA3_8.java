package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA3_8 extends BaseResult {

  public ResultA3_8(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("3_8");
  }

  @Override
  public void setResult() {
    if(getA() >=1 && getA() <= 1.1){
      setColorBLUE();
    } else if(getA() < 0.5){
      setColorGREEN();
    } else if(getA() >= 0.54 && getA() <= 0.59){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.YELLOW) + "\n" +getResources(R.string.A3_8_RED));
    } else if(getA() >= 0.6 && getA() <= 0.74){
      setColorRED();
      setPossibleReasons(getResources(R.string.A3_8_RED));
    } else if(getA() > 0.74){
      setColorBURGUNDY();
    }
  }

}
