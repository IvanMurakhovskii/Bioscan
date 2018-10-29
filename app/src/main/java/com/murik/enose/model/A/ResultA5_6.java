package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA5_6 extends BaseResult {


  public ResultA5_6(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("5_6");
  }

  @Override
  public void setResult() {
    if(getA() < 1){
      setColorGRAY();
    } else if(getA() > 1.9 && getA() < 2.3){
      setColorBLUE();
      setPossibleReasons("Жарко, испарина");

    } else if(getA() > 1.5 && getA() < 1.9 ){
      setColorGREEN();
    } else if(getA() >= 1.2 && getA() <= 1.5){
      setColorRED();
      setPossibleReasons(getResources(R.string.A5_6_RED));
    } /*else if(getA() > 1.5 && getA() < 1.8){
      setColorBURGUNDY();
      setPossibleReasons(getResources(R.string.A5_6_BURGUNDY));
    } */
    else if(getA() > 2){
      if(getInputData().isPractice()){
        setColorCRIMSON();
        setPossibleReasons(getResources(R.string.A5_6_CRIMSON));
      }
    }
  }
}
