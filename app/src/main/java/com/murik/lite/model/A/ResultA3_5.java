package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA3_5 extends BaseResult {


  public ResultA3_5(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("3_5");
  }

  @Override
  public void setResult() {
    if(getA() >= 0.2 && getA() <= 0.3){
      setColorRED();
      setPossibleReasons(getResources(R.string.A3_5_RED2));
    } else if( getA() <=  1.3 && getA() > 0.9){
      setColorGREEN();
    } else if(getA() >= 0.7 && getA() <= 0.9){
      setColorYELLOW();
      setPossibleReasons(getResources(R.string.A3_5_YELLOW));
    } else if( getA() >= 1.3 && getA() <= 2.2){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A3_5_PRIMARY_DARK_GRAY));
    }  else if(getA() <= 0.6){
      setColorRED();
      setPossibleReasons(getResources(R.string.A3_5_RED));
    }
  }

}
