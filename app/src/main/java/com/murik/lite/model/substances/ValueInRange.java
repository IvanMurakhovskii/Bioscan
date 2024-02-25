package com.murik.lite.model.substances;

public class ValueInRange {
    private final double value;
    private final double min;
    private final double max;

    public ValueInRange(double value, double min, double max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public boolean isValueInRange() {
        return value >= min && value <= max;
    }
}
