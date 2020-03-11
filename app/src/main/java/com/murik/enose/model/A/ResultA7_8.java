package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA7_8 extends BaseResult {


  public ResultA7_8(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("7_8");
  }

  @Override
  public void setResult() {
    if(getA() == 0.7){
      setColorBLUE();
    } else if(getA() < 0.25 && getA() > 0.2){
      setColorGREEN();
    } else if(getA() >= 0.1 && getA() <= 0.2){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A7_8_YELLOW));
    } else if(getA() >= 0.25 && getA() <= 0.35){
      setColorRED();
      setPossibleReasons(getResources(R.string.A7_8_RED));
    } else if(getA() >= 0.3){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A7_8_BURGUNDY));
    }
  }
}
