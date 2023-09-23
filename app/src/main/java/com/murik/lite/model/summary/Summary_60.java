package com.murik.lite.model.summary;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.R;
import com.murik.lite.configuration.SettingsService;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.enums.SummaryTheme;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.Setter;
import lombok.val;

@Setter
@Getter
public class Summary_60 extends BaseResult {

    public Summary_60(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
    }

    public void setResult() {

        val settings = SettingsService.getInstance().getSummaryTheme();
        val a = BigDecimal.valueOf(getA()).setScale(0, RoundingMode.HALF_UP).intValue();
        if (a >= 94 && a <= 100) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.SUMMARY_60_GREEN));
            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_1);
            } else {
                setImageResId(R.drawable.green_2);
            }
        } else if (a >= 86 && a < 94) {
            setColorGREENLITE();
            setPossibleReasons(getResources(R.string.SUMMARY_60_GREEN_LITE));
            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_2);
            } else {
                setImageResId(R.drawable.green_lite);
            }
        } else if (a >= 78.5 && a < 86) {
            setColorGREENLITE();
            setPossibleReasons(getResources(R.string.SUMMARY_60_GREEN_LITE_1));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_3);
            } else {
                setImageResId(R.drawable.green_lite_2);
            }
        } else if (a >= 70 && a < 78.5) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.SUMMARY_60_YELLOW_1));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_4);
            } else {
                setImageResId(R.drawable.yellow);
            }
        } else if (a >= 65.1 && a < 70) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.SUMMARY_60_YELLOW_2));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_5);
            } else {
                setImageResId(R.drawable.yellow_2);
            }
        } else if (a >= 60 && a < 65.1) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.SUMMARY_60_ORANGE_1));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_6);
            } else {
                setImageResId(R.drawable.orange);
            }
        } else if (a >= 55 && a < 60) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.SUMMARY_60_ORANGE_2));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_7);
            } else {
                setImageResId(R.drawable.orange_2);
            }
        } else if (a > 49.1 && a < 55) {
            setColorRED();
            setPossibleReasons(getResources(R.string.SUMMARY_60_RED_1));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_8);
            } else {
                setImageResId(R.drawable.red);
            }
        } else if (a >= 34 && a < 49.1) {
            setColorRED();
            setPossibleReasons(getResources(R.string.SUMMARY_60_RED_2));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_9);
            } else {
                setImageResId(R.drawable.red_2);
            }
        } else if (a > 20 && a < 34) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.SUMMARY_60_BURGUNDY));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_10);
            } else {
                setImageResId(R.drawable.burgundy);
            }
        } else if (a > 10 && a < 20) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.SUMMARY_60_WHITE));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_11);
            } else {
                setImageResId(R.drawable.burgundy);
            }
        } else if (a <= 10) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.SUMMARY_60_WHITE_1));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_12);
            } else {
                setImageResId(R.drawable.white);
            }
        }
    }
}
