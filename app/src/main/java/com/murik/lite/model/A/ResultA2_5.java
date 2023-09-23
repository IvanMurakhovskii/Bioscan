package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA2_5 extends BaseResult {


  public ResultA2_5(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("2_5");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.51 && getA() < 1.3){
      setColorGREEN();
    } else if(getA() > 1.19 && getA() < 1.21){
      setColorBLUE();
      setPossibleReasons(getResources(R.string.A2_5_BLUE));
    }else if(getA() < 0.50){
      setColorRED();
      setPossibleReasons(getResources(R.string.A2_5_RED));
    } else if(getA() > 1.3 && getA() < 2.0){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A2_5_GRAY));
    } else if(getA() > 2.5){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A2_5_GRAY2));
    } else if(getA() <= 0.48){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A2_5_YELLOW));
    }
  }
}
