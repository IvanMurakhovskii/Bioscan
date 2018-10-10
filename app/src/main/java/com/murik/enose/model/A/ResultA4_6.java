package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.InputDataParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA4_6 extends BaseResult {


  public ResultA4_6(double A, InputDataParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("4_6");
  }

  @Override
  public void setResult() {
    if(getA() < 4.8){
      setColorGREEN();
      //setPossibleReasons(getResources(R.string.YELLOW) + "\n" +  getResources(R.string.A4_6_BURGUNDY));
    } else if(getA() >= 4.8 && getA() <= 5.8){
      if(getInputData().isPractice()){
        setColorCRIMSON();
        setPossibleReasons(getResources(R.string.A4_6_CRIMSON));
      } else {
        setColorRED();
        setPossibleReasons(getResources(R.string.A4_6_BURGUNDY));
      }

    }
  }

}
