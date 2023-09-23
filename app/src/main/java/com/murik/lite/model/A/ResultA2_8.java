package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;


public class ResultA2_8 extends BaseResult {


  public ResultA2_8(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("2_8");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.2 && getA() < 0.45){
      setColorGREEN();
    } else if(getA() <= 0.19){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A2_8_YELLOW));
    } else if(getA() > 0.42) {
      setColorRED();
      setPossibleReasons(getResources(R.string.A2_8_RED));
    } else if(getA() >= 2.8){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A2_8_GRAY));
    }
  }
}

