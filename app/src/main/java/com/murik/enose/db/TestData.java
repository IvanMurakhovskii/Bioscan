package com.murik.enose.db;

import com.murik.enose.db.model.Measure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestData {
    public static List<Measure> getMeasures(int sessionIndex){
        List<Measure> measures = new ArrayList<>();
        measures.add(new Measure(new Date(), sessionIndex, 0, 0,9999990, 0, 500));
        measures.add(new Measure(new Date(), sessionIndex, 1, 0,9999991, 1, 500));
        measures.add(new Measure(new Date(), sessionIndex, 0, 0,9999992, 2, 500));
        measures.add(new Measure(new Date(), sessionIndex, 1, 0,9999993, 3, 500));
        measures.add(new Measure(new Date(), sessionIndex, 0, 0,9999994, 4, 500));
        measures.add(new Measure(new Date(), sessionIndex, 1, 0,9999995, 5, 500));
        measures.add(new Measure(new Date(), sessionIndex, 0, 0,9999996, 6, 500));
        measures.add(new Measure(new Date(), sessionIndex, 1, 0,9999997, 7, 500));
        measures.add(new Measure(new Date(), sessionIndex, 0, 0,9999998, 8, 500));
        measures.add(new Measure(new Date(), sessionIndex, 1, 0,9999999, 9, 500));
        return measures;
    }
}
