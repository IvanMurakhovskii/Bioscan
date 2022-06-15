 package com.murik.enose.model.resultbyMaxValue;

 import android.content.Context;
 import android.util.Log;

 import com.murik.enose.Const;
 import com.murik.enose.dto.DataByMaxParcelable;
 import com.murik.enose.dto.OneSensorResultParametersDto;
 import com.murik.enose.model.A_30.A_10_20;
 import com.murik.enose.model.A_30.A_15_30;
 import com.murik.enose.model.A_30.A_15_30_GRAY;
 import com.murik.enose.model.A_30.A_15_45;
 import com.murik.enose.model.A_30.A_20_40;
 import com.murik.enose.model.A_30.A_50_30;
 import com.murik.enose.model.A_30.S_15_30;
 import com.murik.enose.model.A_30.S_15_30_GRAY;
 import com.murik.enose.model.A_Diagnost.ResultA_First1_2_Diagnost;
 import com.murik.enose.model.A_Diagnost.ResultA_First2_3_Diagnost;
 import com.murik.enose.model.A_Diagnost.ResultA_First2_4_Diagnost;
 import com.murik.enose.model.A_Diagnost.ResultA_First2_5_Diagnost;
 import com.murik.enose.model.A_Diagnost.ResultA_Second1_3_Diagnost;
 import com.murik.enose.model.A_Diagnost.ResultA_Second1_4_Diagnost;
 import com.murik.enose.model.A_Diagnost.ResultA_Second3_4_Diagnost;
 import com.murik.enose.model.A_Diagnost.S_30_60_Diagnost;
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
 import com.murik.enose.model.A_Second.ResultA_Second2_3;
 import com.murik.enose.model.A_Second.ResultA_Second3_4;
 import com.murik.enose.model.OneSensorLongMeasure;
 import com.murik.enose.model.OneSensorShortMeasure;
 import com.murik.enose.model.ResultAFactory;
 import com.murik.enose.model.SensorValueAttitudeFor30;
 import com.murik.enose.model.TAU.TAU_30;
 import com.murik.enose.model.TAU.TAU_60;
 import com.murik.enose.model.TAU.TAU_80;
 import com.murik.enose.model.common_A.A_20_30;
 import com.murik.enose.model.common_A.A_20_30_GRAY;
 import com.murik.enose.model.common_A.A_20_30_for_30;
 import com.murik.enose.model.common_A.A_20_30_for_80;
 import com.murik.enose.model.common_A.A_20_60;
 import com.murik.enose.model.common_A.A_20_60_GRAY;
 import com.murik.enose.model.common_A.Result_L;
 import com.murik.enose.model.common_A.S_30_60;
 import com.murik.enose.model.common_A.S_30_60_GRAY;
 import com.murik.enose.model.Е.E_30;
 import com.murik.enose.model.Е.E_60;
 import com.murik.enose.model.Е.E_80;

 import java.math.BigDecimal;
 import java.math.RoundingMode;

 import lombok.val;
 import lombok.var;

 import static com.murik.enose.service.Impl.BaseMeasureService.calculateDeltaTau;
 import static com.murik.enose.service.Impl.BaseMeasureService.calculateDifferenceLeftRight;
 import static com.murik.enose.service.Impl.BaseMeasureService.getAreaByMask;
 import static com.murik.enose.service.Impl.BaseMeasureService.getOneSensorResultParameters;
 import static com.murik.enose.service.Impl.BaseMeasureService.getPS2435;
 import static com.murik.enose.service.Impl.BaseMeasureService.getPS3425;

public class ResultAFactoryOneSensor extends ResultAFactory {

    private OneSensorShortMeasure oneSensorShortMeasure;
    private OneSensorLongMeasure oneSensorLongMeasure;
    private SensorValueAttitudeFor30 sensorValueAttitudeFor30;

    private int hand = 0;

    public ResultAFactoryOneSensor(DataByMaxParcelable inputData, int hand, Context context) {
        super(inputData, hand, context);
        this.hand = hand;
        oneSensorShortMeasure = new OneSensorShortMeasure(getMaxSensResult());
        oneSensorLongMeasure = new OneSensorLongMeasure(getMaxSensResult());
        sensorValueAttitudeFor30 = new SensorValueAttitudeFor30(getMaxSensResult());

    }

