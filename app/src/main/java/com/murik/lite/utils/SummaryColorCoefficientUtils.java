package com.murik.lite.utils;

import android.graphics.Color;

import static com.murik.lite.helper.ColorHelper.*;

import lombok.experimental.UtilityClass;


@UtilityClass
public class SummaryColorCoefficientUtils {
    public static double getColorCoefficient(int color) {
        if(color == getColorGREEN()) return 1;
        if(color == getColorYELLOW()) return 1.5;
        if(color == getColorRED()) return 2;
        if(color == getColorORANGE()) return 2.25;
        if(color == getColorBURGUNDY()) return 2.5;
        if(color == Color.WHITE) return 3;
        return 1F;
    }
}
