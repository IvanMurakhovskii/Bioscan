package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA1_3 extends BaseResult {

  public ResultA1_3(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("1_3");
  }

  @Override
  public void setResult() {
    if (getA() >= 0.7 && getA() < 0.85) {
      setColorBLUE();
      setPossibleReasons(getResources(R.string.voter));
    } else if (getA() >= 0.85 && getA() <= 1.4) {
      setColorGREEN();
    } else if (getA() >= 1.8 && getA() <= 3) {
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A1_3_YELLOW));
    } else if (getA() >= 0.6 && getA() < 0.73) {
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_3_RED));
    } else if(getA() >=0.74 && getA() <=0.80){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_3_RED2));
    }else if (getA() > 1.4) {
      if (getInputData().isPractice()) {
        setColorCRIMSON();
        setPossibleReasons(getResources(R.string.Practice));
      } else {
        setColorYELLOW();
        if (getInputData().getGender() == Const.GENDER_MALE) {
          setPossibleReasons(getResources(R.string.A1_3_YELLOW_MAN));
        } else {
          setPossibleReasons(getResources(R.string.A1_3_YELLOW_FEMININE) + "\n" + getResources(
              R.string.A1_3_YELLOW_FEMININE));
        }
      }
    } else if (getA() < 0.6) {
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A1_3_BURGUNDY));
    }
  }

}
