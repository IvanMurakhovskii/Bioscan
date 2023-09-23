package com.murik.lite.enums;

import lombok.Getter;

@Getter
public enum BluetoothDimensionTimeEnum {
     _60(60, 72, BluetoothDimensionAlgorithm.BASE),
    _30(30,   80, BluetoothDimensionAlgorithm.SIMPLE),
    _160(60, 160, BluetoothDimensionAlgorithm.ADVANCED);

    private int maxSignalTime;
    private int dimensionTime;
    private BluetoothDimensionAlgorithm algorithm;

    BluetoothDimensionTimeEnum(int maxSignalTime, int dimensionTime, BluetoothDimensionAlgorithm algorithm) {
        this.maxSignalTime = maxSignalTime;
        this.dimensionTime = dimensionTime;
        this.dimensionTime = dimensionTime;
        this.algorithm = algorithm;
    }
}
