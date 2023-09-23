package com.murik.lite.service.Impl;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BaseMeasureServiceTest {

    @Test
    public void calculateDeltaTau() {
        List<Integer> testList = Arrays.asList(1, 4, 6, 4, 8, 9, 9, 11, 12, 15, 20, 19, 17, 13, 11, 10, 5, 4, 2, 1, 0);

        int result = BaseMeasureService.calculateDeltaTau(10, testList);

        assertEquals(result, 8);
    }
}