package com.murik.lite.model.summary;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.val;

public class Summary_80 extends BaseResult {

    public Summary_80(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
    }

    public void setResult() {
        val a = BigDecimal.valueOf(getA()).setScale(0, RoundingMode.HALF_UP).intValue();
        if (a >= 85.1 && a <= 100) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.SUMMARY_80_GREEN));
        } else if (a >= 78.9 && a <= 85) {
            setColorGREENLITE();
            setPossibleReasons(getResources(R.string.SUMMARY_80_GREEN_LITE));
        } else if (a >= 70 && a <= 78) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.SUMMARY_80_YELLOW_1));
        } else if (a >= 65.1 && a <= 69) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.SUMMARY_80_YELLOW_2));
        } else if (a >= 60 && a <= 65) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.SUMMARY_80_ORANGE_1));
        } else if (a >= 55 && a <= 59) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.SUMMARY_80_ORANGE_2));
        } else if (a >= 50 && a <= 54.9) {
            setColorRED();
            setPossibleReasons(getResources(R.string.SUMMARY_80_RED_1));
        } else if (a >= 34 && a <= 49) {
            setColorRED();
            setPossibleReasons(getResources(R.string.SUMMARY_80_RED_2));
        }  else if (a >= 10 && a <= 33.9) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.SUMMARY_60_BURGUNDY));
        } else if (a < 10) {
            setColor(Color.WHITE);
            setPossibleReasons("Ошибка прибора, проверить сенсор");
        }

    }
}
