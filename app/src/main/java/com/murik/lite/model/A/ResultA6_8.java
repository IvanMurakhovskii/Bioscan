package com.murik.lite.model.A;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA6_8 extends BaseResult {

  public ResultA6_8(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("6_8");
  }

  @Override
  public void setResult() {
    if(getA() > 0.29 && getA() <= 0.32){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A6_8_YELLOW3));
    } else if(getA() > 0.2 && getA() <= 0.28){
      setColorGREEN();
    } else if(getA() >= 0.5 && getA() <= 0.6){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A6_8_YELLOW));
    } else if(getA() >=1 && getA() <= 1.4){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A6_8_YELLOW2));
    } else if(getA() > 0.28 && getA() < 0.294){
      setColorRED();
      setPossibleReasons(getResources(R.string.A6_8_RED));
    } else if(getA() > 0.17 && getA() < 0.21) {
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A6_8_GRAY));
    } else if(getA() > 0.11 && getA() < 0.12) {
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A6_8_GRAY2));
    } else if(getA() >= 0.07 && getA() <= 0.11) {
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A6_8_GRAY3));
    } else if(getA() > 0.15 && getA() < 0.17) {
      setColor(Color.WHITE);
      setPossibleReasons(getResources(R.string.A6_8_WHITE));
    }
  }
}
