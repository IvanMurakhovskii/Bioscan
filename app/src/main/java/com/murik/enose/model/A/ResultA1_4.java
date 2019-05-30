package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA1_4 extends BaseResult {


  public ResultA1_4(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("1_4");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.42){
      setColorGREEN();
    } else if(getA() >= 0.38 && getA() <= 0.41){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_4_RED));
    } else if(getA() <= 0.3){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_4_RED_EXTRA));
    } else if(getA() >= 0.7){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A1_4_BURGUNDY));
    } else if(getA() >= 0.8 && getA() <= 1.1){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A1_4_GRAY));
    } else if(getA() >= 0.64 && getA() <= 0.66){
      setColorBLUE();
      setPossibleReasons(getResources(R.string.A1_4_BLUE));
    }
  }
}
