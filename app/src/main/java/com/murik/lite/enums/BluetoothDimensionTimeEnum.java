package com.murik.lite.enums;

import com.murik.lite.configuration.AuthService;

import java.util.Arrays;

import lombok.Getter;

@Getter
//todo сделать один enum
public enum BluetoothDimensionTimeEnum {
     _60(60, 72, BluetoothDimensionAlgorithm.BASE),
    _30(30,   80, BluetoothDimensionAlgorithm.SIMPLE),
    _160(60, 160, BluetoothDimensionAlgorithm.ADVANCED),
    _200(80, 200, BluetoothDimensionAlgorithm._200);

    private int maxSignalTime;
    private int dimensionTime;
    private BluetoothDimensionAlgorithm algorithm;

    public static BluetoothDimensionTimeEnum[] getValuesByRole() {
        if (AuthService.getInstance().isAdmin()) {
            return BluetoothDimensionTimeEnum.values();
        }
        return new BluetoothDimensionTimeEnum[]{BluetoothDimensionTimeEnum._60, BluetoothDimensionTimeEnum._30};
    }

    public static BluetoothDimensionTimeEnum getByAlgorithm(BluetoothDimensionAlgorithm algorithm) {
        for (BluetoothDimensionTimeEnum v : BluetoothDimensionTimeEnum.values()) {
            if (v.getAlgorithm() == algorithm) {
                return v;
            }
        }

        return null;
    }

    BluetoothDimensionTimeEnum(int maxSignalTime, int dimensionTime, BluetoothDimensionAlgorithm algorithm) {
        this.maxSignalTime = maxSignalTime;
        this.dimensionTime = dimensionTime;
        this.algorithm = algorithm;
    }
}
