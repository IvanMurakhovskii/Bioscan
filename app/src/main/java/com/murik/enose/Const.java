package com.murik.enose;

import android.support.annotation.IntRange;
import android.util.Range;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Const {
  public static final int GENDER_FEMININE = 0;
  public static final int GENDER_MALE = 1;
  public static final int LEFT_HAND = 0;
  public static final int RIGHT_HAND = 1;
  public static final int MEAN_HAND = 2;

  public static final String SENSOR_1 = "SID0001";
  public static final String SENSOR_2 = "SID0002";
  public static final String SENSOR_3 = "SID0003";
  public static final String SENSOR_4 = "SID0004";
  public static final String SENSOR_5 = "SID0005";
  public static final String SENSOR_6 = "SID0006";
  public static final String SENSOR_7 = "SID0007";
  public static final String SENSOR_8 = "SID0008";

  //all sensors
  public static final int[] TOTAL = {30, 45, 60, 80, 100, 120, 180};
  public static final int[] HEALTH = {150, 160, 170};
  public static final int[] ENERGY = {110, 120, 130, 140, 150};
  public static final int[] BAD = {20, 30, 180};
  public static final int[] ENDOKRIN = {10, 20, 30, 60};

  //one sensor measure
  public static final int[] SHORT = {30, 60, 90, 110, 120};
  public static final int[] LONG = {40, 60, 80, 120, 140};
  public static final int[] BODY = range(10, 60);
  public static final int[] LUNGS = range(10, 20);
  public static final int[] DANGER = range(140, 160);

  public static final int PAGE_SHORT = 0;
  public static final int PAGE_LONG = 1;
  public static final int PAGE_BODY = 2;
  public static final int PAGE_LUNGS = 3;
  public static final int PAGE_DANGER = 4;

  public static final List<String> energySens = Arrays.asList(Const.SENSOR_1, Const.SENSOR_3, Const.SENSOR_7);
  public static final List<String> endocrinSens = Arrays.asList(Const.SENSOR_3, Const.SENSOR_4, Const.SENSOR_5, Const.SENSOR_7);
  public static final List<String> allSens = Arrays.asList(Const.SENSOR_1, Const.SENSOR_2, Const.SENSOR_3, Const.SENSOR_4, Const.SENSOR_5, Const.SENSOR_6, Const.SENSOR_7, Const.SENSOR_8);
  public static final List<String> allSensWithoutEight = Arrays.asList(Const.SENSOR_1, Const.SENSOR_2, Const.SENSOR_3, Const.SENSOR_4, Const.SENSOR_5, Const.SENSOR_6, Const.SENSOR_7);


  public static final int PAGE_TOTAL = 0;
  public static final int PAGE_ENDOKRIN = 1;
  public static final int PAGE_ENERGY = 2;
  public static final int PAGE_HEALTH = 3;
  public static final int PAGE_BAD = 4;

  public static final int SELECT_LEFT_FILE = 111;
  public static final int SELECT_RIGHT_FILE = 112;

  public static final int STANDARD_MEASURE_TYPE = 0;
  public static final int ONE_SENSOR_MEASURE_TYPE = 1;

  public static final String STANDARD_MEASURE = "STANDARD_MEASURE";
  public static final String FIRST_MEASURE = "FIRST_MEASURE";
  public static final String SECOND_MEASURE = "SECOND_MEASURE";


  private static int[] range(int start, int length) {
    int[] range = new int[length - start + 1];
    for (int i = start; i <= length; i++) {
      range[i - start] = i;
    }
    return range;
  }
}
