package com.murik.enose.model.A;

import android.content.Context;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

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
            setPossibleReasons(getResources(R.string.A4_5_RED) +"\n"+ getResources(R.string.FEMININE));
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
