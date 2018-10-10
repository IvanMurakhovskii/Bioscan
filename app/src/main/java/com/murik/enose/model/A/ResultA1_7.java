package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.InputDataParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA1_7 extends BaseResult {


  public ResultA1_7(double A, InputDataParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("1_7");
  }

  @Override
  public void setResult() {
    if(getA() <= 2.1){
      setColorGREEN();
    } else if(getA() > 2.1 && getA() <= 2.2){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.YELLOW) + "\n" + getResources(R.string.A1_7_RED));
    } else if(getA() > 2.3){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_7_RED));
    }
  }
}
