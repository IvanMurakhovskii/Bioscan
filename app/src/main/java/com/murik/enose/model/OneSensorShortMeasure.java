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
        try {
            return A.get(0).get(1);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA2_3() {
        try {
            return A.get(1).get(2);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA2_4() {
        try {
            return A.get(1).get(3);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA2_5() {
        try {
            return A.get(1).get(4);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA5_2() {
        try {
            return A.get(4).get(1);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA1_5() {
        try {
            return A.get(0).get(4);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA4_5() {
        try {
            return A.get(3).get(4);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA5_4() {
        try {
            return A.get(4).get(3);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA3_6() {
        try {
            return A.get(2).get(5);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA3_2() {
        try {
            return A.get(2).get(1);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA6_9() {
        try {
            return A.get(5).get(8);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA4_7() {
        try {
            return A.get(3).get(6);
        } catch (Exception e) {
            return -9999;
        }
    }

    public float getFirstA12_6() {
        try {
            return A.get(11).get(5);
        } catch (Exception e) {
            return -9999;
        }
    }
}
