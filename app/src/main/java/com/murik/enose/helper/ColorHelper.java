package com.murik.enose.helper;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.murik.enose.App;
import com.murik.enose.R;

public class ColorHelper {

    public static int getColorPRIMARY_DARK() {
        return ContextCompat.getColor(App.getContext(), R.color.colorPrimaryDark);
    }

    public static int getColorGRAY() {
        return Color.GRAY;
    }

    public static int getColorBURGUNDY() {
        return ContextCompat.getColor(App.getContext(), R.color.colorResultBurgundy);
    }

    public static int getColorORANGE() {
        return ContextCompat.getColor(App.getContext(), R.color.orange);
    }

    public static int getColorRED() {
        return ContextCompat.getColor(App.getContext(), R.color.colorResultRed);
    }

    public static int getColorCRIMSON() {
        return ContextCompat.getColor(App.getContext(), R.color.colorResultCrimson);
    }

    public static int getColorPINK() {
        return ContextCompat.getColor(App.getContext(), R.color.fabColor);
    }

    public static int getColorYELLOW() {
        return ContextCompat.getColor(App.getContext(), R.color.colorResultYellow);
    }

    public static int getColorGREEN() {
        return ContextCompat.getColor(App.getContext(), R.color.colorResultGreen);
    }

    public static int getColorBlue() {
        return ContextCompat.getColor(App.getContext(), R.color.colorResultBlue);
    }

}
