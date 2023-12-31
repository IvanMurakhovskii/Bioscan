package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA2_4 extends BaseResult {


  public ResultA2_4(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("2_4");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.3 && getA() <=0.63){
      setColorGREEN();
    } else if(getA() >= 0.41 && getA() <= 0.45){
      setColorBLUE();
      setPossibleReasons(getResources(R.string.A2_4_BLUE));
    }else if(getA() >= 0.25 && getA() < 0.3){
      setColorRED();
      setPossibleReasons(getResources(R.string.A2_4_RED));
    } else if(getA() >= 0.16 && getA() <= 0.24){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A2_4_BURGUNDY));
    } else if(getA() >= 1.4 && getA() <= 2.2){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A2_4_GRAY));
    } else if(getA() == 1){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A2_4_YELLOW));
    }
  }
}
