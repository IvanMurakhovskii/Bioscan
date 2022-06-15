package com.murik.enose.db.model;

import java.util.Date;

import lombok.Data;

@Data
public class Measure {
    private int id;
    private Date createdWhen;
    private long sessionId;
    private int sensorIndex;
    private int hand;
    private long value;
    private long diffValue;
    private long timeFromStartOfMeasure;

    public String getSqlValueForInsertion(){
        return ("(" + createdWhen.getTime() + "," + sessionId + "," + sensorIndex + "," + hand + ","
                + value + "," + diffValue + "," + timeFromStartOfMeasure + ");\n");
    }

    public Measure(Date createdWhen, long sessionId, int sensorIndex, int hand, long value,
                   long diffValue, long timeFromStartOfMeasure) {
        this.createdWhen = createdWhen;
        this.sessionId = sessionId;
        this.sensorIndex = sensorIndex;
        this.hand = hand;
        this.value = value;
        this.diffValue = diffValue;
        this.timeFromStartOfMeasure = timeFromStartOfMeasure;
    }
}
