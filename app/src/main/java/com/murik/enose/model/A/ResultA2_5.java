package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA2_5 extends BaseResult {


  public ResultA2_5(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("2_5");
  }

  @Override
  public void setResult() {
    if(getA() > 0.52){
      setColorGREEN();
    } else if(getA() <= 0.52 && getA() >= 0.48){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.YELLOW) + "\n" + getResources(R.string.A2_5_YELLOW));
    }else if(getA() < 0.48){
      setColorRED();
      setPossibleReasons(getResources(R.string.A2_5_RED));
    }
  }
}
