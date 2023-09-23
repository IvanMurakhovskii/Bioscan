package com.murik.lite.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lombok.val;

public class MathUtils {

    public static double round(double value) {
        if (value > 1.0) {
            return new BigDecimal(value).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
        } else {
            return new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        }
    }

    public static Double divide(Double a1, Double a2) {
        return a2 == 0 ? -9999F : round( a1 / a2);
    }

    public static Double divide(Integer a1, Integer a2) {
        return divide((double) a1, (double) a2);
    }

    public static int findClosestValueIndex(List<Integer> list, Double searchValue) {

        double closestDiff = Integer.MAX_VALUE;
        int closestDiffIndex = 0;

        for(int i = 0; i < list.size(); i++) {
            double tmpDiff = Math.abs(searchValue - list.get(i));

            if(tmpDiff <= closestDiff) {
                closestDiff = tmpDiff;
                closestDiffIndex = i;
            }
        }

        return closestDiffIndex;
    }

    public static void inverseListValueIfMiddleValueBelowZero(List<Integer> data) {
        val size = data.size();

        if (size > 0) {
            val middleValue = data.get(size / 2);
            if (middleValue < 0) inverseListValues(data);
        }
    }

    public static void inverseListValues(List<Integer> data) {

        for (int i = 0; i < data.size(); i++) {
            val value = -data.get(i);
            data.set(i, value);
        }

    }

    public static Integer getData(List<Integer> data, int index) {
        if (data == null || index >= data.size()) return 0;
        return data.get(index);
    }

    public static Integer max(List<Integer> data) {
        if (data == null || data.size() == 0) {
           return 0;
        }

        int max = data.get(0);

        for (int el : data) {
            if (el > max) max = el;
        }

        return max;
    }
}
