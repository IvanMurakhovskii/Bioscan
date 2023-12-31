package com.murik.lite.model.A;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.Const;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

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
    } else if (getA() >= 1.8 && getA() < 3) {
      setColorYELLOW();
      if (getInputData().getGender() == Const.GENDER_MALE) {
        setPossibleReasons(getResources(R.string.A1_3_YELLOW_MAN));
      } else {
        setPossibleReasons(getResources(R.string.A1_3_YELLOW_FEMININE));
      }
    } else if (getA() >= 0.6 && getA() <= 0.84) {
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_3_RED));
    }else if (getA() > 1.4 && getA() < 1.8) {
      if (getInputData().isPractice()) {
        setColorCRIMSON();
        setPossibleReasons(getResources(R.string.Practice));
      } else {
        setColorYELLOW();
        setPossibleReasons(getResources(R.string.A1_3_YELLOW));
      }
    } else if (getA() < 0.6) {
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A1_3_BURGUNDY));
    } else if(getA() > 3){
      setColor(Color.WHITE);
      setPossibleReasons(getResources(R.string.A1_3_WHITE));
    }
  }

}
