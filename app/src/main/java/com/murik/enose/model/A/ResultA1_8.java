package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA1_8 extends BaseResult {


  public ResultA1_8(double A,  DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("1_8");
  }

  @Override
  public void setResult() {
    if(getA() < 0.45){
      setColorGREEN();
    } else if(getA() >=0.45 && getA() <= 0.47){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A1_8_YELLOW));
    } else if(getA() > 0.47 && getA() <= 0.55){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_8_RED));
    }
  }
}
