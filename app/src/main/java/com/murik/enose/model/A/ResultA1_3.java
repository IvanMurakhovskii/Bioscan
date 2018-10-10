package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.dto.InputDataParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA1_3 extends BaseResult {


  public ResultA1_3(double A, InputDataParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("1_3");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.7 && getA() <= 0.84){
      setColorBLUE();
    } else if(getA() >= 1.4){
      setColorGREEN();
    } else if(getA() >= 0.65 && getA() <= 0.75){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.YELLOW) + "\n" + getResources(R.string.A1_3_RED));
    } else if(getA() >= 0.6 && getA() <= 0.64){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_3_RED));
    } else if(getA() < 0.6){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A1_3_BURGUNDY));
    } else if(getA() >= 1.23 && getA() <= 1.35){
      if(getInputData().isPractice()) {
        setColorCRIMSON();
        if(getInputData().getGender() == Const.GENDER_MALE){
          setPossibleReasons(getResources(R.string.A1_3_CRIMSON) +   "\n" + getResources(R.string.Man));
        } else if(getInputData().getGender() == Const.GENDER_FEMININE){
          setPossibleReasons(getResources(R.string.A1_3_CRIMSON) +   "\n" + getResources(R.string.A1_3_CRIMSON_FEMININE));

        }
      }
    }
  }

}
