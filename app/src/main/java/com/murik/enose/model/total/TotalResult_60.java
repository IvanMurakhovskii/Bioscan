package com.murik.enose.model.total;

import com.murik.enose.Const;
import com.murik.enose.model.A_First.ResultA_First1_2;
import com.murik.enose.model.A_First.ResultA_First40_70;
import com.murik.enose.model.A_Second.ResultA_Second1_2;
import com.murik.enose.model.A_Second.ResultA_Second1_3_4_60;
import com.murik.enose.model.TAU.TAU_60;
import com.murik.enose.model.common_A.A_20_30;
import com.murik.enose.model.common_A.A_20_60;
import com.murik.enose.model.common_A.S_30_60;
import com.murik.enose.model.Е.E_60;

import java.util.ArrayList;
import java.util.List;

public class TotalResult_60 {
    private A_20_30 IV;
    private E_60 e_60;
    private S_30_60 s_30_60;
    private TAU_60 tau_60;
    private ResultA_First40_70 I;
    private ResultA_Second1_3_4_60 VI;
    private ResultA_First1_2 III;
    private A_20_60 V;
    private int hand;
    private ResultA_Second1_2 II;

    public TotalResult_60(int hand, A_20_30 IV, E_60 e_60, S_30_60 s_30_60, TAU_60 tau_60, ResultA_First40_70 I, ResultA_Second1_3_4_60 VI, ResultA_First1_2 III, A_20_60 V, ResultA_Second1_2 II) {
        this.IV = IV;
        this.e_60 = e_60;
        this.s_30_60 = s_30_60;
        this.tau_60 = tau_60;
        this.I = I;
        this.VI = VI;
        this.V = V;
        this.III = III;
        this.II = II;
        this.hand = hand;
    }

    public List<String> createAndGetDescription() {
        List<String> totalIndicators = new ArrayList<>();

        if (firstCondition()) {
            totalIndicators.add("Активное воспаление, принять меры.");
        }
        if (secondCondition()) {
            totalIndicators.add("Повышено давление, контроль");
        }
        if (thirdCondition()) {
            totalIndicators.add("Критическая усталость, переутомление. Принять меры! При потвторении - обратить внимание.");
        }

        if (fourthCondition()) {
            totalIndicators.add("Перегрузка после еды(до 2-х часов после принятия пищи).");
        }

        if (fifthCondition()) {
            totalIndicators.add("Голод, слабость/усталость, недостаток кислорода/ переутомление сердца. Критично. (если после еды – ложный)");
        }

        if (sixthCondition()) {
            totalIndicators.add("Критический голод, сильная слабость/усталость, переутомление сердца. Срочно принять меры! (если после обильной еды – ложный)");
        }

        if (seventhCondition()) {
            totalIndicators.add("Голод, слабость/усталость, недостаток кислорода/ переутомление сердца. Критично если после еды - ложный)");
        }

        if (eighthCondition()) {
            totalIndicators.add("Ложный после еды. Если натощак – проблемы в органах ЖКТ. Обратить внимание.");
        }

        if (ninthCondition()) {
            totalIndicators.add("Обратить внимание! Слабость сердца.");
        }

        if (tenthCondition()) {
            totalIndicators.add("Голод. Срочно принять пищу!");
        }

        if (eleventhCondition()) {
            totalIndicators.add("Усталость, срочно отдых!");
        }

        if (twelfthCondition()) {
            totalIndicators.add("Системная усталость, голод! После еды - срочный отдых!");
        }

        if (thirteenthCondition()) {
            totalIndicators.add("После еды - норма.");
        }

        if (fourteenthCondition()) {
            totalIndicators.add("Болезнь(развитие) воспаление.");
        }

        if (fifteenthCondition()) {
            totalIndicators.add("Стабильная работа организма, нет серъезных изменений.");
        }

        if (sixteenthCondition()) {
            totalIndicators.add("Спазм сосудов, боль.");
        }

        if (seventeenthCondition()) {
            totalIndicators.add("Голод, рекомендуется принять пищу!");
        }

        if (eighteenthCondition()) {
            totalIndicators.add("Сильный голод, спазм в органах ЖКТ");
        }

        if (nineteenthCondition()) {
            totalIndicators.add("Жарко, повторить через 5 минут. При повторе - контроль давления.");
        }

        if (twentyFirstCondition()) {
            totalIndicators.add("Натощак норма.");
        }

        return totalIndicators;
    }

    private boolean firstCondition() {
        return (IV.getViewColor() == ColorHelper.getColorRED())
                && (e_60.getViewColor() == ColorHelper.getColorRED())
                && (s_30_60.getViewColor() == ColorHelper.getColorRED())
                && (tau_60.getViewColor() == ColorHelper.getColorRED() || tau_60.getViewColor() == ColorHelper.getColorYELLOW());
    }

    private boolean secondCondition() {
        return (IV.getViewColor() == ColorHelper.getColorRED())
                && (s_30_60.getViewColor() == ColorHelper.getColorRED())
                && (tau_60.getViewColor() == ColorHelper.getColorRED())
                && (hand == Const.LEFT_HAND);
    }

    private boolean thirdCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (e_60.getViewColor() == ColorHelper.getColorRED())
                && (s_30_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (hand == Const.RIGHT_HAND);
    }

