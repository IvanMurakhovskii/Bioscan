package com.murik.lite.model.total;

import android.content.Context;
import android.graphics.Color;

import com.murik.lite.Const;
import com.murik.lite.R;
import com.murik.lite.model.A_30.A_10_20;
import com.murik.lite.model.A_30.A_15_30;
import com.murik.lite.model.A_30.A_15_45;
import com.murik.lite.model.A_30.A_20_40;
import com.murik.lite.model.A_30.A_50_30;
import com.murik.lite.model.A_30.S_15_30;
import com.murik.lite.model.TAU.TAU_30;
import com.murik.lite.model.common_A.A_20_30_for_30;
import com.murik.lite.model.total.helper.ColorHelper;
import com.murik.lite.model.Е.E_30;

import java.util.ArrayList;
import java.util.List;

import lombok.val;

public class TotalResult_30 {

    private Context context;

    private A_10_20 I;
    private A_15_30 II;
    private A_15_45 III;
    private A_20_30_for_30 IV;
    private A_20_40 V;
    private A_50_30 VI;
    private E_30 E;
    private S_15_30 S;
    private TAU_30 tau;
    private int hand;

    public TotalResult_30(Context context,
                          A_10_20 I,
                          A_15_30 II,
                          A_15_45 III,
                          A_20_30_for_30 IV,
                          A_20_40 V,
                          A_50_30 VI,
                          E_30 E,
                          S_15_30 S,
                          TAU_30 tau,
                          int hand) {
        this.I = I;
        this.II = II;
        this.III = III;
        this.IV = IV;
        this.V = V;
        this.VI = VI;
        this.E = E;
        this.S = S;
        this.tau = tau;
        this.hand = hand;
        this.context = context;
    }

    private String getResource(int resId) {
        return this.context.getResources().getString(resId);
    }