    @Override
    public Float calculateAndGetAreaDifference() {

        Float bodyLeft = 0F;
        Float bodyRight = 0F;

        Float energyRight = 0F;
        Float energyLeft = 0F;

        Float discreteLeft = 0F;
        Float discreteRight = 0F;

        val maxSignalTime = getInputData().getTimeRegistrationMaxSignal();
        val leftSensorData = getInputData().getLeftHandDataSensor();
        val rightSensorData = getInputData().getRightHandDataSensor();

        if (maxSignalTime == 30) {
            bodyLeft = getAreaByMask(Const.BODY_30, leftSensorData);
            bodyRight = getAreaByMask(Const.BODY_30, rightSensorData);

            energyLeft = getAreaByMask(Const.ENERGY_30, leftSensorData);
            energyRight = getAreaByMask(Const.ENERGY_30, rightSensorData);

            discreteLeft = getAreaByMask(Const.DISCRETE_30, leftSensorData);
            discreteRight = getAreaByMask(Const.DISCRETE_30, rightSensorData);
        } else if (maxSignalTime == 60) {
            bodyLeft = getAreaByMask(Const.BODY, leftSensorData);
            bodyRight = getAreaByMask(Const.BODY, rightSensorData);

            energyLeft = getAreaByMask(Const.ENERGY_60, leftSensorData);
            energyRight = getAreaByMask(Const.ENERGY_60, rightSensorData);

            discreteLeft = getAreaByMask(Const.DISCRETE_60, leftSensorData);
            discreteRight = getAreaByMask(Const.DISCRETE_60, rightSensorData);
        } else if (maxSignalTime == 80) {
            bodyLeft = getAreaByMask(Const.BODY, leftSensorData);
            bodyRight = getAreaByMask(Const.BODY, rightSensorData);

            energyLeft = getAreaByMask(Const.ENERGY, leftSensorData);
            energyRight = getAreaByMask(Const.ENERGY, rightSensorData);

            discreteLeft = getAreaByMask(Const.DISCRETE, leftSensorData);
            discreteRight = getAreaByMask(Const.DISCRETE, rightSensorData);
        }

        val bodyDifference = Math.abs(calculateDifferenceLeftRight(bodyLeft, bodyRight));
        val energyDifference = Math.abs(calculateDifferenceLeftRight(energyLeft, energyRight));
        val discreteDifference = Math.abs(calculateDifferenceLeftRight(discreteLeft, discreteRight));

        return (bodyDifference + energyDifference + discreteDifference) / 3;
    }

