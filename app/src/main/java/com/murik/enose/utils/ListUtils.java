package com.murik.enose.utils;

import java.util.Collections;
import java.util.List;

import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class ListUtils {
    public static <T> boolean listSizeCondition(List<T> list, int size) {
        return list != null && list.size() > size;
    }

    public static int findClosestValueIndex(List<Integer> list, Double searchValue) {

        double closestDiff = Integer.MAX_VALUE;
        int closestDiffIndex = 0;

        for (int i = 0; i < list.size(); i++) {
            double tmpDiff = Math.abs(searchValue - list.get(i));

            if (tmpDiff <= closestDiff) {
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
}
