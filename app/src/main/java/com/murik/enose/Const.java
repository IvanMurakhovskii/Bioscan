package com.murik.enose;

import android.graphics.Color;

import java.util.Arrays;
import java.util.List;

public class Const {
  public static final int GENDER_FEMININE = 0;
  public static final int GENDER_MALE = 1;
  public static final int LEFT_HAND = 0;
  public static final int RIGHT_HAND = 1;
  public static final int MEAN_HAND = 2;

  public static final int BIOSCANNER_DEVICE_TYPE = 0;
  public static final int DUAL_SENSOR_DEVICE_TYPE = 1;

  public static final String SENSOR_1 = "SID0001";
  public static final String SENSOR_2 = "SID0002";
  public static final String SENSOR_3 = "SID0003";
  public static final String SENSOR_4 = "SID0004";
  public static final String SENSOR_5 = "SID0005";
  public static final String SENSOR_6 = "SID0006";
  public static final String SENSOR_7 = "SID0007";
  public static final String SENSOR_8 = "SID0008";

  public static final String[] SENSORS_SID = {"SID0001", "SID0002", "SID0003", "SID0004", "SID0005", "SID0006", "SID0007", "SID0008"};
  public static final String[] SENSORS_SID_ENDOKRIN = { "SID0003", "SID0004", "SID0005", "SID0007"};
  public static final String[] SENSORS_SID_ENERGY = {"SID0001", "SID0003","SID0007"};


  //all sensors
  public static final int[] TOTAL = {30, 45, 60, 80, 100, 120, 180};
  public static final int[] HEALTH = {150, 160, 170};

  public static final int[] ENERGY = {110, 120, 130, 140, 150};
  public static final int[] ENERGY_60 = {85, 90, 100, 105, 110};
  public static final int[] ENERGY_30 = {40, 45, 50, 55, 60};
  public static final int[] BAD = {20, 30, 180};
  public static final int[] ENDOKRIN = {10, 20, 30, 60};

  //one sensor measure
  public static final int[] SHORT = {30, 60, 90, 110, 120}; //короткая
  public static final int[] LONG = {40, 60, 80, 120, 140};  //длинная
  public static final int[] BODY = range(10, 60);
  public static final int[] BODY_30 = range(4, 30);
  public static final int[] LUNGS = range(10, 20);
  public static final int[] DANGER = range(140, 160);
  public static final int[] DISCRETE = {30, 40, 60, 80, 90, 110, 120, 140};
  public static final int[] DISCRETE_60 = {30, 40, 60, 80, 90, 110, 120};
  public static final int[] DISCRETE_30 = { 15, 20, 25, 30, 40, 50, 55};

  public static final int[] MASK_60 = range(0, 60);
  public static final int[] MASK_30 = range(0, 30);
  public static final int[] MASK_20 = range(0, 20);
  public static final int[] MASK_15 = range(0, 15);

  public static final int PAGE_SHORT = 0;
  public static final int PAGE_LONG = 1;
  public static final int PAGE_LUNGS = 3;
  public static final int PAGE_DANGER = 4;

  public static final int PAGE_DISCRETE = 0;
  public static final int PAGE_BODY = 1;
  public static final int PAGE_ENERGY_ONE_SENSOR = 2;


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
  public static final int CHART = 2;

  public static final String STANDARD_MEASURE = "STANDARD_MEASURE";
  public static final String ONE_SENSOR_MEASURE = "ONE_SENSOR_MEASURE";
  public static final String FIRST_MEASURE = "FIRST_MEASURE";
  public static final String SECOND_MEASURE = "SECOND_MEASURE";


  public static final String DIAGNOST = "DIAGNOSE"; // НД
  public static final String BIOSCANER = "BIOSCANER"; // OPS
  public static final String DUAL_SENSOR = "DOUBLE_SENSOR";

  private static int[] range(int start, int length) {
    int[] range = new int[length - start + 1];
    for (int i = start; i <= length; i++) {
      range[i - start] = i;
    }
    return range;
  }
}
