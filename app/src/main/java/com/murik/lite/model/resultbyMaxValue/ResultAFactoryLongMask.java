package com.murik.lite.model.resultbyMaxValue;

import android.content.Context;

import com.murik.lite.model.A_Second.A_40_60;
import com.murik.lite.model.A_Second.A_40_80;
import com.murik.lite.model.A_Second.ResultA_Second1_4;
import com.murik.lite.model.A_Second.ResultA_Second1_5;
import com.murik.lite.model.A_Second.A_60_80;
import com.murik.lite.model.A_Second.ResultA_Second2_4;
import com.murik.lite.model.A_Second.ResultA_Second2_5;
import com.murik.lite.model.A_Second.ResultA_Second3_4;
import com.murik.lite.model.A_Second.ResultA_Second3_5;
import com.murik.lite.model.A_Second.ResultA_Second5_4;
import com.murik.lite.model.OneSensorLongMeasure;
import com.murik.lite.model.ResultAFactory;
import com.murik.lite.dto.DataByMaxParcelable;

public class ResultAFactoryLongMask extends ResultAFactory {

    public ResultAFactoryLongMask(DataByMaxParcelable inputData, int hand, Context context) {
        super(inputData, hand, context);
    }

    @Override
    public Double calculateAndGetAreaDifference() {
        return 0D;
    }

    @Override
    public boolean calculateResultA() {
        if(!getMaxSensResult().isEmpty()){
            OneSensorLongMeasure oneSensorShortMeasure = new OneSensorLongMeasure(getMaxSensResult());
            try{
                getA().add(new A_40_60(oneSensorShortMeasure.getSecondA1_2(), getInputData(), getContext(), 1));
                getA().add(new A_40_80(oneSensorShortMeasure.getSecondA1_3(), getInputData(), getContext(), 1));
                getA().add(new ResultA_Second1_4(oneSensorShortMeasure.getSecondA1_4(), getInputData(), getContext(), 1));
                getA().add(new ResultA_Second1_5(oneSensorShortMeasure.getSecondA1_5(), getInputData(), getContext(), 1));
                getA().add(new A_60_80(oneSensorShortMeasure.getSecondA2_3(), getInputData(), getContext(), 1));
                getA().add(new ResultA_Second2_4(oneSensorShortMeasure.getSecondA2_4(), getInputData(), getContext(), 1));
                getA().add(new ResultA_Second2_5(oneSensorShortMeasure.getSecondA2_5(), getInputData(), getContext(), 1));
                getA().add(new ResultA_Second3_4(oneSensorShortMeasure.getSecondA3_4(), getInputData(), getContext(), 1));
                getA().add(new ResultA_Second3_5(oneSensorShortMeasure.getSecondA3_5(), getInputData(), getContext(), 1));
                getA().add(new ResultA_Second5_4(oneSensorShortMeasure.getSecondA5_4(), getInputData(), getContext(), 1));
            }catch (Exception e){
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
