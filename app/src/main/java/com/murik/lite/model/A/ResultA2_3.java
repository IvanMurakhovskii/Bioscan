package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA2_3 extends BaseResult{


  public ResultA2_3(double A,DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("2_3");
  }

  @Override
  public void setResult() {
    if(getA() > 0.5 && getA() < 1.3){
      setColorGREEN();
      setPossibleReasons(getResources(R.string.A2_3_GREEN));
    } else if (getA() == 0.5) {
        setColorYELLOW();
        setPossibleReasons(getResources(R.string.A2_3_YELLOW) + "\n" + getResources(R.string.FEMININE));
    } else if(getA() > 1.3 && getA() < 1.9){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A2_3_GRAY2));
    } else if(getA() > 2.3 && getA() < 3.0){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A2_3_GRAY3));
    } else if(getA() >= 2 && getA() <= 2.2){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A2_3_GRAY));
    } else if(getA() > 1.9 && getA() <= 2.0){
      setColorGRAY();
      setPossibleReasons(getResources(R.string.A4_6_WHITE));
    }
  }
}
