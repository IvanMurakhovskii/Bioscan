package com.murik.lite.model.resultbyMaxValue;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.murik.lite.R;
import com.murik.lite.model.ResultBySens;
import com.murik.lite.dto.DataByMaxParcelable;

import lombok.Getter;
import lombok.Setter;

public abstract class BaseResult implements ResultBySens {
    private double A;
    private int color = Color.WHITE;
    private String possibleReasons = "Проверить условия измерения! При повторе – очень опасно! Воспаление! Обратить внимание!";
    private Context context;
    private String legend;
    private DataByMaxParcelable inputData;

    @Getter
    @Setter
    private int imageResId = 0;

    public BaseResult(double A, DataByMaxParcelable inputData, Context context) {
        this.A = A;
        this.context = context;
        this.inputData = inputData;

        if (Double.isNaN(A) || Double.isInfinite(A)) {
            color = Color.WHITE;
            return;
        }
        setResult();
    }

    public DataByMaxParcelable getInputData() {
        return inputData;
    }

    @Override
    public abstract void setResult();

    @Override
    public int getViewColor() {
        return color;
    }

    @Override
    public String getResultComment() {
        return possibleReasons;
    }

    public double getA() {
        return A;
    }

    public void setA(float A) {
        this.A = A;
    }

    public void setColorPRIMARY_DARK() {
        color = ContextCompat.getColor(context, R.color.colorPrimaryDark);
    }

    public void setColorORANGE() {
        color = ContextCompat.getColor(context, R.color.orange);
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

    public void setColorPINK() {
        color = ContextCompat.getColor(context, R.color.fabColor);
    }

    public void setColorYELLOW() {
        color = ContextCompat.getColor(context, R.color.colorResultYellow);
    }

    public void setColorGREEN() {
        color = ContextCompat.getColor(context, R.color.green);
        possibleReasons = "";
    }

    public void setColorGREENLITE() {
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

    @Override
    public Context getContext() {
        return context;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    @Override
    public String getLegend() {
        return legend;
    }

}
