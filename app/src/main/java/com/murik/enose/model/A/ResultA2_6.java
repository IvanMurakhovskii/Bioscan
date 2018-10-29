package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA2_6 extends BaseResult {


  public ResultA2_6(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("2_6");
  }

  @Override
  public void setResult() {
    if(getA() < 1.4){
      setColorGREEN();
    } else if(getA() == 1.4){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.YELLOW) + "\n" + getResources(R.string.A2_6_RED));
    } else if(getA() > 1.4 && getA() < 1.7){
      setColorRED();
      setPossibleReasons(getResources(R.string.A2_6_RED));
    } else if(getA() >= 1.7){
      if(getInputData().isPractice()){
        setColorCRIMSON();
        setPossibleReasons(getResources(R.string.A2_6_CRIMSON));
      } else {
        setColorBURGUNDY();
        setPossibleReasons(getResources(R.string.A2_6_BURGUNDY));
      }

    }
  }
}
