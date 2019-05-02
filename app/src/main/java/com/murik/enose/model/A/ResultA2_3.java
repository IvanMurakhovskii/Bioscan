package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA2_3 extends BaseResult{


  public ResultA2_3(double A,DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("2_3");
  }

  @Override
  public void setResult() {
    if(getA() > 0.5 && getA() < 1.3){
      setColorGREEN();
    } else if (getA() == 0.5) {
      if(getInputData().getGender() == Const.GENDER_MALE) {
        setColorBLUE();
        setPossibleReasons(getResources(R.string.A2_3_BLUE));
      } else {
        setColorYELLOW();
        setPossibleReasons(getResources(R.string.A2_3_YELLOW) + "\n" + getResources(R.string.FEMININE));
      }
    } else if(getA() > 1.3 && getA() < 1.9){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A2_3_RED));
    }else if(getA() >= 2){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A2_3_GRAY));
    }
  }
}
