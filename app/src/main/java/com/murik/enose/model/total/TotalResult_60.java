package com.murik.enose.model.total;

import android.content.Context;

import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.A_First.ResultA_First1_2;
import com.murik.enose.model.A_First.ResultA_First40_70;
import com.murik.enose.model.A_Second.ResultA_Second1_2;
import com.murik.enose.model.A_Second.ResultA_Second1_3_4_60;
import com.murik.enose.model.TAU.TAU_60;
import com.murik.enose.model.common_A.A_20_30;
import com.murik.enose.model.common_A.A_20_60;
import com.murik.enose.model.common_A.S_30_60;
import com.murik.enose.model.total.helper.ColorHelper;
import com.murik.enose.model.Е.E_60;

import java.util.ArrayList;
import java.util.List;

public class TotalResult_60 {

    private Context context;

    private ResultA_First40_70 I;
    private ResultA_Second1_2 II;
    private ResultA_First1_2 III;
    private A_20_30 IV;
    private A_20_60 V;
    private ResultA_Second1_3_4_60 VI;
    private E_60 e_60;
    private S_30_60 s_30_60;
    private TAU_60 tau_60;
    private int hand;

    public TotalResult_60(Context context,
                          ResultA_First40_70 I,
                          ResultA_Second1_2 II,
                          ResultA_First1_2 III,
                          A_20_30 IV,
                          A_20_60 V,
                          ResultA_Second1_3_4_60 VI,
                          E_60 e_60,
                          S_30_60 s_30_60,
                          TAU_60 tau_60,
                          int hand) {
        this.I = I;
        this.II = II;
        this.III = III;
        this.IV = IV;
        this.V = V;
        this.VI = VI;
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

        if (fourthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.III), ""));
        }

        if (fifthCondition()) {
            totalIndicators.add(new TotalResult(getResource(R.string.IV), ""));
        }

        return totalIndicators;
    }

    private boolean firstCondition() {
        return (I.getA() >= 0.84 && I.getA() <= 1.3)
                && (III.getA() >= 0.45 && III.getA() <= 0.46)
                && (tau_60.getA() >= 0.47 && tau_60.getA() <= 51);
    }

    private boolean secondCondition() {
        return ((III.getA() >= 0.43 && III.getA() <= 0.46)
                && (VI.getA() <= 0.15)
                && (tau_60.getA() >= 47 && tau_60.getA() <= 51));
    }

    private boolean thirdCondition() {
        return (I.getA() >= 0.40 && I.getA() <= 0.75)
                && (III.getA() >= 0.43 && III.getA() <= 0.46)
                && (VI.getA()  <= 0.15)
                && (s_30_60.getA() >= 0.17 && s_30_60.getA() <= 0.21)
                && hand == Const.LEFT_HAND;
    }

    private boolean fourthCondition() {
        return (I.getA() >= 0.84 && I.getA() <= 1.3)
                && (II.getA() >= 0.56 && II.getA() <= 0.58)
                && (tau_60.getA() >= 36 && tau_60.getA() <= 42)
                && (s_30_60.getA() >= 0.17 && s_30_60.getA() <= 0.21)
                && hand == Const.LEFT_HAND;
    }

    private boolean fifthCondition() {
        return (IV.getA() >= 0.50 && IV.getA() <= 0.55)
                && (VI.getA() >= 0.43 && VI.getA() <= 0.48)
                && (tau_60.getA() >= 47 && tau_60.getA() <= 51)
                && (s_30_60.getA() > 0.17 && s_30_60.getA() <= 0.18);
    }
}
