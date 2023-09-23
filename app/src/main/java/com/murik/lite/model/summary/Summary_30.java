package com.murik.lite.model.summary;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.val;

public class Summary_30 extends BaseResult {

    public Summary_30(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
    }

    public void setResult() {
        val a = BigDecimal.valueOf(getA()).setScale(0, RoundingMode.HALF_UP).intValue();
        if (a >= 85.1 && a <= 100) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.SUMMARY_30_GREEN));
        } else if (a >= 79.1 && a <= 85) {
            setColorGREENLITE();
            setPossibleReasons(getResources(R.string.SUMMARY_30_GREEN_2));
        } else if (a >=69.2 && a <= 79) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.SUMMARY_30_YELLOW));
        } else if (a >= 50 && a <= 69.1) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.SUMMARY_30_YELLOW_2));
        } else if (a >= 55 && a <= 59) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.SUMMARY_30_ORANGE_2));
        } else if (a > 30 && a <= 49.9) {
            setColorRED();
            setPossibleReasons(getResources(R.string.SUMMARY_30_RED));
        } else if (a >= 34 && a <= 49) {
            setColorRED();
            setPossibleReasons(getResources(R.string.SUMMARY_30_RED_2));
        }  else if (a >= 10 && a <= 30.2) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.SUMMARY_30_BURGUNDY));
        } else if (a < 10) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.SUMMARY_30_WHITE));
        }
    }
}