    @Override
    public boolean calculateResultA() {

        if (!getMaxSensResult().isEmpty()) {

            val maxSignalTime = getInputData().getTimeRegistrationMaxSignal();

//            val PS_2435_right = getPS2435(getInputData().getRightHandDataSensor(), Const.SHORT);
//            val PS_3425_right = getPS3425(getInputData().getRightHandDataSensor(), Const.SHORT);
//
////            val SI_right = PS_3425_right / PS_2435_right;
//
//            val PS_2435_left = getPS2435(getInputData().getLeftHandDataSensor(), Const.SHORT);
//            val PS_3425_left = getPS3425(getInputData().getLeftHandDataSensor(), Const.SHORT);

//            val SI_left = PS_3425_left / PS_2435_left;


            val PS_2435 = getPS2435(getMaxSensResult(), Const.SHORT);
            val PS_3425 = getPS3425(getMaxSensResult(), Const.SHORT);

            float si = (PS_2435 != 0) ? PS_3425 / PS_2435 : -9999;

//            float bosi = 0;

//            if (getInputData().getLeftHandDataSensor() != null && getInputData().getRightHandDataSensor() != null) {
//                bosi = calculateDifference(SI_left, SI_right);
//            }

            OneSensorResultParametersDto oneSensorResultParameters;

            var a_20_30 = 0d;
            var a_20_60 = 0d;
            var a_40_70 = 0d;

            if (getInputData().getLeftHandDataSensor().size() >= 70) {
                val a20 = getMaxSensResult().get(20);
                val a30 = getMaxSensResult().get(30);
                val a40 = getMaxSensResult().get(40);
                val a60 = getMaxSensResult().get(60);
                val a70 = getMaxSensResult().get(70);

                a_20_30 = a30 == 0 ? 0 : round((double) a20 / a30);
                a_20_60 = a60 == 0 ? 0 : round((double) a20 / a60);
                a_40_70 = a70 == 0 ? 0 : round((double) a40 / a70);
            }

            oneSensorResultParameters = getOneSensorResultParameters(getMaxSensResult(), maxSignalTime);

            val s20_30 = oneSensorResultParameters.getS20_30();
            val s20_60 = oneSensorResultParameters.getS20_60();
            val s30_60 = oneSensorResultParameters.getS30_60();
            val L = oneSensorResultParameters.getL();
            val En = oneSensorResultParameters.getEn();

            try {
                val tau = calculateDeltaTau(maxSignalTime, getMaxSensResult());

                if (getInputData().getSensorType().equals(Const.DIAGNOST)) {
                    createParametersForDiagnost(si, a_20_30, a_20_60, s30_60, L, En);
                } else {
                    if (maxSignalTime == 80) {
                        createParametersFor_80(si, a_20_30, a_20_60, s30_60, L, En, tau);
                    } else if (maxSignalTime == 60) {
                        createParametersFor_60(a_20_30, a_20_60, a_40_70, s20_30, s20_60, s30_60, En, tau);
                    } else if (maxSignalTime == 30) {
                        createParametersFor_30(tau, En, s20_30);
                    } else {
                        //todo сделать ограничение при выборе
                        throw new Exception("Нет подсчета параметров для выбранного максимального значения времени.");
                    }
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

    private void createParametersFor_30(final int tau, final Float En, final double a20_30) {
        getA().add(new A_10_20(sensorValueAttitudeFor30.getA10_20(), getInputData(), getContext(), 1.81F));
        getA().add(new A_15_30(sensorValueAttitudeFor30.getA15_30(), getInputData(), getContext(), 1.81F));
        getA().add(new A_20_30_for_30(a20_30, getInputData(), getContext(), "IV"));
        getA().add(new A_20_40(sensorValueAttitudeFor30.getA20_40(), getInputData(), getContext(), 1.81F));
        getA().add(new A_15_45(sensorValueAttitudeFor30.calculateA(15, 45), getInputData(), getContext(), 1.81F));
        getA().add(new A_50_30(sensorValueAttitudeFor30.getA50_30(), getInputData(), getContext(), 1.81F));
        getA().add(new TAU_30(tau, getInputData(), getContext()));
        getA().add(new E_30(En, getInputData(), getContext()));

        getA().add(new S_15_30(sensorValueAttitudeFor30.calculateAndGetS15_30(), getInputData(), getContext(), 1.81F));

        if (getInputData().isExpert()) {
            getA().add(new A_15_30_GRAY(sensorValueAttitudeFor30.getA15_30(), getInputData(), getContext(), 1.81F));
            getA().add(new A_20_30_GRAY(sensorValueAttitudeFor30.getA20_30(), getInputData(), getContext(), 1.81F));
            getA().add(new S_15_30_GRAY(sensorValueAttitudeFor30.calculateAndGetS15_30(), getInputData(), getContext(), 1.81F));
        }
    }

    private void createParametersFor_60(double a20_30, double a20_60, double a_40_70, Float s20_30, Float s20_60, Float s30_60, Float en, final int tau) {

        getA().add(new ResultA_First40_70(a_40_70, getInputData(), getContext(), 1.81F));
        getA().add(new ResultA_Second1_2(oneSensorLongMeasure.getSecondA1_2(), getInputData(), getContext(), 1.19F));
        getA().add(new A_20_30(a20_30, getInputData(), getContext(), "IV"));
        getA().add(new A_20_60((a20_60), getInputData(), getContext(), "V"));
        getA().add(new ResultA_First1_2(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), "III"));
        getA().add(new ResultA_Second1_3_4_60(oneSensorShortMeasure.getFirstA3_2(), getInputData(), getContext(), 0.39F));
        getA().add(new TAU_60(tau, getInputData(), getContext()));
        getA().add(new E_60(en, getInputData(), getContext()));
        getA().add(new S_30_60((s30_60), getInputData(), getContext(), 1));


        if (getInputData().isExpert()) {
            getA().add(new ResultA_First2_3_Gray(a_40_70, getInputData(), getContext(), 1));
            getA().add(new ResultA_First1_2_Gray(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), "III_G"));
            getA().add(new A_20_30_GRAY((a20_30), getInputData(), getContext(), "IV_G"));
            getA().add(new A_20_60_GRAY((a20_60), getInputData(), getContext(), "V_G"));
            getA().add(new S_30_60_GRAY((s30_60), getInputData(), getContext(), 1.63F));
        }
    }

    private void createParametersFor_80(float si, double a20_30, double a20_60, Float s30_60, Float l, Float en, final int tau) {
        getA().add(new SI(si, getInputData(), getContext()));
        getA().add(new ResultA_First2_3(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1.81F));
        getA().add(new ResultA_Second1_2(oneSensorLongMeasure.getSecondA1_2(), getInputData(), getContext(), 1.19F));
        getA().add(new ResultA_First1_2(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), 1.35F));
        getA().add(new A_20_30_for_80(a20_30, getInputData(), getContext(), 1.24F));
        getA().add(new ResultA_Second2_3(oneSensorLongMeasure.getSecondA2_3(), getInputData(), getContext(), 1.1F));
        getA().add(new ResultA_Second1_3(oneSensorLongMeasure.getSecondA1_3(), getInputData(), getContext(), 1.3F));
        getA().add(new ResultA_First2_5(1 / oneSensorShortMeasure.getFirstA5_2(), getInputData(), getContext(), 0.47F));
        getA().add(new ResultA_First2_4(1 / oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 0.53F));
        getA().add(new A_20_60((a20_60), getInputData(), getContext(), 1.63F));
//        getA().add(new ResultA_Second1_4(1 / oneSensorLongMeasure.getSecondA1_4(), getInputData(), getContext(), 0.39F));
        getA().add(new ResultA_Second3_4(1 / oneSensorLongMeasure.getSecondA3_4(), getInputData(), getContext(), 0.39F));
        getA().add(new TAU_80(tau, getInputData(), getContext()));
        getA().add(new E_80(en, getInputData(), getContext()));
        getA().add(new S_30_60((s30_60), getInputData(), getContext(), 1.63F));

        if (getInputData().isExpert()) {
            getA().add(new ResultA_First2_3_Gray(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1));
            getA().add(new A_20_30_GRAY((a20_30), getInputData(), getContext(), 1.24F));
            getA().add(new ResultA_First1_2_Gray(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), 1));
            getA().add(new ResultA_First2_5_Gray(1 / oneSensorShortMeasure.getFirstA5_2(), getInputData(), getContext(), 1));
            getA().add(new ResultA_First2_4_Gray(1 / oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 1));
            getA().add(new A_20_60_GRAY((a20_60), getInputData(), getContext(), 1.24F));
            getA().add(new S_30_60_GRAY((s30_60), getInputData(), getContext(), 1.63F));
        }

        getA().add(new Result_L(l, getInputData(), getContext()));
    }

