package com.murik.lite.model.summary.stress;

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
public class SecondStressSummary_60 extends BaseResult {

    public SecondStressSummary_60(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
    }

    public void setResult() {

        val settings = SettingsService.getInstance().getSummaryTheme();
        val a = BigDecimal.valueOf(getA()).setScale(0, RoundingMode.HALF_UP).intValue();
        if (a >= 0 && a <= 10) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.second_stress0));
            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_1);
            } else if (settings.equals(SummaryTheme.ABSTRACT)) {
                setImageResId(R.drawable.green_2);
            } else if (settings.equals(SummaryTheme.DREAM)) {
                setImageResId(R.drawable.dream_1);
            } else if (settings.equals(SummaryTheme.APRICOT)) {
                setImageResId(R.drawable.apricot_1);
            } else if (settings.equals(SummaryTheme.MARSHMALLOW)) {
                setImageResId(R.drawable.marshmallow_1);
            } else if (settings.equals(SummaryTheme.GNOMES)) {
                setImageResId(R.drawable.gnomes_1);
            } else if (settings.equals(SummaryTheme.SANTA_LETTER)) {
                setImageResId(R.drawable.santa_letter_1);
            } else if (settings.equals(SummaryTheme.GLASS_LION)) {
                setImageResId(R.drawable.glass_lion_1);
            }
        } else if (a >= 10 && a <= 20) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.second_stress1));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_4);
            } else if (settings.equals(SummaryTheme.ABSTRACT)) {
                setImageResId(R.drawable.yellow);
            } else if (settings.equals(SummaryTheme.DREAM)) {
                setImageResId(R.drawable.dream_4);
            } else if (settings.equals(SummaryTheme.APRICOT)) {
                setImageResId(R.drawable.apricot_4);
            } else if (settings.equals(SummaryTheme.MARSHMALLOW)) {
                setImageResId(R.drawable.marshmallow_4);
            } else if (settings.equals(SummaryTheme.GNOMES)) {
                setImageResId(R.drawable.gnomes_4);
            } else if (settings.equals(SummaryTheme.SANTA_LETTER)) {
                setImageResId(R.drawable.santa_letter_4);
            } else if (settings.equals(SummaryTheme.GLASS_LION)) {
                setImageResId(R.drawable.glass_lion_4);
            }
        } else if (a >= 20 && a <= 40) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.second_stress2));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_6);
            } else if (settings.equals(SummaryTheme.ABSTRACT)) {
                setImageResId(R.drawable.orange);
            } else if (settings.equals(SummaryTheme.DREAM)) {
                setImageResId(R.drawable.dream_6);
            } else if (settings.equals(SummaryTheme.APRICOT)) {
                setImageResId(R.drawable.apricot_6);
            } else if (settings.equals(SummaryTheme.MARSHMALLOW)) {
                setImageResId(R.drawable.marshmallow_6);
            } else if (settings.equals(SummaryTheme.GNOMES)) {
                setImageResId(R.drawable.gnomes_6);
            } else if (settings.equals(SummaryTheme.SANTA_LETTER)) {
                setImageResId(R.drawable.santa_letter_6);
            } else if (settings.equals(SummaryTheme.GLASS_LION)) {
                setImageResId(R.drawable.glass_lion_6);
            }
        } else if (a >= 40 && a <= 60) {
            setColorRED();
            setPossibleReasons(getResources(R.string.second_stress3));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_8);
            } else if (settings.equals(SummaryTheme.ABSTRACT)) {
                setImageResId(R.drawable.red);
            } else if (settings.equals(SummaryTheme.DREAM)) {
                setImageResId(R.drawable.dream_8);
            } else if (settings.equals(SummaryTheme.APRICOT)) {
                setImageResId(R.drawable.apricot_8);
            } else if (settings.equals(SummaryTheme.MARSHMALLOW)) {
                setImageResId(R.drawable.marshmallow_8);
            } else if (settings.equals(SummaryTheme.GNOMES)) {
                setImageResId(R.drawable.gnomes_8);
            } else if (settings.equals(SummaryTheme.SANTA_LETTER)) {
                setImageResId(R.drawable.santa_letter_8);
            } else if (settings.equals(SummaryTheme.GLASS_LION)) {
                setImageResId(R.drawable.glass_lion_8);
            }
        } else if (a >= 60 && a <= 80) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.second_stress4));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_9);
            } else if (settings.equals(SummaryTheme.ABSTRACT)) {
                setImageResId(R.drawable.red_2);
            } else if (settings.equals(SummaryTheme.DREAM)) {
                setImageResId(R.drawable.dream_9);
            } else if (settings.equals(SummaryTheme.APRICOT)) {
                setImageResId(R.drawable.apricot_9);
            } else if (settings.equals(SummaryTheme.MARSHMALLOW)) {
                setImageResId(R.drawable.marshmallow_9);
            } else if (settings.equals(SummaryTheme.GNOMES)) {
                setImageResId(R.drawable.gnomes_9);
            } else if (settings.equals(SummaryTheme.SANTA_LETTER)) {
                setImageResId(R.drawable.santa_letter_9);
            } else if (settings.equals(SummaryTheme.GLASS_LION)) {
                setImageResId(R.drawable.glass_lion_9);
            }
        } else if (a >= 80 && a <= 100) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.second_stress5));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_10);
            } else if (settings.equals(SummaryTheme.ABSTRACT)) {
                setImageResId(R.drawable.burgundy);
            } else if (settings.equals(SummaryTheme.DREAM)) {
                setImageResId(R.drawable.dream_10);
            } else if (settings.equals(SummaryTheme.APRICOT)) {
                setImageResId(R.drawable.apricot_10);
            } else if (settings.equals(SummaryTheme.MARSHMALLOW)) {
                setImageResId(R.drawable.marshmallow_10);
            } else if (settings.equals(SummaryTheme.GNOMES)) {
                setImageResId(R.drawable.gnomes_10);
            } else if (settings.equals(SummaryTheme.SANTA_LETTER)) {
                setImageResId(R.drawable.santa_letter_10);
            } else if (settings.equals(SummaryTheme.GLASS_LION)) {
                setImageResId(R.drawable.glass_lion_10);
            }
        } else if (a > 10 && a < 20) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.SUMMARY_60_WHITE));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_11);
            } else if (settings.equals(SummaryTheme.ABSTRACT)) {
                setImageResId(R.drawable.burgundy);
            } else if (settings.equals(SummaryTheme.DREAM)) {
                setImageResId(R.drawable.dream_11);
            } else if (settings.equals(SummaryTheme.APRICOT)) {
                setImageResId(R.drawable.apricot_11);
            } else if (settings.equals(SummaryTheme.MARSHMALLOW)) {
                setImageResId(R.drawable.marshmallow_11);
            } else if (settings.equals(SummaryTheme.GNOMES)) {
                setImageResId(R.drawable.gnomes_11);
            } else if (settings.equals(SummaryTheme.SANTA_LETTER)) {
                setImageResId(R.drawable.santa_letter_11);
            } else if (settings.equals(SummaryTheme.GLASS_LION)) {
                setImageResId(R.drawable.glass_lion_11);
            }
        } else if (a <= 10) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.SUMMARY_60_WHITE_1));

            if (settings.equals(SummaryTheme.LION)) {
                setImageResId(R.drawable.man_12);
            } else if (settings.equals(SummaryTheme.ABSTRACT)) {
                setImageResId(R.drawable.white);
            } else if (settings.equals(SummaryTheme.DREAM)) {
                setImageResId(R.drawable.dream_12);
            } else if (settings.equals(SummaryTheme.APRICOT)) {
                setImageResId(R.drawable.apricot_12);
            } else if (settings.equals(SummaryTheme.MARSHMALLOW)) {
                setImageResId(R.drawable.marshmallow_12);
            } else if (settings.equals(SummaryTheme.GNOMES)) {
                setImageResId(R.drawable.gnomes_12);
            } else if (settings.equals(SummaryTheme.SANTA_LETTER)) {
                setImageResId(R.drawable.santa_letter_12);
            } else if (settings.equals(SummaryTheme.GLASS_LION)) {
                setImageResId(R.drawable.glass_lion_12);
            }
        }
    }
}
