package com.murik.enose.enums;

public enum BluetoothDimensionTimeEnum {
    _15_DUAL_SENS(15, 15),
    _30(30, 80),
    _30_DUAL_SENS(30, 30),
    _60(60, 160),
    _60_DUAL_SENS(60, 60),
    _80(80, 200),
    _300_DUAL_SENS(300, 300),
    _600_DUAL_SENS(600, 600),
    _1800_DUAL_SENS(1800, 1800);

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
