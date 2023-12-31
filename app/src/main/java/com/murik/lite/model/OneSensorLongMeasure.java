package com.murik.lite.model;

import com.murik.lite.Const;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class OneSensorLongMeasure {

    private List<Integer> secondData = new ArrayList<>();

    private ArrayList<ArrayList<Double>> A = new ArrayList<>();

    public OneSensorLongMeasure(List<Integer> resultData) {
        if (resultData != null && resultData.size() > Const.LONG[Const.LONG.length - 1]) {
            for (int i = 0; i < Const.LONG.length; i++) {
                secondData.add(resultData.get(Const.LONG[i]));
            }
            calculateA();
        }
    }

    public void calculateA() {
        double max;
        Double tmp;

        for (int i = 0; i < secondData.size(); i++) {
            max = secondData.get(i);
            ArrayList<Double> tmpA = new ArrayList<>();
            for (int j = 0; j < secondData.size(); j++) {
                tmp = max / secondData.get(j);
                if (!Double.isNaN(tmp) && !Double.isInfinite(tmp)) {
                    if (tmp > 1.0) {
                        tmp = new BigDecimal(tmp).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
                    } else {
                        tmp = new BigDecimal(tmp).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
                    }
                }

                tmpA.add(tmp);
            }
            A.add(tmpA);
        }
    }

    public double getSecondA1_2() {
        try {
            return A.get(0).get(1);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA1_3() {
        try {
            return A.get(0).get(2);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA3_1() {
        try {
            return A.get(2).get(0);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA2_3() {
        try {
            return A.get(1).get(2);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA3_4() {
        try {
            return A.get(2).get(3);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA3_5() {
        try {
            return A.get(2).get(4);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA5_4() {
        try {
            return A.get(4).get(3);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA1_4() {
        try {
            return A.get(0).get(3);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA2_4() {
        try {
            return A.get(1).get(3);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA1_5() {
        try {
            return A.get(0).get(4);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA2_5() {
        try {
            return A.get(1).get(4);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA4_6() {
        try {
            return A.get(3).get(5);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA4_8() {
        try {
            return A.get(3).get(7);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA6_8() {
        try {
            return A.get(5).get(7);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getSecondA9_6() {
        try {
            return A.get(8).get(5);
        } catch (Exception e) {
            return -9999;
        }
    }
}
