package com.murik.enose.model.summary;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

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
        } else if (a >= 78 && a <= 85) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.SUMMARY_30_GREEN_2));
        } else if (a >= 70 && a <= 78) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.SUMMARY_30_YELLOW));
        } else if (a >= 65.1 && a <= 69) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.SUMMARY_30_YELLOW_2));
        } else if (a >= 60 && a <= 65) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.SUMMARY_30_ORANGE));
        } else if (a >= 55 && a <= 59) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.SUMMARY_30_ORANGE_2));
        } else if (a > 49 && a <= 54.9) {
            setColorRED();
            setPossibleReasons(getResources(R.string.SUMMARY_30_RED));
        } else if (a >= 34 && a <= 49) {
            setColorRED();
            setPossibleReasons(getResources(R.string.SUMMARY_30_RED_2));
        }  else if (a >= 10.1 && a <= 33.9) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.SUMMARY_30_BURGUNDY));
        } else if (a < 10) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.SUMMARY_30_WHITE));
        }
    }
}
