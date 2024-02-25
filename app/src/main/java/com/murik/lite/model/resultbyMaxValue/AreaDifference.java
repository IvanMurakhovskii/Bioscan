package com.murik.lite.model.resultbyMaxValue;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.murik.lite.R;

public abstract class AreaDifference {
    private double areaDifference;
    private int color;
    private String possibleReasons = "норма";
    private Context context;
    private int gender;

    public AreaDifference(double areaDifference, int gender, Context context) {
        this.areaDifference = areaDifference;
        this.context = context;
        this.gender = gender;
        setColorGREEN();

        if (Double.isNaN(areaDifference) || Double.isInfinite(areaDifference)) {
            color = Color.WHITE;
            return;
        }
        setResult();
    }

    public abstract void setResult();

    public double getAreaDifference() {
        return areaDifference;
    }

    public int getGender() {
        return gender;
    }

    public int getViewColor() {
        return color;
    }

    public String getResultComment() {
        return possibleReasons;
    }

    public void setColorGRAY() {
        color = Color.GRAY;
    }

    public void setColorBURGUNDY() {
        color = ContextCompat.getColor(context, R.color.colorResultBurgundy);
    }

    public void setColorRED() {
        color = ContextCompat.getColor(context, R.color.colorResultRed);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setColorCRIMSON() {
        color = ContextCompat.getColor(context, R.color.colorResultCrimson);
    }

    public void setColorYELLOW() {
        color = ContextCompat.getColor(context, R.color.colorResultYellow);
    }

    public void setColorGREEN() {
        color = ContextCompat.getColor(context, R.color.colorResultGreen);
    }

    public void setColorBLUE() {
        color = ContextCompat.getColor(context, R.color.colorResultBlue);
    }

    public void setPossibleReasons(String possibleReasons) {
        this.possibleReasons = possibleReasons;
    }

    public String getResources(int idRes) {
        return context.getResources().getString(idRes);
    }

}
