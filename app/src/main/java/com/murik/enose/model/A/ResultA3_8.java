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
    if(getA() >=0.4 && getA() <= 0.42){
      setColorBLUE();
    } else if(getA() < 0.5){
      setColorGREEN();
    } else if(getA() > 0.54 && getA() <= 0.59){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A3_8_YELLOW));
    } else if(getA() >= 0.6 && getA() <= 0.74){
      setColorRED();
      setPossibleReasons(getResources(R.string.A3_8_RED));
    } else if(getA() > 1){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A3_8_GRAY));
    } else if(getA() >= 0.12 && getA() <= 0.16) {
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A3_8_GRAY2));
    }
  }

}
