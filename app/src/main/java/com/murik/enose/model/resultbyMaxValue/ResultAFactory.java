package com.murik.enose.model.resultbyMaxValue;

import android.content.Context;
import com.murik.enose.Const;
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
import com.murik.enose.model.A.ResultA3_5;
import com.murik.enose.model.A.ResultA3_7;
import com.murik.enose.model.A.ResultA3_8;
import com.murik.enose.model.A.ResultA4_5;
import com.murik.enose.model.A.ResultA4_6;
import com.murik.enose.model.A.ResultA4_8;
import com.murik.enose.model.A.ResultA5_6;
import com.murik.enose.model.A.ResultA6_7;
import com.murik.enose.model.A.ResultA6_8;
import com.murik.enose.model.ResultBySens;
import com.murik.enose.model.dto.DataByMaxParcelable;
import java.util.ArrayList;

public class ResultAFactory {

  private ArrayList<ResultBySens> A = new ArrayList<>();
  private ArrayList<Integer> maxSensResult = new ArrayList<>();
  private ResultByMask resultByMask;
  private DataByMaxParcelable inputData;

  private Context context;

  public ResultAFactory(DataByMaxParcelable inputData, int hand, Context context) {

    if (hand == Const.LEFT_HAND) {
      this.maxSensResult = inputData.getLeftHandDataSensor();
    } else if(hand == Const.RIGHT_HAND){
      this.maxSensResult = inputData.getRightHandDataSensor();
    } else if(hand == Const.MEAN_HAND) {
      for(int i = 0; i < inputData.getRightHandDataSensor().size(); i++){
        this.maxSensResult.add(inputData.getRightHandDataSensor().get(i)
            + inputData.getLeftHandDataSensor().get(i));
      }
    }
    this.inputData = inputData;
    this.resultByMask = new ResultByMask(maxSensResult);

    this.context = context;
  }

  public boolean calculateResultA(){
    if(!maxSensResult.isEmpty()){
      resultByMask.setDataSensorMax(maxSensResult);
      resultByMask.calculateA();

      A.add(new ResultA1_3(resultByMask.getA1_3(),inputData, context));
      A.add(new ResultA1_5(resultByMask.getA1_5(),inputData, context));
      A.add(new ResultA1_8(resultByMask.getA1_8(),inputData, context));
      A.add(new ResultA1_4(resultByMask.getA1_4(),inputData, context));
      A.add(new ResultA2_4(resultByMask.getA2_4(),inputData, context));
      A.add(new ResultA2_5(resultByMask.getA2_5(),inputData, context));
      A.add(new ResultA6_7(resultByMask.getA6_7(),inputData, context));
      A.add(new ResultA3_8(resultByMask.getA3_8(),inputData, context));
      A.add(new ResultA4_5(resultByMask.getA4_5(),inputData, context));
      A.add(new ResultA4_6(resultByMask.getA4_6(),inputData, context));
      A.add(new ResultA6_8(resultByMask.getA6_8(),inputData, context));
      A.add(new ResultA1_2(resultByMask.getA1_2(),inputData, context));
      A.add(new ResultA5_6(resultByMask.getA5_6(),inputData, context));
      A.add(new ResultA3_5(resultByMask.getA3_5(),inputData, context));
      A.add(new ResultA3_7(resultByMask.getA3_7(),inputData, context));
      A.add(new ResultA2_3(resultByMask.getA2_3(),inputData, context));
      A.add(new ResultA4_8(resultByMask.getA4_8(),inputData, context));
      //A.add(new ResultA7_8(resultByMask.getA7_8(),inputData, context));
      A.add(new ResultA1_7(resultByMask.getA1_7(),inputData, context));
      A.add(new ResultA2_6(resultByMask.getA2_6(),inputData, context));
      //A.add(new ResultA5_8(resultByMask.getA5_8(),inputData, context));

      return true;
    } else {
      return false;
    }
  }
  public ArrayList<ResultBySens> getA() {
    return A;
  }
}
