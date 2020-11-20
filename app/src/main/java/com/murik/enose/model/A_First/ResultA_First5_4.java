package com.murik.enose.model.A_First;

import android.content.Context;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.resultbyMaxValue.BaseResultFirst;

public class ResultA_First5_4 extends BaseResultFirst {


  public ResultA_First5_4(double A, DataByMaxParcelable inputData, Context context, float coefficient) {
    super(A, inputData, context, coefficient);
    setLegend("5_4");
  }

    public void setResult() {
      if (getA() >= 0.68 && getA() <= 0.72) {
        setColorRED();
        setPossibleReasons(getResources(R.string.A_SHORT_5_4_RED));
      } else if (getA() >= 0.73 && getA() <= 0.8) {
        setColorYELLOW();
        setPossibleReasons(getResources(R.string.A_SHORT_5_4_YELLOW_1));
      } else if (getA() >= 1.4 && getA() <= 3) {
        setColorYELLOW();
        setPossibleReasons(getResources(R.string.A_SHORT_5_4_YELLOW_2));
      } else if (getA() >= 0.45 && getA() <= 0.67) {
        setColorBURGUNDY();
        setPossibleReasons(getResources(R.string.A_SHORT_5_4_BURGUNDY_1));
      } else if (getA() >= 0.3 && getA() <= 0.44) {
        setColorBURGUNDY();
        setPossibleReasons(getResources(R.string.A_SHORT_5_4_BURGUNDY_2));
      } else if(getA() <= 0) {
        if(getInputData().isPractice()) {
          setColorCRIMSON();
          setPossibleReasons(getResources(R.string.A_SHORT_5_4_CRIMSON));
        }
      }
    }
}
