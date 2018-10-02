package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.InputDataParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA1_2 extends BaseResult {


  public ResultA1_2(double A, InputDataParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("A1_2");
  }

  public void setResult(){
    if(getA() > 2){
      setColorBLUE();
    }else if (getA() >= 1.14 && getA() < 2){
      setColorGREEN();
    } else if(getA() >= 0.9 && getA() < 1.14){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A1_2_YELLOW));
    } else if(getA() < 0.9){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_2_RED));
    } else if(getA() >= 2){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A1_2_BURGUNDY));
    }
  }
}
