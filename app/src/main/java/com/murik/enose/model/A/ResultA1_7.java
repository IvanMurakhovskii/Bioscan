package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA1_7 extends BaseResult {


  public ResultA1_7(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("1_7");
  }

  @Override
  public void setResult() {
    if(getA() < 1.1 && getA() > 0.8){
      setColorBLUE();
//      setPossibleReasons(getResources(R.string.A1_7_BLUE) );
    } else if(getA() <= 1.9 && getA() >= 1.1){
      setColorGREEN();
    } else if(getA() > 1.9 && getA() <= 2.3){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A1_7_YELLOW));
    } else if(getA() > 2.3){
      setColorRED();
      setPossibleReasons(getResources(R.string.A1_7_RED));
    } else if(getA() >= 4){
      setColorBLUE();
      setPossibleReasons(getResources(R.string.voter));
    } else if(getA() >= 0.38 && getA() <= 0.84){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A1_7_GRAY2));
    } else if(getA() >= 0.85 && getA() <= 1){
    setColorGRAY();
    setPossibleReasons(getResources(R.string.A1_7_GRAY));
    }
  }
}
