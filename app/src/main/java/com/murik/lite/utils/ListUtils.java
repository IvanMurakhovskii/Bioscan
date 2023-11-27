package com.murik.lite.utils;

import java.util.List;

import lombok.val;

public class ListUtils {

    public static <T> boolean listSizeCondition(List<T> list, int size) {
        return list != null && list.size() > size;
    }

    public static boolean isDataSensorAllZero(List<Integer> data) {
        return (data.get(0) == 0 && data.get(data.size() / 2) == 0 && data.get(data.size() - 1) == 0);
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
