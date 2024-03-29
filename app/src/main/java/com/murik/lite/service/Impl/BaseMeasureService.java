package com.murik.lite.service.Impl;

import com.murik.lite.Const;
import com.murik.lite.dto.OneSensorResultParametersDto;
import com.murik.lite.enums.BluetoothDimensionAlgorithm;
import com.murik.lite.utils.ListUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lombok.val;

public class BaseMeasureService {
    public static Double getAreaByMask(int[] mask, List<Integer> data) {
        double area = 0d;
        double dx;
        int y1;
        int y2;
        if (data != null) {
            for (int key = 0; key < mask.length - 1; key++) {
                if (data.size() >= mask[key]) {
                    dx = mask[key] - mask[key + 1];
                    if (!data.isEmpty()) {
                        y1 = ListUtils.listSizeCondition(data, mask[key]) ? data.get(mask[key]) : 0;
                        y2 = ListUtils.listSizeCondition(data, mask[key + 1]) ? data.get(mask[key + 1]) : 0;
                        if (y1 * y2 > 0) {
                            area += Math.abs((y1 + y2) / 2f * dx);
                        } else {
                            double dx1 = (1f + Math.abs(y2) * dx);
                            area += Math.abs((dx1 * Math.abs(y1) + (dx - dx1) * Math.abs(y2)) / 2f);
                        }
                    }
                }
            }
        }
        return area;
    }

    public static Float calculateDelta(final Float area, final Float areaTotal) {

        float delta;

        delta = (area / areaTotal) * 100;

        if (Float.isInfinite(delta) || Float.isNaN(delta)) {
            delta = 0;
        }
        return delta;
    }

    // middle value depend on measure type - 30, 60 or 80
    public static int calculateDeltaTau(final int maxSignalTime, final List<Integer> data) {
        if (data.size() < maxSignalTime) return -1;

        double halfMiddleValue = data.get(maxSignalTime) / 2d;

        List<Integer> firstPartArray = data.subList(0, maxSignalTime);
        List<Integer> secondPartArray = data.subList(maxSignalTime, data.size());

        int firstMiddleTime = ListUtils.findClosestValueIndex(firstPartArray, halfMiddleValue);
        int secondMiddleTime = ListUtils.findClosestValueIndex(secondPartArray, halfMiddleValue) + maxSignalTime;

        return (secondMiddleTime - firstMiddleTime);
    }

    public static int calculateT(final int maxSignalTime, final List<Integer> data) {
        if (data.size() <= maxSignalTime) return -1;

        double halfMiddleValue = data.get(maxSignalTime) / 2d;

        List<Integer> firstPartArray = data.subList(0, maxSignalTime);

        int firstMiddleTime = ListUtils.findClosestValueIndex(firstPartArray, halfMiddleValue);

        return (maxSignalTime - firstMiddleTime) * 2;
    }

    public static int calculateT_80(final int maxSignalTime, final List<Integer> data) {
        if (data.size() <= maxSignalTime) return -1;

        double halfMiddleValue = data.get(maxSignalTime) / 2d;

        List<Integer> firstPartArray = data.subList(0, maxSignalTime);

        int firstMiddleTime = ListUtils.findClosestValueIndex(firstPartArray, halfMiddleValue);

        return firstMiddleTime * 2;
    }

    public static Double calculateDifference(final double a, final double b) {
        double result;
        if (a > b) {
            result = ((a - b) / a) * 100;
        } else {
            result = ((b - a) / b) * 100;
        }

        if (Double.isInfinite(result) || Double.isNaN(result)) {
            result = 0;
        }

        return result;
    }

    public static Double calculateDifferenceLeftRight(final double left, final double right) {
        double result = ((left - right) / ((left + right) / 2)) * 100;

        if (Double.isInfinite(result) || Double.isNaN(result)) {
            result = 0;
        }

        return result;
    }

    public static Double getPS3425(final List<Integer> data, final int[] mask) {
        if (data != null && !data.isEmpty() && mask.length >= 4 && data.size() > mask[mask.length - 1]) {
            return ((double) (data.get(mask[2]) * data.get(mask[3])) / (double) (data.get(mask[1]) * data.get(mask[4])));
        }
        return 0d;
    }

    public static Double getPS2435(final List<Integer> data, final int[] mask) {
        if (data != null && !data.isEmpty() && mask.length >= 4 && data.size() > mask[mask.length - 1]) {
            return ((double) (data.get(mask[1]) * data.get(mask[3])) / (double) (data.get(mask[2]) * data.get(mask[4])));
        }
        return 0d;
    }

    public static Float getAreaDifference(final Float areaLeft, final Float areaRight) {
        if (areaLeft != 0 && areaRight != 0) {
            Float areaTotalAverage = (areaLeft + areaRight) / 2;
            Float areaTotalDifference = 2 * ((areaLeft - areaTotalAverage) / areaTotalAverage) * 100;
            BigDecimal bd = new BigDecimal(areaTotalDifference).setScale(1, RoundingMode.FLOOR);
            return Math.abs(bd.floatValue());
        }
        return 0F;
    }

    public static OneSensorResultParametersDto getOneSensorResultParameters(List<Integer> sensorData, BluetoothDimensionAlgorithm algorithm) {

        if (sensorData.size() >= 70) {
            val a40 = sensorData.get(40);
            val a70 = sensorData.get(70);

            val a_40_70 = a70 == 0 ? 0 : a40 / a70;
        }

        val s60 = getAreaByMask(Const.MASK_60, sensorData);
        val s30 = getAreaByMask(Const.MASK_30, sensorData);
        val s20 = getAreaByMask(Const.MASK_20, sensorData);
        Double S_ENERGY = 0d;
        Double S_DISCRETE = 0d;
        Double S_BODY = 0d;

        if (algorithm.equals(BluetoothDimensionAlgorithm.SIMPLE)) {
            S_BODY = getAreaByMask(Const.BODY_30, sensorData);
            S_ENERGY = getAreaByMask(Const.ENERGY_30, sensorData);
            S_DISCRETE = getAreaByMask(Const.DISCRETE_30, sensorData);
        } else if (algorithm.equals(BluetoothDimensionAlgorithm.BASE) || algorithm.equals(BluetoothDimensionAlgorithm.ADVANCED)) {
            S_ENERGY = getAreaByMask(Const.ENERGY_160, sensorData);
            S_DISCRETE = getAreaByMask(Const.DISCRETE_160, sensorData);
            S_BODY = getAreaByMask(Const.BODY, sensorData);
        } else if (algorithm.equals(BluetoothDimensionAlgorithm._200)) {
            S_ENERGY = getAreaByMask(Const.ENERGY, sensorData);
            S_DISCRETE = getAreaByMask(Const.DISCRETE, sensorData);
            S_BODY = getAreaByMask(Const.BODY, sensorData);
        }
        val En = round(S_ENERGY / S_DISCRETE);
        val E2 = round(S_BODY / S_DISCRETE);

        val L = round(S_BODY / S_DISCRETE);

        return new OneSensorResultParametersDto()
                .setS20_30(round(s20 / s30))
                .setS20_60(round(s20 / s60))
                .setS30_60(round(s30 / s60))
                .setEn(En)
                .setE2(E2)
                .setL(L);
    }

    public static Double round(Double value) {
        if (value > 1.0) {
            return new BigDecimal(value).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
        } else {
            return new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        }
    }
}
