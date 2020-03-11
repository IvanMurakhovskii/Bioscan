package com.murik.enose.model;

import com.murik.enose.Const;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class OneSensorShortMeasure {

    private List<Integer> firstData = new ArrayList<>();

    private ArrayList<ArrayList<Float>> A = new ArrayList<>();

    public OneSensorShortMeasure(List<Integer> resultData) {

        if (resultData != null && resultData.size() > Const.SHORT[Const.SHORT.length - 1]) {
            for (int i = 0; i < Const.SHORT.length; i++) {
                firstData.add(resultData.get(Const.SHORT[i]));
            }
        }
        calculateA();
    }

    public void calculateA(){
        float max;
        float tmp;

        for (int i = 0; i < firstData.size(); i++){
            max = firstData.get(i);
            ArrayList<Float> tmpA = new ArrayList<>();
            for(int j = 0; j < firstData.size(); j++){
                tmp = max/firstData.get(j);
                if(!Double.isNaN(tmp) && !Double.isInfinite(tmp)){
                    if(tmp > 1.0){
                        tmp = new BigDecimal(tmp).setScale(1, RoundingMode.HALF_EVEN).floatValue();
                    } else {
                        tmp = new BigDecimal(tmp).setScale(2, RoundingMode.HALF_EVEN).floatValue();
                    }
                }

                tmpA.add(tmp);
            }
            A.add(tmpA);
        }
    }

    public float getFirstA1_2() {
        return A.get(0).get(1);
    }

    public float getFirstA2_3() {
        return A.get(1).get(2);
    }

    public float getFirstA2_4() {
        return A.get(1).get(3);
    }

    public float getFirstA2_5() {
        return A.get(1).get(4);
    }

    public float getFirstA1_5() {
        return A.get(0).get(4);
    }

    public float getFirstA4_5() {
        return A.get(3).get(4);
    }

    public float getFirstA5_4() {
        return A.get(4).get(3);
    }
}
