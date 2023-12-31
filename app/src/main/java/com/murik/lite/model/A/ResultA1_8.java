package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA1_8 extends BaseResult {


  public ResultA1_8(double A,  DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("1_8");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.1 && getA() < 0.45){
      setColorGREEN();
    } else if(getA() >=0.45 && getA() <= 0.47){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A1_8_YELLOW));
    } else if(getA() > 0.48 && getA() <= 0.55){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_8_RED));
    } else if(getA() < 0.1){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_8_RED2));
    } else if(getA() >= 0.8 && getA() <= 1.8){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A1_8_GRAY));
    }
  }
}
