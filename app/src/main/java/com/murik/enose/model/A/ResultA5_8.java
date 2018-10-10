package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.InputDataParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA5_8 extends BaseResult {


  public ResultA5_8(double A, InputDataParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("5_8");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.35 && getA() <= 1.0){
      setColorBLUE();
    } else if(getA() == 1){
      setColorGREEN();
    } else if(getA() <= 0.35){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.YELLOW) + "\n" + getResources(R.string.A5_8_RED));
    } else if(getA() > 0.35 && getA() < 0.39){
      if(getInputData().isPractice()){
        setColorCRIMSON();
        setPossibleReasons(getResources(R.string.A5_8_CRIMSOM));
      } else {
        setColorRED();
        setPossibleReasons(getResources(R.string.A5_8_RED));
      }

    }
  }
}
