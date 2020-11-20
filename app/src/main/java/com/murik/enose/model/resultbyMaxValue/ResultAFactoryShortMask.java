package com.murik.enose.model.resultbyMaxValue;

import android.content.Context;

import com.murik.enose.Const;
import com.murik.enose.model.A_First.B;
import com.murik.enose.model.A_First.K;
import com.murik.enose.model.A_First.ResultA_First1_2;
import com.murik.enose.model.A_First.ResultA_First1_2_Gray;
import com.murik.enose.model.A_First.ResultA_First1_5;
import com.murik.enose.model.A_First.ResultA_First1_5_Gray;
import com.murik.enose.model.A_First.ResultA_First2_3;
import com.murik.enose.model.A_First.ResultA_First2_3_Gray;
import com.murik.enose.model.A_First.ResultA_First2_4;
import com.murik.enose.model.A_First.ResultA_First2_4_Gray;
import com.murik.enose.model.A_First.ResultA_First2_5;
import com.murik.enose.model.A_First.ResultA_First2_5_Gray;
import com.murik.enose.model.A_First.ResultA_First4_5;
import com.murik.enose.model.A_First.ResultA_First5_4;
import com.murik.enose.model.A_First.SI;
import com.murik.enose.model.A_First.SI_Gray;
import com.murik.enose.model.OneSensorShortMeasure;
import com.murik.enose.model.ResultAFactory;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.service.Impl.BaseMeasureService;

import lombok.val;
import lombok.var;

public class ResultAFactoryShortMask extends ResultAFactory {

    private int hand = 0;

    public ResultAFactoryShortMask(DataByMaxParcelable inputData, int hand, Context context) {
        super(inputData, hand, context);
        this.hand = hand;
    }

    @Override
    public boolean calculateResultA() {
        if(!getMaxSensResult().isEmpty()){
            OneSensorShortMeasure oneSensorShortMeasure = new OneSensorShortMeasure(getMaxSensResult());

            var totalArea = 0F;
            var dangerToLungsArea = 0F;
            var PS_3425 = 1F;
            var PS_2435 = 1F;
            var SI = 1F;
            var K = 0F;

            BaseMeasureService ms = new BaseMeasureService();

            if (hand == Const.LEFT_HAND) {

                totalArea = ms.getAreaByMask(Const.DANGER, getInputData().getLeftHandDataSensor());

                val areaDanger = ms.getAreaByMask(Const.DANGER, getInputData().getLeftHandDataSensor());
                val areaLungs = ms.getAreaByMask(Const.LUNGS, getInputData().getLeftHandDataSensor());

                dangerToLungsArea = ms.calculateDifference(areaDanger, areaLungs);

                PS_2435 = ms.getPS2435(getInputData().getLeftHandDataSensor(), Const.SHORT);
                PS_3425 = ms.getPS3425(getInputData().getLeftHandDataSensor(), Const.SHORT);

                SI = PS_3425/PS_2435;

            } else if(hand == Const.RIGHT_HAND){
                totalArea = ms.getAreaByMask(Const.DANGER, getInputData().getRightHandDataSensor());

                val areaDanger = ms.getAreaByMask(Const.DANGER, getInputData().getRightHandDataSensor());
                val areaLungs = ms.getAreaByMask(Const.LUNGS, getInputData().getRightHandDataSensor());

                dangerToLungsArea = ms.calculateDifference(areaDanger, areaLungs);

                PS_2435 = ms.getPS2435(getInputData().getRightHandDataSensor(), Const.SHORT);
                PS_3425 = ms.getPS3425(getInputData().getRightHandDataSensor(), Const.SHORT);

                SI = PS_3425/PS_2435;
            }

            if(getInputData().getGender() == Const.GENDER_MALE) {
                K = totalArea/3500;
            } else {
                K = totalArea/3000;
            }

            try{
                getA().add(new ResultA_First1_2(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First2_3(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First2_4(oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First2_5(oneSensorShortMeasure.getFirstA2_5(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First1_5(oneSensorShortMeasure.getFirstA1_5(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First4_5(oneSensorShortMeasure.getFirstA4_5(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First5_4(oneSensorShortMeasure.getFirstA5_4(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First1_2_Gray(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First2_3_Gray(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First2_4_Gray(oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First2_5_Gray(oneSensorShortMeasure.getFirstA2_5(), getInputData(), getContext(), 1));
                getA().add(new ResultA_First1_5_Gray(oneSensorShortMeasure.getFirstA1_5(), getInputData(), getContext(), 1));
                getA().add(new SI_Gray(SI, getInputData(), getContext()));

//                getA().add(new K(K, getInputData(), getContext()));
                getA().add(new SI(SI, getInputData(), getContext()));
//                getA().add(new B(dangerToLungsArea, getInputData(), getContext()));
            }catch (Exception e){
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
