package com.murik.enose.model.resultbyMaxValue;

import android.content.Context;
import com.murik.enose.model.A.ResultA1_2;
import com.murik.enose.model.A.ResultA1_3;
import com.murik.enose.model.A.ResultA1_4;
import com.murik.enose.model.A.ResultA1_5;
import com.murik.enose.model.A.ResultA1_7;
import com.murik.enose.model.A.ResultA1_8;
import com.murik.enose.model.A.ResultA2_3;
import com.murik.enose.model.A.ResultA2_4;
import com.murik.enose.model.A.ResultA2_5;
import com.murik.enose.model.A.ResultA3_5;
import com.murik.enose.model.A.ResultA3_7;
import com.murik.enose.model.A.ResultA3_8;
import com.murik.enose.model.A.ResultA4_5;
import com.murik.enose.model.A.ResultA4_6;
import com.murik.enose.model.A.ResultA4_8;
import com.murik.enose.model.A.ResultA5_6;
import com.murik.enose.model.A.ResultA5_8;
import com.murik.enose.model.A.ResultA6_7;
import com.murik.enose.model.A.ResultA7_8;
import com.murik.enose.model.InputData;
import com.murik.enose.model.ResultBySens;
import java.util.ArrayList;

public class ResultAFactory {

  private ArrayList<ResultBySens> A = new ArrayList<>();
  private ArrayList<Integer> maxSensResult;
  private ResultByMask resultByMask;
  private boolean isPrectice;

  private Context context;

  public ResultAFactory(ResultByMask resultByMask,InputData data, Context context) {
    this.maxSensResult = data.getDatasens();
    this.isPrectice = data.isPractice();
    this.resultByMask = resultByMask;
    this.context = context;
  }

  public void calculateResultA(){
    resultByMask.setDataSensorMax(maxSensResult);
    resultByMask.calculateA();

    A.add(new ResultA1_2(resultByMask.getA1_2(),isPrectice, context));
    A.add(new ResultA2_3(resultByMask.getA2_3(),isPrectice, context));
    A.add(new ResultA3_5(resultByMask.getA3_5(),isPrectice, context));
    A.add(new ResultA3_7(resultByMask.getA3_7(),isPrectice, context));
    A.add(new ResultA4_8(resultByMask.getA4_8(),isPrectice, context));
    A.add(new ResultA7_8(resultByMask.getA7_8(),isPrectice, context));
    A.add(new ResultA1_4(resultByMask.getA1_4(),isPrectice, context));
    A.add(new ResultA1_5(resultByMask.getA1_5(),isPrectice, context));
    A.add(new ResultA3_8(resultByMask.getA3_8(),isPrectice, context));
    A.add(new ResultA2_4(resultByMask.getA2_4(),isPrectice, context));
    A.add(new ResultA1_7(resultByMask.getA1_7(),isPrectice, context));
    A.add(new ResultA1_8(resultByMask.getA1_8(),isPrectice, context));
    A.add(new ResultA2_5(resultByMask.getA2_5(),isPrectice, context));
    A.add(new ResultA1_3(resultByMask.getA1_3(),isPrectice, context));
    A.add(new ResultA4_5(resultByMask.getA4_5(),isPrectice, context));
    A.add(new ResultA4_6(resultByMask.getA4_6(),isPrectice, context));
    A.add(new ResultA6_7(resultByMask.getA6_7(),isPrectice, context));
    A.add(new ResultA5_8(resultByMask.getA5_8(),isPrectice, context));
    A.add(new ResultA5_6(resultByMask.getA5_6(),isPrectice, context));


  }
  public ArrayList<ResultBySens> getA() {
    return A;
  }
}
