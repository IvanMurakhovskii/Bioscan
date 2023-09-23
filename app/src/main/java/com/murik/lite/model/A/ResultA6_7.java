package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.Const;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA6_7 extends BaseResult {


  public ResultA6_7(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("6_7");
  }

  @Override
  public void setResult() {
    if(getA() < 0.85 && getA() >= 0.45){
      setColorGREEN();
    } else if(getA() >= 0.85 && getA() < 0.9){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A6_7_YELLOW));
    } else if(getA() >= 0.38 && getA() <= 0.44){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A6_7_GRAY));
    }else if(getA() >= 0.9){
      setColorRED();
      if(getInputData().getGender() == Const.GENDER_FEMININE){
        setPossibleReasons(getResources(R.string.A6_7_RED_FEMININE));
      } else {
        setPossibleReasons(getResources(R.string.A6_7_RED_MAN));
      }
    }
  }
}
