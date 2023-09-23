package com.murik.lite.model.total;

import android.content.Context;

import com.murik.lite.Const;
import com.murik.lite.R;
import com.murik.lite.model.A_First.A_30_60;
import com.murik.lite.model.A_First.A_40_70;
import com.murik.lite.model.A_Second.A_40_60;
import com.murik.lite.model.A_Second.A_90_60;
import com.murik.lite.model.TAU.TAU_60;
import com.murik.lite.model.common_A.A_20_30;
import com.murik.lite.model.common_A.A_20_60;
import com.murik.lite.model.common_A.S_30_60;
import com.murik.lite.model.Е.E_60;

import java.util.ArrayList;
import java.util.List;

public class TotalResult_60 {

    private Context context;

    private A_40_70 a_40_70;
    private A_40_60 a_40_60;
    private A_30_60 a_30_60;
    private A_20_30 a_20_30;
    private A_20_60 a_20_60;
    private A_90_60 a_90_60;
    private E_60 e_60;
    private S_30_60 s_30_60;
    private TAU_60 tau_60;
    private int hand;

    public TotalResult_60(Context context,
                          A_40_70 a_40_70,
                          A_40_60 a_40_60,
                          A_30_60 a_30_60,
                          A_20_30 a_20_30,
                          A_20_60 V,
                          A_90_60 a_90_60,
                          E_60 e_60,
                          S_30_60 s_30_60,
                          TAU_60 tau_60,
                          int hand) {
        this.a_40_70 = a_40_70;
        this.a_40_60 = a_40_60;
        this.a_30_60 = a_30_60;
        this.a_20_30 = a_20_30;
        this.a_20_60 = V;
        this.a_90_60 = a_90_60;
        this.e_60 = e_60;
        this.s_30_60 = s_30_60;
        this.tau_60 = tau_60;
        this.hand = hand;
        this.context = context;
    }

    private String getResource(int resId) {
        return this.context.getResources().getString(resId);
    }

    public List<TotalResult> createAndGetDescription() {
        List<TotalResult> totalIndicators = new ArrayList<>();

        if (firstCondition() || secondCondition() ) {
            totalIndicators.add(new TotalResult(getResource(R.string.I), ""));
        }

        if (thirdCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.II), ""));
        }

        if (a_1()) {
            totalIndicators.add(new TotalResult(getResource(R.string.a_1), ""));
        }

        if (a_2()) {
            totalIndicators.add(new TotalResult(getResource(R.string.a_2), ""));
        }

        if (a_3()) {
            totalIndicators.add(new TotalResult(getResource(R.string.a_3), ""));
        }

        if (a_4()) {
            totalIndicators.add(new TotalResult(getResource(R.string.a_4), ""));
        }

        if (a_5()) {
            totalIndicators.add(new TotalResult(getResource(R.string.a_5), ""));
        }

        /*if (fourthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.III), ""));
        }

        if (fifthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.IV), ""));
        }*/

        return totalIndicators;
    }

    private boolean firstCondition() {
        return (a_40_70.getA() >= 0.86 && a_40_70.getA() <= 1)
                && (a_30_60.getA() >= 0.48 && a_30_60.getA() <= 0.55)
                && (tau_60.getA() >= 47 && tau_60.getA() <= 51);
    }

    private boolean secondCondition() {
        return ((a_30_60.getA() >= 0.43 && a_30_60.getA() <= 0.46)
                && (a_90_60.getA() <= 0.15)
                && (tau_60.getA() >= 47 && tau_60.getA() <= 51));
    }

    private boolean a_1() {
        return ((a_40_70.getA() >= 0.40 && a_40_70.getA() <= 0.75)
                && (a_30_60.getA() >= 0.43 && a_30_60.getA() <= 0.46)
                && (s_30_60.getA() >= 0.16 && s_30_60.getA() <= 0.18));
    }

    private boolean a_2() {
        return ((a_40_70.getA() >= 0.86 && a_40_70.getA() <= 1)
                && (a_40_60.getA() >= 0.45 && a_40_60.getA() <= 0.57)
                && (s_30_60.getA() >= 0.16 && s_30_60.getA() <= 0.18)
                && (tau_60.getA() >= 36 && tau_60.getA() <= 42));
    }

    private boolean a_3() {
        return ((a_20_30.getA() >= 0.5 && a_20_30.getA() <= 0.55)
                && (tau_60.getA() >= 47 && tau_60.getA() <= 51)
                && (s_30_60.getA() >= 0.17 && s_30_60.getA() <= 0.18));
    }

    private boolean a_4() {
        return ((a_40_70.getA() >= 0.4 && a_40_70.getA() <= 0.75)
                && (a_30_60.getA() >= 0.2 && a_30_60.getA() <= 0.27)
                && (a_20_30.getA() >= 0.5 && a_20_30.getA() <= 0.56)
                && (a_20_60.getA() >= 0.32 && a_20_60.getA() <= 0.35)
                && (tau_60.getA() >= 0.40 && tau_60.getA() <= 0.52));
    }

    private boolean a_5() {
        return ((a_40_70.getA() >= 0.4 && a_40_70.getA() <= 0.75)
                && (a_30_60.getA() >= 0.2 && a_30_60.getA() <= 0.27)
                && (a_20_30.getA() >= 0.5 && a_20_30.getA() <= 0.56)
                && (s_30_60.getA() >= 0.17 && s_30_60.getA() <= 0.21)
                && (tau_60.getA() >= 0.40 && tau_60.getA() <= 0.52));
    }

    private boolean thirdCondition() {
        return (a_40_70.getA() >= 0.40 && a_40_70.getA() <= 0.75)
                && (a_30_60.getA() >= 0.43 && a_30_60.getA() <= 0.46)
                && (a_90_60.getA()  <= 0.15)
                && (s_30_60.getA() >= 0.17 && s_30_60.getA() <= 0.21)
                && hand == Const.LEFT_HAND;
    }

    private boolean fourthCondition() {
        return (a_40_70.getA() >= 0.84 && a_40_70.getA() <= 1.3)
                && (a_40_60.getA() >= 0.56 && a_40_60.getA() <= 0.58)
                && (tau_60.getA() >= 36 && tau_60.getA() <= 42)
                && (s_30_60.getA() >= 0.17 && s_30_60.getA() <= 0.21)
                && hand == Const.LEFT_HAND;
    }

    private boolean fifthCondition() {
        return (a_20_30.getA() >= 0.50 && a_20_30.getA() <= 0.55)
                && (a_90_60.getA() >= 0.43 && a_90_60.getA() <= 0.48)
                && (tau_60.getA() >= 47 && tau_60.getA() <= 51)
                && (s_30_60.getA() > 0.17 && s_30_60.getA() <= 0.18);
    }
}
