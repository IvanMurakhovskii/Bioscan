package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

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
    } else if(getA() >= 0.13 && getA() <= 0.15) {
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A3_8_GRAY3));
    } else if(getA() >= 0.18 && getA() <= 0.19) {
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A3_8_GRAY2));
    }
  }

}
