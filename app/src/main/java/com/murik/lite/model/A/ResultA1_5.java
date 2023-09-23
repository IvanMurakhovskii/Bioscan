package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA1_5 extends BaseResult {


  public ResultA1_5(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("1_5");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.9 && getA() < 1.0){
      setColorGREEN();
    } else if(getA() > 0.6 && getA() < 0.81){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A1_5_YELLOW));
    } else if(getA() >= 0.81 && getA() <= 0.89){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_5_RED));
    } else if(getA() <= 0.6){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A1_5_BURGUNDY));
    } else if(getA() >= 0.22 && getA() <= 0.28){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A1_5_BURGUNDY2));
    } else if(getA() > 1.1 && getA() <= 1.8){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A1_5_GRAY));
    }
  }
}
