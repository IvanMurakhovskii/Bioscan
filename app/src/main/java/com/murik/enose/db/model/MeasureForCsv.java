package com.murik.enose.db.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeasureForCsv {
    private int id;
    private int sessionId;
    private int deviceType;
    private int measureTime;
    private String description;
    private int sensorIndex;
    private long timeFromStartOfMeasure;
    private long diffValue;
    private long value;

    public List<String> getRowForCsv(){
        List<String> result = new ArrayList<>();
        result.add(id + "");
        result.add(sessionId + "");
        result.add(deviceType + "");
        result.add(measureTime + "");
        result.add(description);
        result.add(timeFromStartOfMeasure + "");
        result.add(diffValue + "");
        result.add(value + "");
        return result;
    }
}
