package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA3_7 extends BaseResult {


  public ResultA3_7(double A,DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("3_7");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.2 && getA() < 0.6){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A3_7_GRAY));
    } else if(getA() >= 1.04 && getA() < 1.25){
      setColorGREEN();
    } else if(getA() > 0.95 && getA() < 1.04){
      setColorRED();
      setPossibleReasons(getResources(R.string.A3_7_RED));
    } else if(getA() >= 0.4 && getA() < 0.8){
      setColorRED();
      setPossibleReasons(getResources(R.string.A3_7_RED2));
    } else if(getA() >= 2.5){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A3_7_BURGUNDY));
    }
  }
}
