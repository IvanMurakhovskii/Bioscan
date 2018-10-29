package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA3_5 extends BaseResult {


  public ResultA3_5(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("3_5");
  }

  @Override
  public void setResult() {
    if(getA() > 1.3 && getA() < 1.5){
      setColorBLUE();
      setPossibleReasons(getResources(R.string.A3_5_BLUE));
    } else if( getA() <  1.25){
      setColorGREEN();
    } else if(getA() >= 0.8 && getA() <=0.9){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A3_5_YELLOW));
    } else if( getA() >= 1.5 && getA() <= 1.6){
      setColorRED();
      setPossibleReasons(getResources(R.string.A3_5_RED));
    } else  if(getA() > 2){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.GIGROPAT));
    }
  }

}
