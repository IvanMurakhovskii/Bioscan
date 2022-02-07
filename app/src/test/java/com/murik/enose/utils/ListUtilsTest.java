package com.murik.enose.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListUtilsTest{
    @Test
    public void findClosestValueIndex_ShouldReturnValue() {
        List<Integer> testList = Arrays.asList(1,2,4,7,9,10,11);

        int resultIndex = 3;
        int value = ListUtils.findClosestValueIndex(testList, 6d);
        assertEquals(value, resultIndex);
    }

    @Test
    public void inverseListValueIfMiddleValueBelowZero_ShouldReturnInverseList() {
        List<Integer> testList = Arrays.asList(1,-2,-4,-7,-9,-10,11);



        ListUtils.inverseListValueIfMiddleValueBelowZero(testList);

        List<Integer> returnedList = Arrays.asList(-1,2,4,7,9,10,-11);
        assertEquals(testList, returnedList);
    }
}