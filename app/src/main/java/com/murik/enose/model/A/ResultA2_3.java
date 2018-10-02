package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.dto.InputDataParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA2_3 extends BaseResult{


  public ResultA2_3(double A,InputDataParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("A2_3");
  }

  @Override
  public void setResult() {
    if(getA() > 0.5){
      setColorGREEN();
    } else if(getA() > 0.5 && getA() <= 0.6){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A2_3_YELLOW));
      if(getInputData().getGender() == Const.GENDER_FEMININE){
        setPossibleReasons(getResources(R.string.A2_3_YELLOW) + "\n" + getResources(R.string.A2_3_YELLOW_FEMININE));
      }

    } if (getA() == 0.5) {
      setColorBLUE();
    }
  }

}
