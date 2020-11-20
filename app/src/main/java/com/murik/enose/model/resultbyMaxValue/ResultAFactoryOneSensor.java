package com.murik.enose.model.resultbyMaxValue;

import android.content.Context;
import android.util.Log;

import com.murik.enose.Const;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.A_First.BOSI;
import com.murik.enose.model.A_First.ResultA_First1_2;
import com.murik.enose.model.A_First.ResultA_First1_2_Gray;
import com.murik.enose.model.A_First.ResultA_First2_3;
import com.murik.enose.model.A_First.ResultA_First2_3_Gray;
import com.murik.enose.model.A_First.ResultA_First2_4;
import com.murik.enose.model.A_First.ResultA_First2_4_Gray;
import com.murik.enose.model.A_First.ResultA_First2_5;
import com.murik.enose.model.A_First.ResultA_First2_5_Gray;
import com.murik.enose.model.A_First.ResultA_First40_70;
import com.murik.enose.model.A_First.SI;
import com.murik.enose.model.A_Second.ResultA_Second1_2;
import com.murik.enose.model.A_Second.ResultA_Second1_3;
import com.murik.enose.model.A_Second.ResultA_Second1_3_4_60;
import com.murik.enose.model.A_Second.ResultA_Second1_4;
import com.murik.enose.model.A_Second.ResultA_Second2_3;
import com.murik.enose.model.A_Second.ResultA_Second3_4;
import com.murik.enose.model.OneSensorLongMeasure;
import com.murik.enose.model.OneSensorShortMeasure;
import com.murik.enose.model.ResultAFactory;
import com.murik.enose.model.common_A.A_20_30;
import com.murik.enose.model.common_A.A_20_30_GRAY;
import com.murik.enose.model.common_A.A_20_60;
import com.murik.enose.model.common_A.A_20_60_GRAY;
import com.murik.enose.model.common_A.A_30_60;
import com.murik.enose.model.common_A.A_30_60_GRAY;
import com.murik.enose.model.common_A.Result_E;
import com.murik.enose.model.common_A.Result_L;
import com.murik.enose.service.Impl.BaseMeasureService;

import lombok.val;
import lombok.var;

public class ResultAFactoryOneSensor extends ResultAFactory {

    private int hand = 0;

    public ResultAFactoryOneSensor(DataByMaxParcelable inputData, int hand, Context context) {
        super(inputData, hand, context);
        this.hand = hand;
    }

