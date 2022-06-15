package com.murik.enose.utils;

import java.util.List;

public class ListUtils {
    public static <T> boolean listSizeCondition(List<T> list, int size) {
        return list != null && list.size() > size;
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
}
