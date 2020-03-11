package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA4_8 extends BaseResult {


  public ResultA4_8(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("4_8");
  }

  @Override
  public void setResult() {
if (getA() >= 0.35 && getA() <=0.64){
     setColorGREEN();
    } else if(getA() > 0.64 && getA() <= 1){
      setColorRED();
      setPossibleReasons(getResources(R.string.A4_8_RED));
    } else if(getA() > 1.1) {
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A4_8_GRAY2));
    } else if(getA() >= 0.12 && getA() <= 0.35) {
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A4_8_GRAY));
    }
  }
}
