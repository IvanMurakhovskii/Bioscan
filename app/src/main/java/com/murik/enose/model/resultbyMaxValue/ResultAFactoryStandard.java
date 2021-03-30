package com.murik.enose.model.resultbyMaxValue;

import android.content.Context;

import com.murik.enose.Const;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.A.ResultA1_2;
import com.murik.enose.model.A.ResultA1_3;
import com.murik.enose.model.A.ResultA1_4;
import com.murik.enose.model.A.ResultA1_5;
import com.murik.enose.model.A.ResultA1_7;
import com.murik.enose.model.A.ResultA1_8;
import com.murik.enose.model.A.ResultA2_3;
import com.murik.enose.model.A.ResultA2_4;
import com.murik.enose.model.A.ResultA2_5;
import com.murik.enose.model.A.ResultA2_6;
import com.murik.enose.model.A.ResultA2_8;
import com.murik.enose.model.A.ResultA3_5;
import com.murik.enose.model.A.ResultA3_7;
import com.murik.enose.model.A.ResultA3_8;
import com.murik.enose.model.A.ResultA4_5;
import com.murik.enose.model.A.ResultA4_6;
import com.murik.enose.model.A.ResultA4_8;
import com.murik.enose.model.A.ResultA5_6;
import com.murik.enose.model.A.ResultA5_7;
import com.murik.enose.model.A.ResultA6_7;
import com.murik.enose.model.A.ResultA6_8;
import com.murik.enose.model.ResultAFactory;
import com.murik.enose.model.ResultBySens;

import java.util.ArrayList;

import static com.murik.enose.service.Impl.BaseMeasureService.calculateDifferenceLeftRight;
import static com.murik.enose.service.Impl.BaseMeasureService.getAreaByMask;

public class ResultAFactoryStandard extends ResultAFactory {

  public ResultAFactoryStandard(DataByMaxParcelable inputData, int hand, Context context) {
      super(inputData, hand, context);
  }

  @Override
  public Float calculateAndGetAreaDifference() {
    float areaBodyLeft = getAreaByMask(Const.BODY, getInputData().getLeftHandDataSensor());
    float areaBodyRight = getAreaByMask(Const.BODY, getInputData().getRightHandDataSensor());

    return calculateDifferenceLeftRight(areaBodyLeft, areaBodyRight);
  }

  public boolean calculateResultA(){
    ArrayList<ResultBySens> A = getA();
    ArrayList<Integer> maxSensResult = getMaxSensResult();
    ResultByMask resultByMask = getResultByMask();
    if(!maxSensResult.isEmpty()){
      getResultByMask().setDataSensorMax(maxSensResult);
      resultByMask.calculateA();
      
      try{
        A.add(new ResultA1_2(resultByMask.getA1_2(),getInputData(), getContext()));
        A.add(new ResultA2_8(resultByMask.getA2_8(),getInputData(), getContext()));
        A.add(new ResultA4_6(resultByMask.getA4_6(),getInputData(), getContext()));
        A.add(new ResultA2_3(resultByMask.getA2_3(),getInputData(), getContext()));
        A.add(new ResultA2_4(resultByMask.getA2_4(),getInputData(), getContext()));
        A.add(new ResultA4_8(resultByMask.getA4_8(),getInputData(), getContext()));
        A.add(new ResultA1_8(resultByMask.getA1_8(),getInputData(), getContext()));
        A.add(new ResultA2_6(resultByMask.getA2_6(),getInputData(), getContext()));
        A.add(new ResultA3_7(resultByMask.getA3_7(),getInputData(), getContext()));
        A.add(new ResultA1_5(resultByMask.getA1_5(),getInputData(), getContext()));
        A.add(new ResultA5_6(resultByMask.getA5_6(),getInputData(), getContext()));
        A.add(new ResultA2_5(resultByMask.getA2_5(),getInputData(), getContext()));
        A.add(new ResultA1_3(resultByMask.getA1_3(),getInputData(), getContext()));
        A.add(new ResultA4_5(resultByMask.getA4_5(),getInputData(), getContext()));
        A.add(new ResultA3_5(resultByMask.getA3_5(),getInputData(), getContext()));
        A.add(new ResultA1_4(resultByMask.getA1_4(),getInputData(), getContext()));
        A.add(new ResultA1_7(resultByMask.getA1_7(),getInputData(), getContext()));
        A.add(new ResultA3_8(resultByMask.getA3_8(),getInputData(), getContext()));
        A.add(new ResultA6_8(resultByMask.getA6_8(),getInputData(), getContext()));
        A.add(new ResultA6_7(resultByMask.getA6_7(),getInputData(), getContext()));
        A.add(new ResultA5_7(resultByMask.getA5_7(),getInputData(), getContext()));
        //A.add(new ResultA7_8(resultByMask.getA7_8(),inputData, context));
        //A.add(new ResultA5_8(resultByMask.getA5_8(),inputData, context));
      }catch (Exception e){
        return false;
      }
      return true;
    } else {
      return false;
    }
  }

}
