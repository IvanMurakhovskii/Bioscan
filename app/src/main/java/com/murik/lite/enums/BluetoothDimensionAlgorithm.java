package com.murik.lite.enums;

import lombok.Getter;

@Getter
public enum BluetoothDimensionAlgorithm {
    SIMPLE(10, "Быстрый", "Применять для экстренных случаев"),
    BASE(20, "Общий", "Основной алгоритм измерения"),
    ADVANCED(30, "Расширенный", "Применять в случае недомогания или подозрения на него");

    private int algorithmId;
    private String name;
    private String description;

    BluetoothDimensionAlgorithm(int algorithmId, String name, String description) {
        this.algorithmId = algorithmId;
        this.name = name;
        this.description = description;
    }

    public static BluetoothDimensionAlgorithm getByAlgorithmId(Integer algorithmId) {
        for (BluetoothDimensionAlgorithm e : BluetoothDimensionAlgorithm.values()) {
            if (e.getAlgorithmId() == algorithmId) {
                return e;
            }
        }

        return null;
    }
}
