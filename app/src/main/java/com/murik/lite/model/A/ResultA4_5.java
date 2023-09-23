package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.Const;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA4_5 extends BaseResult {


  public ResultA4_5(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("4_5");
  }

  @Override
  public void setResult() {
    if(getA() >= 1.4 && getA() <= 5) {
      if (getInputData().isPractice()) {
        setColorCRIMSON();
        setPossibleReasons(getResources(R.string.A4_5_CRIMSON));
      }
      }else if(getA() >= 1.4 && getA() < 2.5){
          setColorGREEN();
      } else if(getA() >= 2.5 && getA() <= 2.8){
          setColorYELLOW();
          setPossibleReasons(getResources(R.string.A4_5_YELLOW));
      }else if(getA() >= 1 && getA() <= 1.3) {
          setColorRED();
          setPossibleReasons(getResources(R.string.A4_5_RED_EXTRA));
      } else if(getA() > 2.8 ){
          setColorRED();
          if(getInputData().getGender() == Const.GENDER_FEMININE){
            setPossibleReasons(getResources(R.string.FEMININE) +"\n"+ getResources(R.string.A4_5_RED));
          } else {
            setPossibleReasons(getResources(R.string.A4_5_RED));
          }
      }else if(getA() >=0.75 && getA() <=1.2){
          setColorRED();
          setPossibleReasons(getResources(R.string.A4_5_RED_EXTRA));
      } else if(getA() >= 0.34 && getA() <0.5){
          setColorGRAY();
          setPossibleReasons(getResources(R.string.A4_5_GRAY));
      }
  }
}
