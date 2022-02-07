package com.murik.enose.model.summary;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.A_First.ResultA_First1_2;
import com.murik.enose.model.A_First.ResultA_First40_70;
import com.murik.enose.model.A_Second.ResultA_Second1_2;
import com.murik.enose.model.A_Second.ResultA_Second1_3_4_60;
import com.murik.enose.model.TAU.TAU_60;
import com.murik.enose.model.common_A.A_20_30;
import com.murik.enose.model.common_A.A_20_60;
import com.murik.enose.model.common_A.S_30_60;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.total.TotalResult;
import com.murik.enose.model.Е.E_60;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import lombok.val;

public class Summary_60 extends BaseResult {

    public Summary_60(double A, DataByMaxParcelable inputData, Context context) {
        super(A, inputData, context);
    }

    public void setResult() {
        val a = BigDecimal.valueOf(getA()).setScale(0, RoundingMode.HALF_UP).intValue();
        if (a >= 86 && a <= 100) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.SUMMARY_60_GREEN));
        } else if (a >= 80 && a <= 85) {
            setColorGREEN();
            setPossibleReasons(getResources(R.string.SUMMARY_60_GREEN_LITE));
        } else if (a >= 70 && a <= 79) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.SUMMARY_60_YELLOW_1));
        } else if (a >= 66 && a <= 69) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.SUMMARY_60_YELLOW_2));
        } else if (a >= 60 && a <= 65) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.SUMMARY_60_ORANGE_1));
        } else if (a >= 55 && a <= 59) {
            setColorORANGE();
            setPossibleReasons(getResources(R.string.SUMMARY_60_ORANGE_2));
        } else if (a >= 50 && a <= 54) {
            setColorRED();
            setPossibleReasons(getResources(R.string.SUMMARY_60_RED_1));
        } else if (a >= 34 && a <= 49) {
            setColorRED();
            setPossibleReasons(getResources(R.string.SUMMARY_60_RED_2));
        }  else if (a >= 0 && a <= 33) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.SUMMARY_60_BURGUNDY));
        }
    }
}