    private boolean fourthCondition() {
        return (IV.getViewColor() == ColorHelper.getColorRED())
                && (e_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (s_30_60.getViewColor() == ColorHelper.getColorRED())
                && (tau_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (hand == Const.LEFT_HAND);
    }

    private boolean fifthCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (IV.getViewColor() == ColorHelper.getColorYELLOW())
                && (VI.getViewColor() == ColorHelper.getColorYELLOW() || VI.getViewColor() == ColorHelper.getColorRED())
                && (e_60.getViewColor() == ColorHelper.getColorYELLOW()
                && (tau_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (s_30_60.getViewColor() == ColorHelper.getColorYELLOW())
                && hand == Const.LEFT_HAND);
    }

    private boolean sixthCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (IV.getViewColor() == ColorHelper.getColorYELLOW())
                && (VI.getViewColor() == ColorHelper.getColorYELLOW() || VI.getViewColor() == ColorHelper.getColorRED())
                && (e_60.getViewColor() == ColorHelper.getColorRED())
                && (s_30_60.getViewColor() == ColorHelper.getColorYELLOW() || s_30_60.getViewColor() == ColorHelper.getColorRED())
                && (hand == Const.LEFT_HAND);
    }

    private boolean seventhCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (IV.getViewColor() == ColorHelper.getColorYELLOW() || IV.getViewColor() == ColorHelper.getColorRED())
                && (e_60.getViewColor() == ColorHelper.getColorRED() || e_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (s_30_60.getViewColor() == ColorHelper.getColorYELLOW() || s_30_60.getViewColor() == ColorHelper.getColorRED())
                && (hand == Const.RIGHT_HAND);
    }

    private boolean eighthCondition() {
        return (III.getViewColor() == ColorHelper.getColorYELLOW() && III.getViewColor() == ColorHelper.getColorRED())
                && (V.getViewColor() == ColorHelper.getColorYELLOW() || V.getViewColor() == ColorHelper.getColorRED())
                && (VI.getViewColor() == ColorHelper.getColorRED())
                && (tau_60.getViewColor() == ColorHelper.getColorRED());
    }

    private boolean ninthCondition() {
        return (IV.getViewColor() == ColorHelper.getColorYELLOW() && IV.getViewColor() == ColorHelper.getColorRED())
                && (VI.getViewColor() == ColorHelper.getColorYELLOW() && VI.getViewColor() == ColorHelper.getColorRED())
                && (e_60.getViewColor() == ColorHelper.getColorRED() || e_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (tau_60.getViewColor() == ColorHelper.getColorRED() || tau_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (s_30_60.getViewColor() == ColorHelper.getColorYELLOW() || s_30_60.getViewColor() == ColorHelper.getColorRED())
                && (hand == Const.LEFT_HAND);
    }

    private boolean tenthCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (IV.getViewColor() == ColorHelper.getColorYELLOW())
                && (VI.getViewColor() == ColorHelper.getColorBURGUNDY())
                && (tau_60.getA() >= 50 && tau_60.getA() <= 53)
                && (e_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (s_30_60.getA() >= 0.15 && s_30_60.getA() <= 0.18)
                && (hand == Const.RIGHT_HAND);
    }

    private boolean eleventhCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (III.getViewColor() == ColorHelper.getColorYELLOW())
                && (tau_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (e_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (s_30_60.getViewColor() == ColorHelper.getColorYELLOW());
    }

    private boolean twelfthCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (IV.getViewColor() == ColorHelper.getColorYELLOW())
                && (III.getViewColor() == ColorHelper.getColorYELLOW())
                && (tau_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (e_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (s_30_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (hand == Const.LEFT_HAND);
    }

    private boolean thirteenthCondition() {
        return (V.getViewColor() == ColorHelper.getColorYELLOW())
                && (III.getViewColor() == ColorHelper.getColorYELLOW())
                && (e_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (tau_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (hand == Const.LEFT_HAND);
    }

    private boolean fourteenthCondition() {
        return (VI.getViewColor() == ColorHelper.getColorYELLOW())
                && (V.getViewColor() == ColorHelper.getColorYELLOW())
                && ((III.getViewColor() == ColorHelper.getColorRED())
                    || (IV.getViewColor() == ColorHelper.getColorYELLOW()
                    || IV.getViewColor() == ColorHelper.getColorRED()));
    }

    private boolean fifteenthCondition() {
        return (IV.getA() >= 0.62 && IV.getA() <= 0.64
        && II.getA() >= 0.62 && II.getA() <= 0.64);
    }

    private boolean sixteenthCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (e_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (tau_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (s_30_60.getViewColor() == ColorHelper.getColorYELLOW());
    }

    private boolean seventeenthCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (III.getViewColor() == ColorHelper.getColorYELLOW())
                && (e_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (tau_60.getViewColor() == ColorHelper.getColorYELLOW())
                && hand == Const.LEFT_HAND;
    }

    private boolean eighteenthCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (IV.getViewColor() == ColorHelper.getColorYELLOW())
                && (V.getViewColor() == ColorHelper.getColorYELLOW())
                && (III.getViewColor() == ColorHelper.getColorRED())
                && (e_60.getViewColor() == ColorHelper.getColorYELLOW() || e_60.getViewColor() == ColorHelper.getColorRED())
                && (tau_60.getViewColor() == ColorHelper.getColorYELLOW());
    }

    private boolean nineteenthCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (III.getViewColor() == ColorHelper.getColorYELLOW())
                && (VI.getViewColor() == ColorHelper.getColorRED())
                && (tau_60.getViewColor() == ColorHelper.getColorRED());
    }

    private boolean twentyFirstCondition() {
        return (I.getViewColor() == ColorHelper.getColorYELLOW())
                && (III.getViewColor() == ColorHelper.getColorYELLOW())
                && (tau_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (e_60.getViewColor() == ColorHelper.getColorYELLOW())
                && (s_30_60.getViewColor() == ColorHelper.getColorYELLOW())
                && hand == Const.RIGHT_HAND;
    }
}