    private void createParametersForDiagnost(float si, double a20_30, double a20_60, Float s30_60, Float l, Float en) {
        getA().add(new SI(si, getInputData(), getContext()));
        getA().add(new ResultA_First2_3_Diagnost(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1.81F));
        getA().add(new ResultA_First1_2_Diagnost(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), 1.35F));
        getA().add(new ResultA_Second1_3_Diagnost(oneSensorLongMeasure.getSecondA1_3(), getInputData(), getContext(), 1.3F));
        getA().add(new ResultA_First2_5_Diagnost(1 / oneSensorShortMeasure.getFirstA5_2(), getInputData(), getContext(), 0.47F));
        getA().add(new ResultA_First2_4_Diagnost(1 / oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 0.53F));
        getA().add(new ResultA_Second1_4_Diagnost(1 / oneSensorLongMeasure.getSecondA1_4(), getInputData(), getContext(), 0.39F));
        getA().add(new ResultA_Second3_4_Diagnost(1 / oneSensorLongMeasure.getSecondA3_4(), getInputData(), getContext(), 0.39F));
        getA().add(new S_30_60_Diagnost((s30_60), getInputData(), getContext(), 1.63F));

        if (getInputData().isExpert()) {
            getA().add(new ResultA_First2_3_Gray(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1));
            getA().add(new A_20_30_GRAY((a20_30), getInputData(), getContext(), 1.24F));
            getA().add(new ResultA_First1_2_Gray(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), 1));
            getA().add(new ResultA_First2_5_Gray(1 / oneSensorShortMeasure.getFirstA5_2(), getInputData(), getContext(), 1));
            getA().add(new ResultA_First2_4_Gray(1 / oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 1));
            getA().add(new A_20_60_GRAY((a20_60), getInputData(), getContext(), 1.24F));
            getA().add(new S_30_60_GRAY((s30_60), getInputData(), getContext(), 1.63F));
        }

        getA().add(new E_80(en, getInputData(), getContext()));
        getA().add(new Result_L(l, getInputData(), getContext()));
    }

    private double round(double value) {
        if(value > 1.0){
            return new BigDecimal(value).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
        } else {
            return new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        }
    }
}
