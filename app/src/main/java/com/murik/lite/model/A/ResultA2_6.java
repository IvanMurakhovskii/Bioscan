package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA2_6 extends BaseResult {


  public ResultA2_6(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("2_6");
  }

  @Override
  public void setResult() {
    if(getA() < 1.4){
      setColorGREEN();
    } else if(getA() > 1.4 && getA() <= 1.7){
      setColorRED();
      setPossibleReasons(getResources(R.string.A2_6_RED));
    } else if(getA() > 1.7){
      if(getInputData().isPractice()){
        setColorCRIMSON();
        setPossibleReasons(getResources(R.string.Practice));
      } else {
        setColorBURGUNDY();
        setPossibleReasons(getResources(R.string.A2_6_BURGUNDY));
      }
    } else if(getA() > 2 && getA() < 3.5){
      if(getInputData().isPractice()){
        setColorCRIMSON();
        setPossibleReasons(getResources(R.string.Practice));
      } else {
        setColorBURGUNDY();
        setPossibleReasons(getResources(R.string.A2_6_BURGUNDY));
      }
    } else if(getA() > 2.2 && getA() < 2.8){
        setColorGRAY();
      setPossibleReasons(getResources(R.string.A5_7_GRAY2));

    }
  }
}