    public List<TotalResult> createAndGetDescription() {
        List<TotalResult> totalIndicators = new ArrayList<>();

        if (firstCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.I_30), ""));
        }

        if (secondCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.II_30), ""));
        }

        if (thirdCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.III_30), ""));
        }

        if (fourthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.IV_30), ""));
        }

        if (fourthCondition() && VI.getA() == 0.55 && hand == Const.LEFT_HAND) {
            totalIndicators.add(new TotalResult(getResource(R.string.V_30), ""));
        }

        if (fifth_1_Condition() || fifthCondition() || sixthCondition() || seventhCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.VI_30), ""));
        }

        if (eighthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.VII_30), ""));
        }

        if (ninthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.VIII_30), ""));
        }

        if (tenthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.IX_30), ""));
        }

        if (eleventhCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.X_30), ""));
        }

        if (twelfthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.XI_30), ""));
        }

        if (thirteenthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.XII_30), ""));
        }

        if (fourteenthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.XIII_30), ""));
        }

        if (fifteenthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.XIV_30), ""));
        }

        totalIndicators.addAll(colorsCondition());

        return totalIndicators;
    }

    private List<Integer> getAllColors() {
        val colors = new ArrayList<Integer>();

        colors.add(I.getViewColor());
        colors.add(II.getViewColor());
        colors.add(III.getViewColor());
        colors.add(IV.getViewColor());
        colors.add(V.getViewColor());
        colors.add(VI.getViewColor());
        colors.add(E.getViewColor());
        colors.add(S.getViewColor());
        colors.add(tau.getViewColor());

        return colors;
    }

    private List<TotalResult> colorsCondition() {
        val result = new ArrayList<TotalResult>();

        val colors = getAllColors();

        int greenCount = 0;
        int yellowCount = 0;
        int redCount = 0;
        int burgundyCount = 0;
        int whiteCount = 0;
        int blueCount = 0;
        int crimsonCount = 0;
        int pinkCount = 0;
        int grayColor = 0;

        for (Integer color : colors) {
            if (color == ColorHelper.getColorGREEN()) greenCount++;
            if (color == ColorHelper.getColorYELLOW()) yellowCount++;
            if (color == ColorHelper.getColorRED()) redCount++;
            if (color == ColorHelper.getColorBURGUNDY()) burgundyCount++;
            if (color == ColorHelper.getColorBlue()) blueCount++;
            if (color == Color.WHITE) whiteCount++;
            if (color == ColorHelper.getColorCRIMSON()) crimsonCount++;
            if (color == ColorHelper.getColorPINK()) pinkCount++;
            if (color == Color.GRAY) grayColor++;
        }

        int colorsWithoutGray = colors.size() - grayColor;

        if (greenCount == 2 && (yellowCount + redCount + burgundyCount) == colorsWithoutGray - 2) {
            result.add(new TotalResult(getResource(R.string.COLOR_30_I), ""));
        }

        if (greenCount < 2) {
            result.add(new TotalResult(getResource(R.string.COLOR_30_II), ""));
        }

        if (greenCount == colors.size() || (greenCount == (colorsWithoutGray - 1) &&  yellowCount == 1 )) {
            result.add(new TotalResult(getResource(R.string.COLOR_30_III), ""));
        }

        if (yellowCount == 2 && (greenCount == colorsWithoutGray - 2)) {
            result.add(new TotalResult(getResource(R.string.COLOR_30_IV), ""));
        }

        if (IV.getViewColor() == ColorHelper.getColorBlue() && greenCount == (colorsWithoutGray - 1)) {
            result.add(new TotalResult(getResource(R.string.COLOR_30_V), ""));
        }

        if (burgundyCount > 3) {
            result.add(new TotalResult(getResource(R.string.COLOR_30_VI), ""));
        }

        if (whiteCount == 1 && greenCount == (colorsWithoutGray - 1)) {
            result.add(new TotalResult(getResource(R.string.COLOR_30_VII), ""));
        }

        if (whiteCount == 2) {
            result.add(new TotalResult(getResource(R.string.COLOR_30_VIII), ""));
        }

        return result;

    }

    private boolean firstCondition() {
        return (III.getA() >= 0.35 && III.getA() <= 0.55)
                && (IV.getA() >= 0.27 && IV.getA() <= 0.49)
                && (tau.getA() >= 26 && tau.getA() <= 37)
                && (S.getA() >= 0.17 && S.getA() <= 0.21);
    }

    private boolean secondCondition() {
        return (III.getA() <= 0.35 && III.getA() >= 0.55)
                && (IV.getA() >= 0.27 && IV.getA() <= 0.49)
                && (V.getA() >= 0.65 && V.getA() <= 0.68)
                && (S.getA() >= 0.17 && S.getA() <= 0.21);
    }

    private boolean thirdCondition() {
        return (II.getA() >= 0.20 && II.getA() <= 0.29)
                && (III.getA() >= 0.27 && III.getA() <= 0.32)
                && (tau.getA() > 46)
                && (S.getA() >= 0.10 && S.getA() <= 0.16);
    }

    private boolean fourthCondition() {
        return (III.getA() >= 0.62 && III.getA() <= 0.64)
                && (IV.getA() >= 0.40 && IV.getA() <= 0.46)
                && (V.getA() >= 0.69 && V.getA() <= 0.76);
    }

    private boolean fifth_1_Condition() {
        return (III.getA() >= 0.56 && III.getA() <= 0.70)
                && (IV.getA() >= 0.34 && IV.getA() <= 0.49);
    }

    private boolean fifthCondition() {
        return fifth_1_Condition()
                && (V.getA() >= 0.69 && V.getA() <= 0.76);
    }

    private boolean sixthCondition() {
        return fifthCondition()
                && (VI.getA() >= 0.54 && VI.getA() <= 0.56);
    }

    private boolean seventhCondition() {
        return sixthCondition()
                && (S.getA() >= 0.20 && S.getA() <= 0.21);
    }

    private boolean eighthCondition() {
        return (IV.getA() >= 0.27 && IV.getA() <= 0.33)
                && (tau.getA() >= 39.5);
    }

    private boolean ninthCondition() {
        return (III.getA() >= 0.35 && III.getA() <= 0.55);
    }

    private boolean tenthCondition() {
        return (I.getA() >= 0.18 && I.getA() <= 0.23)
                && (II.getA() >= 0.20 && II.getA() <= 0.29)
                && (III.getA() >= 0.27 && III.getA() <= 0.32)
                && (tau.getA() >= 35 && tau.getA() <= 39.5)
                && (S.getA() >= 0.10 && S.getA() <= 0.16);
    }

    private boolean eleventhCondition() {
        return (II.getA() >= 0.48 && II.getA() <= 0.55)
                && (III.getA() >= 0.56 && III.getA() <= 0.70)
                && (IV.getA() >= 0.34 && IV.getA() <= 0.49)
                && (V.getA() >= 0.69 && V.getA() <= 0.76)
                && (VI.getA() >= 0.51 && VI.getA() <= 0.55)
                && (tau.getA() > 39.5);
    }

    private boolean twelfthCondition() {
        return (II.getA() >= 0.30 && II.getA() <= 0.334)
                && (IV.getA() >= 0.34 && IV.getA() <= 0.49)
                && (tau.getA() > 39.5);
    }

    private boolean thirteenthCondition() {
        return (II.getViewColor() == ColorHelper.getColorYELLOW())
                && (IV.getA() >= 0.29 && IV.getA() <= 0.33)
                && (tau.getA() >= 40 && tau.getA() <= 43);
    }

    private boolean fourteenthCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (II.getA() >= 0.20 && II.getA() <= 0.28)
                && (IV.getA() >= 0.29 && IV.getA() <= 0.33)
                && (tau.getA() >= 40 && tau.getA() <= 43)
                && (S.getA() >= 0.10 && S.getA() <= 0.16);
    }

    private boolean fifteenthCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (II.getViewColor() == ColorHelper.getColorYELLOW())
                && (IV.getA() >= 0.27 && IV.getA() <= 0.49)
                && (tau.getViewColor() == ColorHelper.getColorBURGUNDY())
                && (S.getViewColor() == ColorHelper.getColorRED());
    }
}
