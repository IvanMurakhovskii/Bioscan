package com.murik.enose.enums;

public enum BluetoothDimensionTimeEnum {
    _30(30, 80),
    _60(60, 160),
    _80(80, 200);

    private int maxSignalTime;

    public int getMaxSignalTime() {
        return maxSignalTime;
    }

    public int getDimensionTime() {
        return dimensionTime;
    }

    private int dimensionTime;
    BluetoothDimensionTimeEnum(int maxSignalTime, int dimensionTime) {
        this.maxSignalTime = maxSignalTime;
        this.dimensionTime = dimensionTime;
    }
}
