package com.murik.enose.model.resultbyMaxValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class ResultByMask {

  private ArrayList<Integer> dataSensorMax;

  private ArrayList<ArrayList<Double>> A = new ArrayList<>();
  private ArrayList<Double> signalParts;

  public ResultByMask(ArrayList<Integer> dataSensorMax) {
    this.dataSensorMax = dataSensorMax;
    //calculateResult();
  }
  
  public void setDataSensorMax(ArrayList<Integer> dataSensorMax){
    this.dataSensorMax = dataSensorMax;
  }
  
  public void calculateA(){
    double max = 0;
    double tmp = 0;

    for (int i = 0; i < dataSensorMax.size() - 1; i++){
      max = dataSensorMax.get(i);
      ArrayList<Double> tmpA = new ArrayList<>();
      for(int j = i+1; j < dataSensorMax.size(); j++){
          tmp = max/dataSensorMax.get(j);
          if(!Double.isNaN(tmp) && !Double.isInfinite(tmp)){
            tmp = new BigDecimal(tmp).setScale(2, RoundingMode.DOWN).doubleValue();
          }

          tmpA.add(tmp);


      }

    A.add(tmpA);
    }
  }

  public void calculateSignalParts() {
      double tmpSignal = 0;
      int maxSensorSum = 0;
      for (int i = 0; i < dataSensorMax.size(); i++){
        maxSensorSum += dataSensorMax.get(i);
      }

    for (int i = 0; i < dataSensorMax.size(); i++){
      tmpSignal = dataSensorMax.get(i)/maxSensorSum;
      signalParts.add(tmpSignal);
    }
  }

  public void calculateResult(){
    calculateA();
    calculateSignalParts();
  }
  public ArrayList<ArrayList<Double>> getA() {
    return A;
  }

  public ArrayList<Double> getSignalParts() {
    return signalParts;
  }

  public double getA1_2(){return A.get(0).get(0);}
  public double getA1_3(){return A.get(0).get(1);}
  public double getA1_4(){return A.get(0).get(2);}
  public double getA1_5(){return A.get(0).get(3);}
  public double getA1_6(){return A.get(0).get(4);}
  public double getA1_7(){return A.get(0).get(5);}
  public double getA1_8(){return A.get(0).get(6);}
  
  public double getA2_3(){return A.get(1).get(0);}
  public double getA2_4(){return A.get(1).get(1);}
  public double getA2_5(){return A.get(1).get(2);}
  public double getA2_6(){return A.get(1).get(3);}
  public double getA2_7(){return A.get(1).get(4);}
  public double getA2_8(){return A.get(1).get(5);}

  public double getA3_4(){return A.get(2).get(0);}
  public double getA3_5(){return A.get(2).get(1);}
  public double getA3_6(){return A.get(2).get(2);}
  public double getA3_7(){return A.get(2).get(3);}
  public double getA3_8(){return A.get(2).get(4);}

  public double getA4_5(){return A.get(3).get(0);}
  public double getA4_6(){return A.get(3).get(1);}
  public double getA4_7(){return A.get(3).get(2);}
  public double getA4_8(){return A.get(3).get(3);}

  public double getA5_6(){return A.get(4).get(0);}
  public double getA5_7(){return A.get(4).get(1);}
  public double getA5_8(){return A.get(4).get(2);}

  public double getA6_7(){return A.get(5).get(0);}
  public double getA6_8(){return A.get(5).get(1);}

  public double getA7_8(){return A.get(6).get(0);}














}