    @Override
    public boolean calculateResultA() {

        BaseMeasureService ms = new BaseMeasureService();

        if (!getMaxSensResult().isEmpty()) {
            OneSensorShortMeasure oneSensorShortMeasure = new OneSensorShortMeasure(getMaxSensResult());
            OneSensorLongMeasure oneSensorLongMeasure = new OneSensorLongMeasure(getMaxSensResult());


            float areaBodyLeft = ms.getAreaByMask(Const.BODY, getInputData().getLeftHandDataSensor());
            float areaBodyRight = ms.getAreaByMask(Const.BODY, getInputData().getRightHandDataSensor());

            Float delta_1_2 = -1F;

            if (areaBodyLeft != 0 && areaBodyRight != 0) {
                delta_1_2 = ms.calculateDifference(areaBodyLeft, areaBodyRight);
            }
            Float s20 = -1F;
            Float s30 = -1F;
            Float s60 = -1F;
            var L = 0F;

            var En = -1F;

            Float S_ENERGY = 1F;

            val PS_2435_right = ms.getPS2435(getInputData().getRightHandDataSensor(), Const.SHORT);
            val PS_3425_right = ms.getPS3425(getInputData().getRightHandDataSensor(), Const.SHORT);

            val SI_right = PS_3425_right / PS_2435_right;

            val PS_2435_left = ms.getPS2435(getInputData().getLeftHandDataSensor(), Const.SHORT);
            val PS_3425_left = ms.getPS3425(getInputData().getLeftHandDataSensor(), Const.SHORT);

            val SI_left = PS_3425_left / PS_2435_left;

            float si = 0;

            float bosi = 0;

            if(getInputData().getLeftHandDataSensor() != null && getInputData().getRightHandDataSensor() != null) {
                bosi = ms.calculateDifference(SI_left, SI_right);
            }

            float a_40_70 = 0;
            if (hand == Const.LEFT_HAND) {

                if(getInputData().getLeftHandDataSensor().size() >= 70) {
                    val a40 = getInputData().getLeftHandDataSensor().get(40);
                    val a70 = getInputData().getLeftHandDataSensor().get(70);

                    a_40_70 = a70 == 0 ? 0 : (float)a40/a70;
                }

                si = SI_left;
                s60 = ms.getAreaByMask(Const.MASK_60, getInputData().getLeftHandDataSensor());
                s30 = ms.getAreaByMask(Const.MASK_30, getInputData().getLeftHandDataSensor());
                s20 = ms.getAreaByMask(Const.MASK_20, getInputData().getLeftHandDataSensor());
                S_ENERGY = ms.getAreaByMask(Const.ENERGY, getInputData().getLeftHandDataSensor());
            } else {

                if(getInputData().getRightHandDataSensor().size() >= 70) {
                    val a40 = getInputData().getRightHandDataSensor().get(40);
                    val a70 = getInputData().getRightHandDataSensor().get(70);

                    a_40_70 = a70 == 0 ? 0 : (float)a40/a70;
                }
                si = SI_right;
                s60 = ms.getAreaByMask(Const.MASK_60, getInputData().getRightHandDataSensor());
                s30 = ms.getAreaByMask(Const.MASK_30, getInputData().getRightHandDataSensor());
                s20 = ms.getAreaByMask(Const.MASK_20, getInputData().getRightHandDataSensor());
                S_ENERGY = ms.getAreaByMask(Const.ENERGY, getInputData().getRightHandDataSensor());
            }

            L = s30 / s60;

            En = (S_ENERGY / s60) * 5;

            float Sn = 0;

            try {

                if (getInputData().getTimeRegistrationMaxSignal() == 80) {

                    getA().add(new SI(si, getInputData(), getContext()));
//                    getA().add(new BOSI(bosi, getInputData(), getContext()));
                    getA().add(new ResultA_First2_3(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1.81F));
                    getA().add(new ResultA_Second1_2(oneSensorLongMeasure.getSecondA1_2(), getInputData(), getContext(), 1.19F));
                    getA().add(new ResultA_Second2_3(oneSensorLongMeasure.getSecondA2_3(), getInputData(), getContext(), 1.1F));
                    getA().add(new ResultA_First1_2(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), 1.35F));
                    getA().add(new A_20_30((s20 / s30), getInputData(), getContext(), 1.24F));
                    getA().add(new ResultA_Second1_3(oneSensorLongMeasure.getSecondA1_3(), getInputData(), getContext(), 1.3F));
                    getA().add(new ResultA_First2_5(1/oneSensorShortMeasure.getFirstA5_2(), getInputData(), getContext(), 0.47F));
                    getA().add(new ResultA_First2_4(1/oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 0.53F));
                    getA().add(new A_20_60((s20 / s60), getInputData(), getContext(), 1.63F));
                    getA().add(new ResultA_Second1_4(1/oneSensorLongMeasure.getSecondA1_4(), getInputData(), getContext(), 0.39F));
                    getA().add(new ResultA_Second3_4(1/oneSensorLongMeasure.getSecondA3_4(), getInputData(), getContext(), 0.39F));
                    getA().add(new A_30_60((s30 / s60), getInputData(), getContext(), 1.63F));

                    if(getInputData().isExpert()) {
                        getA().add(new ResultA_First2_3_Gray(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1));
                        getA().add(new A_20_30_GRAY((s20 / s30), getInputData(), getContext(), 1.24F));
                        getA().add(new ResultA_First1_2_Gray(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), 1));
                        getA().add(new ResultA_First2_5_Gray(1/oneSensorShortMeasure.getFirstA5_2(), getInputData(), getContext(), 1));
                        getA().add(new ResultA_First2_4_Gray(1/oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 1));
                        getA().add(new A_20_60_GRAY((s20 / s60), getInputData(), getContext(), 1.24F));
                        getA().add(new A_30_60_GRAY((s30 / s60), getInputData(), getContext(), 1.63F));
                    }

                    getA().add(new Result_E(En, getInputData(), getContext()));
                    getA().add(new Result_L(L, getInputData(), getContext()));
                } else {
//                    getA().add(new SI(si, getInputData(), getContext()));
                    getA().add(new ResultA_First40_70(a_40_70, getInputData(), getContext(), 1.81F));
                    getA().add(new ResultA_Second1_2(oneSensorLongMeasure.getSecondA1_2(), getInputData(), getContext(), 1.19F));
                    getA().add(new ResultA_First1_2(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), "III"));
                    getA().add(new A_20_30((s20 / s30), getInputData(), getContext(), "IV"));
                    getA().add(new A_20_60((s20 / s60), getInputData(), getContext(), "V"));
                    getA().add(new ResultA_Second1_3_4_60(oneSensorShortMeasure.getFirstA3_2(), getInputData(), getContext(), 0.39F));
                    getA().add(new A_30_60((s30 / s60), getInputData(), getContext(), 1));


                    if(getInputData().isExpert()) {
                        getA().add(new ResultA_First2_3_Gray(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1));
                        getA().add(new ResultA_First1_2_Gray(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), "II_G"));
                        getA().add(new A_20_30_GRAY((s20 / s30), getInputData(), getContext(), "IV"));
                        getA().add(new A_20_60_GRAY((s20 / s60), getInputData(), getContext(), 1.24F));
                        getA().add(new A_30_60_GRAY((s30 / s60), getInputData(), getContext(), 1.63F));
                    }

                    getA().add(new Result_E(En, getInputData(), getContext()));
//                    getA().add(new Result_L(L, getInputData(), getContext()));
                }

            } catch (Exception e) {
                Log.e("ResultAFactoryOneSensor", e.getMessage());
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
