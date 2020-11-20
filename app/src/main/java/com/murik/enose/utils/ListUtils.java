package com.murik.enose.utils;

import java.util.List;

public class ListUtils {
    public static <T> boolean listSizeCondition(List<T> list, int size) {
        return list != null && list.size() > size;
    }
}
