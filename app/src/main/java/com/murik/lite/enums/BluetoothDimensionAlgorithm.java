package com.murik.lite.enums;

import com.murik.lite.configuration.AuthService;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BluetoothDimensionAlgorithm {
    SIMPLE(10, "Быстрый", "Применять для экстренных случаев", 30, 80),
    BASE(20, "Общий", "Основной алгоритм измерения", 60, 72),
    ADVANCED(30, "Расширенный", "Применять в случае недомогания или подозрения на него", 60, 160),
    _200(25, "Компетентный", "Наиболее подробная диагностика", 80, 200);

    private int algorithmId;
    private String name;
    private String description;
    private int maxSignalTime;
    private int dimensionTime;

    public static BluetoothDimensionAlgorithm[] getValuesByRole() {
        if (AuthService.getInstance().isAdmin()) {
            return BluetoothDimensionAlgorithm.values();
        }
        return new BluetoothDimensionAlgorithm[]{BluetoothDimensionAlgorithm.BASE, BluetoothDimensionAlgorithm.SIMPLE};
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
