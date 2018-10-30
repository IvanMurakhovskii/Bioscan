package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA6_7 extends BaseResult {


  public ResultA6_7(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("6_7");
  }

  @Override
  public void setResult() {
    if(getA() < 0.85){
      setColorGREEN();
    } else if(getA() >= 0.85 && getA() < 0.9){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.YELLOW) + "\n" + getResources(R.string.A6_7_YELLOW));
    } else if(getA() >= 0.9){
      setColorRED();

      if(getInputData().getGender() == Const.GENDER_FEMININE){
        setPossibleReasons(getResources(R.string.FEMININE) +"\n"+ getResources(R.string.A6_7_RED));
      } else {
        setPossibleReasons(getResources(R.string.A6_7_RED));
      }
    }
  }
}
