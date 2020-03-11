package com.murik.enose.model.A;

import android.content.Context;
import android.graphics.Color;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class ResultA5_6 extends BaseResult {


  public ResultA5_6(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("5_6");
  }

  @Override
  public void setResult() {
    if (getA() < 1) {
      setColor(Color.WHITE);
    } else if (getA() >= 1.5 && getA() <= 1.9) {
      setColorGREEN();
    } else if(getA() > 1.3 && getA() < 1.5){
      setColorRED();
      setPossibleReasons(getResources(R.string.A5_6_RED));
    } else if(getA() >= 1 && getA() < 1.3){
      setColorRED();
      setPossibleReasons(getResources(R.string.A5_6_RED2));
    } else if (getA() > 2.8) {
      setColorRED();
      setPossibleReasons(getResources(R.string.A5_6_RED3));
    } else if(getA() > 0.8 && getA() < 1){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A5_6_GRAY));
    } else if(getA() >= 0.5 && getA() <= 0.7){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A5_6_GRAY2));
    }
  }
}
