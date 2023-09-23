package com.murik.lite.model;

import com.murik.lite.Const;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lombok.val;

import static com.murik.lite.service.Impl.BaseMeasureService.getAreaByMask;

public class SensorValueAttitudeFor30 {
    List<Integer> resultData;

    public SensorValueAttitudeFor30(List<Integer> resultData) {
        this.resultData = resultData;
    }

    public Double calculateA(int firstTime, int secondTime) {
        double firstSensValue = resultData.get(firstTime - 1);
        double secondSensValue = resultData.get(secondTime - 1);
        try {
            Double result = firstSensValue / secondSensValue;
            if (!Double.isNaN(result) && !Double.isInfinite(result)) {
                if (result > 1.0) {
                    result = new BigDecimal(result).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
                } else {
                    result = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
                }
            }
            return result;
        } catch (Exception e) {
            return -9999D;
        }
    }

    public Float calculateAndGetS15_30() {
        try {
            val s30 = getAreaByMask(Const.MASK_30, resultData);
            val s15 = getAreaByMask(Const.MASK_15, resultData);

            return s15/s30;
        } catch (Exception e) {
            return -9999F;
        }

    }


    public double getA10_20() {
        try {
            return calculateA(10, 20);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getA15_30() {
        try {
            return calculateA(15, 30);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getA20_30() {
        try {
            return calculateA(20, 30);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getA20_40() {
        try {
            return calculateA(20, 40);
        } catch (Exception e) {
            return -9999;
        }
    }

    public double getA50_30() {
        try {
            return calculateA(50, 30);
        } catch (Exception e) {
            return -9999;
        }
    }
}
