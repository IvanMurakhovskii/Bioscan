package com.murik.lite.model.resultbyMaxValue;

import android.content.Context;
import android.util.Log;

import com.murik.lite.Const;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.dto.OneSensorResultParametersDto;
import com.murik.lite.enums.BluetoothDimensionAlgorithm;
import com.murik.lite.model.A_30.A_10_20;
import com.murik.lite.model.A_30.A_15_30;
import com.murik.lite.model.A_30.A_15_30_GRAY;
import com.murik.lite.model.A_30.A_15_45;
import com.murik.lite.model.A_30.A_20_40;
import com.murik.lite.model.A_30.A_50_30;
import com.murik.lite.model.A_30.S_15_30;
import com.murik.lite.model.A_30.S_15_30_GRAY;
import com.murik.lite.model.A_Diagnost.ResultA_First1_2_Diagnost;
import com.murik.lite.model.A_Diagnost.ResultA_First2_3_Diagnost;
import com.murik.lite.model.A_Diagnost.ResultA_First2_4_Diagnost;
import com.murik.lite.model.A_Diagnost.ResultA_First2_5_Diagnost;
import com.murik.lite.model.A_Diagnost.ResultA_Second1_3_Diagnost;
import com.murik.lite.model.A_Diagnost.ResultA_Second1_4_Diagnost;
import com.murik.lite.model.A_Diagnost.ResultA_Second3_4_Diagnost;
import com.murik.lite.model.A_Diagnost.S_30_60_Diagnost;
import com.murik.lite.model.A_First.A_30_60;
import com.murik.lite.model.A_First.A_40_70;
import com.murik.lite.model.A_First.A_60_90;
import com.murik.lite.model.A_First.ResultA_First1_2_Gray;
import com.murik.lite.model.A_First.ResultA_First2_3_Gray;
import com.murik.lite.model.A_First.ResultA_First2_4;
import com.murik.lite.model.A_First.ResultA_First2_4_Gray;
import com.murik.lite.model.A_First.ResultA_First2_5;
import com.murik.lite.model.A_First.ResultA_First2_5_Gray;
import com.murik.lite.model.A_First.SI;
import com.murik.lite.model.A_Second.A_40_60;
import com.murik.lite.model.A_Second.A_40_80;
import com.murik.lite.model.A_Second.A_60_80;
import com.murik.lite.model.A_Second.A_90_60;
import com.murik.lite.model.A_Second.ResultA_Second3_4;
import com.murik.lite.model.A_animals.A_20_30_Animals;
import com.murik.lite.model.A_animals.A_20_60_Animals;
import com.murik.lite.model.A_animals.E_2_60_Animals;
import com.murik.lite.model.A_animals.E_60_Animals;
import com.murik.lite.model.A_animals.ResultA_First1_2_Animals;
import com.murik.lite.model.A_animals.ResultA_First40_70_Animals;
import com.murik.lite.model.A_animals.ResultA_Second1_2_Animals;
import com.murik.lite.model.A_animals.ResultA_Second1_3_4_60_Animals;
import com.murik.lite.model.A_animals.S_30_60_Animals;
import com.murik.lite.model.A_animals.TAU_60_Animals;
import com.murik.lite.model.OneSensorLongMeasure;
import com.murik.lite.model.OneSensorShortMeasure;
import com.murik.lite.model.R1_2;
import com.murik.lite.model.ResultAFactory;
import com.murik.lite.model.ResultBySens;
import com.murik.lite.model.SensorValueAttitudeFor30;
import com.murik.lite.model.TAU.TAU_30;
import com.murik.lite.model.TAU.TAU_60;
import com.murik.lite.model.TAU.TAU_80;
import com.murik.lite.model.TAU.T_60;
import com.murik.lite.model.TAU.T_80;
import com.murik.lite.model.common_A.A_15_20;
import com.murik.lite.model.common_A.A_20_30;
import com.murik.lite.model.common_A.A_20_30_GRAY;
import com.murik.lite.model.common_A.A_20_30_for_30;
import com.murik.lite.model.common_A.A_20_30_for_80;
import com.murik.lite.model.common_A.A_20_60;
import com.murik.lite.model.common_A.A_20_60_GRAY;
import com.murik.lite.model.common_A.A_25_45;
import com.murik.lite.model.common_A.Result_L;
import com.murik.lite.model.common_A.S_30_60;
import com.murik.lite.model.common_A.S_30_60_GRAY;
import com.murik.lite.model.total.TotalResult;
import com.murik.lite.model.total.TotalResult_60;
import com.murik.lite.model.Е.E_2_60;
import com.murik.lite.model.Е.E_2_60_GREY;
import com.murik.lite.model.Е.E_2_DIAGNOST;
import com.murik.lite.model.Е.E_2_DIAGNOST_GRAY;
import com.murik.lite.model.Е.E_30;
import com.murik.lite.model.Е.E_60;
import com.murik.lite.model.Е.E_80;
import com.murik.lite.service.Impl.BaseMeasureService;
import com.murik.lite.utils.ListUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.val;
import lombok.var;

import static com.murik.lite.service.Impl.BaseMeasureService.calculateDeltaTau;
import static com.murik.lite.service.Impl.BaseMeasureService.calculateDifferenceLeftRight;
import static com.murik.lite.service.Impl.BaseMeasureService.calculateT;
import static com.murik.lite.service.Impl.BaseMeasureService.calculateT_80;
import static com.murik.lite.service.Impl.BaseMeasureService.getAreaByMask;
import static com.murik.lite.service.Impl.BaseMeasureService.getOneSensorResultParameters;
import static com.murik.lite.service.Impl.BaseMeasureService.getPS2435;
import static com.murik.lite.service.Impl.BaseMeasureService.getPS3425;
import static com.murik.lite.service.Impl.BaseMeasureService.round;
import static com.murik.lite.utils.ListUtils.getData;
import static com.murik.lite.utils.SummaryColorCoefficientUtils.getColorCoefficient;

@Getter
public class ResultAFactoryOneSensor extends ResultAFactory {

    private OneSensorShortMeasure oneSensorShortMeasure;
    private OneSensorLongMeasure oneSensorLongMeasure;
    private SensorValueAttitudeFor30 sensorValueAttitudeFor30;

    private List<TotalResult> totalIndicators = new ArrayList<>();

    private int hand;

    public ResultAFactoryOneSensor(DataByMaxParcelable inputData, int hand, Context context) {
        super(inputData, hand, context);
        this.hand = hand;
        oneSensorShortMeasure = new OneSensorShortMeasure(getMaxSensResult());
        oneSensorLongMeasure = new OneSensorLongMeasure(getMaxSensResult());
        sensorValueAttitudeFor30 = new SensorValueAttitudeFor30(getMaxSensResult());

    }

    @Override
    public Double calculateAndGetAreaDifference() {

        Double bodyLeft = 0d;
        Double bodyRight = 0d;

        Double energyRight = 0d;
        Double energyLeft = 0d;

        Double discreteLeft = 0d;
        Double discreteRight = 0d;

        val algorithm = BluetoothDimensionAlgorithm.getByAlgorithmId(getInputData().getAlgorithmId());
        val leftSensorData = getInputData().getLeftHandDataSensor();
        val rightSensorData = getInputData().getRightHandDataSensor();

        if (algorithm.equals(BluetoothDimensionAlgorithm.SIMPLE)) {
            bodyLeft = getAreaByMask(Const.BODY_30, leftSensorData);
            bodyRight = getAreaByMask(Const.BODY_30, rightSensorData);

            energyLeft = getAreaByMask(Const.ENERGY_30, leftSensorData);
            energyRight = getAreaByMask(Const.ENERGY_30, rightSensorData);

            discreteLeft = getAreaByMask(Const.DISCRETE_30, leftSensorData);
            discreteRight = getAreaByMask(Const.DISCRETE_30, rightSensorData);
        } else if (algorithm.equals(BluetoothDimensionAlgorithm.BASE)) {
            bodyLeft = getAreaByMask(Const.BODY, leftSensorData);
            bodyRight = getAreaByMask(Const.BODY, rightSensorData);

            energyLeft = getAreaByMask(Const.ENERGY_160, leftSensorData);
            energyRight = getAreaByMask(Const.ENERGY_160, rightSensorData);

            discreteLeft = getAreaByMask(Const.DISCRETE_160, leftSensorData);
            discreteRight = getAreaByMask(Const.DISCRETE_160, rightSensorData);
        } else if (algorithm.equals(BluetoothDimensionAlgorithm._200)) {
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

        if (getMaxSensResult() != null && !getMaxSensResult().isEmpty()) {

            val algorithm = BluetoothDimensionAlgorithm.getByAlgorithmId(getInputData().getAlgorithmId());

            val PS_2435 = getPS2435(getMaxSensResult(), Const.SHORT);
            val PS_3425 = getPS3425(getMaxSensResult(), Const.SHORT);

            double si = (PS_2435 != 0) ? PS_3425 / PS_2435 : -9999;

            OneSensorResultParametersDto oneSensorResultParameters;

            var a_20_30 = 0d;
            var a_20_60 = 0d;
            var a_40_70 = 0d;

            val a20 = getData(getMaxSensResult(), 20);
            val a30 = getData(getMaxSensResult(), 30);
            val a40 = getData(getMaxSensResult(), 40);
            val a60 = getData(getMaxSensResult(), 60);
            val a70 = getData(getMaxSensResult(), 70);

            a_20_30 = a30 == 0 ? -9999 : round((double) a20 / a30);
            a_20_60 = a60 == 0 ? -9999 : round((double) a20 / a60);
            a_40_70 = a70 == 0 ? -9999 : round((double) a40 / a70);

            oneSensorResultParameters = getOneSensorResultParameters(getMaxSensResult(), algorithm);

            val s20_30 = oneSensorResultParameters.getS20_30();
            val s20_60 = oneSensorResultParameters.getS20_60();
            val s30_60 = oneSensorResultParameters.getS30_60();
            val L = oneSensorResultParameters.getL();
            val En = oneSensorResultParameters.getEn();
            val E2 = oneSensorResultParameters.getE2();

            try {
                val tau = calculateDeltaTau(algorithm.getMaxSignalTime(), getMaxSensResult());
                val T = calculateT(algorithm.getMaxSignalTime(), getMaxSensResult());

                if (getInputData().getSensorType().equals(Const.DIAGNOST)) {
                    createParametersForDiagnost(si, a_20_30, a_20_60, s30_60, L, En, E2);
                } else {
                    if (getInputData().isAnimalsSelected()) {
                        createParametersFor_Animals(a_20_30, a_20_60, a_40_70, s20_30, s20_60, s30_60, En, E2, tau);
                        return true;
                    }
                    if (algorithm.equals(BluetoothDimensionAlgorithm._200)) {
                        createParametersFor_80(si, a_20_30, a_20_60, s30_60, L, En, E2, tau);
                    } else if (algorithm.equals(BluetoothDimensionAlgorithm.BASE)) {
                        val S_60 = BaseMeasureService.getAreaByMask(Const.MASK_60, getMaxSensResult());
                        val halfOfArea_60 = S_60 / 2.0d;

                        val r1_2 = ListUtils.findClosestValueIndex(getMaxSensResult(), halfOfArea_60);

                        createParametersFor_60(a_20_30, a_20_60, a_40_70, s30_60, En, E2, r1_2, tau, T);
                    } else if (algorithm.equals(BluetoothDimensionAlgorithm.SIMPLE)) {
                        createParametersFor_30(tau, En, E2, s20_30);
                    } else if (algorithm.equals(BluetoothDimensionAlgorithm.ADVANCED)) {
                        createParametersFor_160(a_20_30, a_20_60, a_40_70, s20_30, s20_60, s30_60, En, E2, 0, tau);
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

    private void createParametersFor_30(final int tau, final Double En, Double e2, final double a20_30) {

        val I = new A_10_20(sensorValueAttitudeFor30.getA10_20(), getInputData(), getContext(), 1.81F);
        val II = new A_15_30(sensorValueAttitudeFor30.getA15_30(), getInputData(), getContext(), 1.81F);
        val III = new A_15_45(sensorValueAttitudeFor30.calculateA(15, 45), getInputData(), getContext(), 1.81F);
        val IV = new A_20_30_for_30(a20_30, getInputData(), getContext(), "IV");
        val V = new A_20_40(sensorValueAttitudeFor30.getA20_40(), getInputData(), getContext(), 1.81F);
        val VI = new A_50_30(sensorValueAttitudeFor30.getA50_30(), getInputData(), getContext(), 1.81F);
        val tau_30 = new TAU_30(tau, getInputData(), getContext());
        val E = new E_30(En, getInputData(), getContext());
        val S = new S_15_30(sensorValueAttitudeFor30.calculateAndGetS15_30(), getInputData(), getContext(), 1.81F);
//        val E2 = new E_2_30(e2, getInputData(), getContext());

        getA().add(I);
        getA().add(II);
        getA().add(IV);
        getA().add(V);
//        getA().add(III);
//        getA().add(VI);
        getA().add(tau_30);
        getA().add(E);

        getA().add(S);
//        getA().add(E2);

        double SP = (0.95 * getColorCoefficient(II.getViewColor()))
//                + (0.95 * getColorCoefficient(III.getViewColor()))
                + (0.9 * getColorCoefficient(V.getViewColor()))
                + (0.9 * getColorCoefficient(I.getViewColor()))
                + (1 * getColorCoefficient(IV.getViewColor()))
//                + (1 * getColorCoefficient(VI.getViewColor()))
                + (1 * getColorCoefficient(S.getViewColor()))
                + (1 * getColorCoefficient(tau_30.getViewColor()));

        double SPN = (SP - 5.75) / (14.8 - 5.75);

        double YZ = (1 - SPN) * 100;

        setSummaryResult(YZ);

//        val totalResult = new TotalResult_30(getContext(), I, II, III, IV, V, VI, E, S, tau_30, hand);

//        totalIndicators = totalResult.createAndGetDescription();

        if (getInputData().isExpert()) {
            getA().add(new A_15_30_GRAY(sensorValueAttitudeFor30.getA15_30(), getInputData(), getContext(), 1.81F));
            getA().add(new A_20_30_GRAY(sensorValueAttitudeFor30.getA20_30(), getInputData(), getContext(), 1.81F));
            getA().add(new S_15_30_GRAY(sensorValueAttitudeFor30.calculateAndGetS15_30(), getInputData(), getContext(), 1.81F));
        }
    }

    private void createParametersFor_60(double a20_30, double a20_60, double a_40_70, Double s30_60, Double en, Double e2, int r1_2, final int tau, final int T) {

        val a15 = getData(getMaxSensResult(), 15);
        val a20 = getData(getMaxSensResult(), 20);
        val a25 = getData(getMaxSensResult(), 25);
        val a30 = getData(getMaxSensResult(), 30);
        val a40 = getData(getMaxSensResult(), 40);
        val a45 = getData(getMaxSensResult(), 45);
        val a60 = getData(getMaxSensResult(), 60);
        val a70 = getData(getMaxSensResult(), 70);

        val a_15_20 = a20 == 0 ? -9999 : round((double) a15 / a20);
        val a_25_45 = a45 == 0 ? -9999 : round((double) a25 / a45);
        val a_30_60 = a60 == 0 ? -9999 : round((double) a30 / a60);
        val a_40_60 = a60 == 0 ? -9999 : round((double) a40 / a60);


        val I = new A_40_70(a_40_70, getInputData(), getContext(), 1.81F);
        val II = new A_40_60(a_40_60, getInputData(), getContext(), 1.19F);
        val III = new A_30_60(a_30_60, getInputData(), getContext(), "III");
        val IV = new A_20_30(a20_30, getInputData(), getContext(), "IV");
        val V = new A_20_60((a20_60), getInputData(), getContext(), "V");
        III.setStressResult();
        val VI = new A_90_60(oneSensorShortMeasure.getFirstA3_2(), getInputData(), getContext(), 0.39F);
        val E = new E_60(en, getInputData(), getContext());
//        val E2 = new E_2_60(e2, getInputData(), getContext());
        val S_30_60 = new S_30_60(s30_60, getInputData(), getContext(), 1);
        val VI_L = new A_25_45(a_25_45, getInputData(), getContext(), 1);
        val VII_L = new A_15_20(a_15_20, getInputData(), getContext(), 1);
        val TAU = new TAU_60(tau, getInputData(), getContext());
        val T_60 = new T_60(T, getInputData(), getContext());
        val R1_2 = new R1_2(r1_2, getInputData(), getContext());
        val S_15_30 = new S_15_30(sensorValueAttitudeFor30.calculateAndGetS15_30(), getInputData(), getContext(), 1.81F);

        val I_30 = new A_10_20(sensorValueAttitudeFor30.getA10_20(), getInputData(), getContext(), 1.81F, "I_30");
        val II_30 = new A_15_30(sensorValueAttitudeFor30.getA15_30(), getInputData(), getContext(), 1.81F, "II_30");

        getA().add(II);
        getA().add(III);
        getA().add(V);
//        getA().add(I);
        getA().add(IV);
//        getA().add(VI);
        getA().add(T_60);
//        getA().add(E);
//        getA().add(R1_2);
        getA().add(S_30_60);
        getA().add(S_15_30);
        getA().add(VI_L);
        getA().add(VII_L);
        getA().add(II_30);
        getA().add(I_30);

//        getA().add(E2);

        double SP = (getColorCoefficient(II.getViewColor()))
                + (getColorCoefficient(III.getViewColor()))
                + (getColorCoefficient(V.getViewColor()))
                + (getColorCoefficient(I_30.getViewColor()) * 0.9d)
                + (getColorCoefficient(IV.getViewColor()))
                + (getColorCoefficient(T_60.getViewColor()))
                + (getColorCoefficient(S_30_60.getViewColor()))
                + (getColorCoefficient(II_30.getViewColor()) * 0.95d)
                + (getColorCoefficient(VII_L.getViewColor()) * 0.9d)
                + (getColorCoefficient(VI_L.getViewColor()))
                + (getColorCoefficient(S_15_30.getViewColor()));

        double SPN = (SP - 10.65) / (25.7 - 10.65);

        double YZ = (1 - SPN) * 100;

        setSummaryResult(YZ);


//        val totalResult = new TotalResult_60(getContext(), I, II, III, IV, V, VI, E, S_30_60, TAU, hand);

//        totalIndicators = totalResult.createAndGetDescription();

        if (getInputData().isExpert()) {
            getA().add(new ResultA_First2_3_Gray(a_40_70, getInputData(), getContext(), 1));
            getA().add(new ResultA_First1_2_Gray(a_30_60, getInputData(), getContext(), "III_G"));
            getA().add(new A_20_30_GRAY((a20_30), getInputData(), getContext(), "IV_G"));
            getA().add(new A_20_60_GRAY((a20_60), getInputData(), getContext(), "V_G"));
            getA().add(new S_30_60_GRAY((s30_60), getInputData(), getContext(), 1.63F));
            getA().add(new E_2_60_GREY(e2, getInputData(), getContext()));
        }
    }

    private void createParametersFor_80(double si, double a20_30, double a20_60, Double s30_60, Double l, Double en, Double E2, final int tau) {

        val a30 = getData(getMaxSensResult(), 30);
        val a40 = getData(getMaxSensResult(), 40);
        val a60 = getData(getMaxSensResult(), 60);
        val a80 = getData(getMaxSensResult(), 70);
        val a90 = getData(getMaxSensResult(), 90);

        val a_30_60 = a60 == 0 ? -9999 : round((double) a30 / a60);
        val a_40_60 = a60 == 0 ? -9999 : round((double) a40 / a60);
        val a_40_80 = a80 == 0 ? -9999 : round((double) a40 / a80);
        val a_60_80 = a80 == 0 ? -9999 : round((double) a60 / a80);
        val a_60_90 = a90 == 0 ? -9999 : round((double) a60 / a90);

        val T = calculateT_80(80, getMaxSensResult());

        val I = new A_60_90(a_60_90, getInputData(), getContext(), 1.81F);
        val II = new A_40_60(a_40_60, getInputData(), getContext(), 1.19F);
        val IV = new A_30_60(a_30_60, getInputData(), getContext(), 1.35F);
        val V = new A_20_30_for_80(a20_30, getInputData(), getContext(), 1.24F);
        val III = new A_60_80(a_60_80, getInputData(), getContext(), 1.1F);
        val VI = new A_40_80(a_40_80, getInputData(), getContext(), 1.3F);
        val VII = new ResultA_First2_5(1 / oneSensorShortMeasure.getFirstA2_5(), getInputData(), getContext(), 0.47F);
        val VIII = new ResultA_First2_4(1 / oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 0.53F);
        val IX = new A_20_60((a20_60), getInputData(), getContext(), 1.63F);
        val XI = new ResultA_Second3_4(1 / oneSensorLongMeasure.getSecondA3_4(), getInputData(), getContext(), 0.39F);
        val Δτ = new TAU_80(tau, getInputData(), getContext());
        val S = new S_30_60((s30_60), getInputData(), getContext(), 1.63F);
        val T_80 = new T_80(T, getInputData(), getContext());

//        getA().add(new ResultA_Second1_4(1 / oneSensorLongMeasure.getSecondA1_4(), getInputData(), getContext(), 0.39F));
//        getA().add(new SI(si, getInputData(), getContext()));
        getA().add(II);
        getA().add(III);
        getA().add(IV);
        getA().add(VI);
        getA().add(S);
        getA().add(Δτ);
        getA().add(IX);
        getA().add(V);
        getA().add(I);
        getA().add(T_80);
//        getA().add(VII);
//        getA().add(VIII);
//        getA().add(XI);
//        getA().add(new E_80(en, getInputData(), getContext()));
//        getA().add(new E_2_80(E2, getInputData(), getContext()));

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


        double SP = (1 * getColorCoefficient(II.getViewColor()))
                + (1 * getColorCoefficient(III.getViewColor()))
                + (1 * getColorCoefficient(IV.getViewColor()))
                + (1 * getColorCoefficient(VI.getViewColor()))
                + (1 * getColorCoefficient(S.getViewColor()))
                + (getColorCoefficient(Δτ.getViewColor()) * 0.9)
                + (1 * getColorCoefficient(IX.getViewColor()))
                + (getColorCoefficient(V.getViewColor()) * 0.95)
                + (getColorCoefficient(I.getViewColor()) * 0.9);

        double SPN = (SP - 8.75) / (21.4 - 8.75);

        double YZ = (1 - SPN) * 100;

        setSummaryResult(YZ);
    }

    private void createParametersFor_160(double a20_30, double a20_60, double a_40_70, Double s20_30, Double s20_60, Double s30_60, Double en, Double e2, int r1_2, final int tau) {

        val I = new A_40_70(a_40_70, getInputData(), getContext(), 1.81F);
        val II = new A_40_60(oneSensorLongMeasure.getSecondA1_2(), getInputData(), getContext(), 1.19F);
        val III = new A_30_60(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), "III");
        val IV = new A_20_30(a20_30, getInputData(), getContext(), "IV");
        val V = new A_20_60((a20_60), getInputData(), getContext(), "V");
        val VI = new A_90_60(oneSensorShortMeasure.getFirstA3_2(), getInputData(), getContext(), 0.39F);
        val E = new E_60(en, getInputData(), getContext());
        val E2 = new E_2_60(e2, getInputData(), getContext());
        val S_30_60 = new S_30_60((s30_60), getInputData(), getContext(), 1);
        val TAU = new TAU_60(tau, getInputData(), getContext());

        getA().add(II);
        getA().add(III);
        getA().add(V);
        getA().add(I);
        getA().add(IV);
        getA().add(VI);
        getA().add(TAU);
        getA().add(E);
        getA().add(S_30_60);
        getA().add(E2);

        double SP = (1 * getColorCoefficient(II.getViewColor()))
                + (1 * getColorCoefficient(III.getViewColor()))
                + (1 * getColorCoefficient(V.getViewColor()))
                + (getColorCoefficient(I.getViewColor()) * 0.9)
                + (1 * getColorCoefficient(IV.getViewColor()))
                + (getColorCoefficient(VI.getViewColor()) * 0.9)
                + (1 * getColorCoefficient(S_30_60.getViewColor()))
                + (getColorCoefficient(TAU.getViewColor()) * 0.9)
                + (getColorCoefficient(E.getViewColor()) * 0.9)
                + (getColorCoefficient(E2.getViewColor()) * 0.9);

        double SPN = (SP - 9.5) / (23.1 - 9.5);

        double YZ = (1 - SPN) * 100;

        setSummaryResult(YZ);

        val totalResult = new TotalResult_60(getContext(), I, II, III, IV, V, VI, E, S_30_60, TAU, hand);

        totalIndicators = totalResult.createAndGetDescription();

        if (getInputData().isExpert()) {
            getA().add(new ResultA_First2_3_Gray(a_40_70, getInputData(), getContext(), 1));
            getA().add(new ResultA_First1_2_Gray(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), "III_G"));
            getA().add(new A_20_30_GRAY((a20_30), getInputData(), getContext(), "IV_G"));
            getA().add(new A_20_60_GRAY((a20_60), getInputData(), getContext(), "V_G"));
            getA().add(new S_30_60_GRAY((s30_60), getInputData(), getContext(), 1.63F));
            getA().add(new E_2_60_GREY(e2, getInputData(), getContext()));
        }
    }

    private void createParametersForDiagnost(double si, double a20_30, double a20_60, Double s30_60, Double l, Double en, Double E2) {
        getA().add(new SI(si, getInputData(), getContext()));
        getA().add(new ResultA_First2_3_Diagnost(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1.81F));
        getA().add(new ResultA_First1_2_Diagnost(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), 1.35F));
        getA().add(new ResultA_Second1_3_Diagnost(oneSensorLongMeasure.getSecondA1_3(), getInputData(), getContext(), 1.3F));
        getA().add(new ResultA_First2_5_Diagnost(1 / oneSensorShortMeasure.getFirstA5_2(), getInputData(), getContext(), 0.47F));
        getA().add(new ResultA_First2_4_Diagnost(1 / oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 0.53F));
        getA().add(new ResultA_Second1_4_Diagnost(1 / oneSensorLongMeasure.getSecondA1_4(), getInputData(), getContext(), 0.39F));
        getA().add(new ResultA_Second3_4_Diagnost(1 / oneSensorLongMeasure.getSecondA3_4(), getInputData(), getContext(), 0.39F));
        getA().add(new E_2_DIAGNOST(E2, getInputData(), getContext()));
        getA().add(new S_30_60_Diagnost((s30_60), getInputData(), getContext(), 1.63F));

        if (getInputData().isExpert()) {
            getA().add(new ResultA_First2_3_Gray(oneSensorShortMeasure.getFirstA2_3(), getInputData(), getContext(), 1));
            getA().add(new A_20_30_GRAY((a20_30), getInputData(), getContext(), 1.24F));
            getA().add(new ResultA_First1_2_Gray(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), 1));
            getA().add(new ResultA_First2_5_Gray(1 / oneSensorShortMeasure.getFirstA5_2(), getInputData(), getContext(), 1));
            getA().add(new ResultA_First2_4_Gray(1 / oneSensorShortMeasure.getFirstA2_4(), getInputData(), getContext(), 1));
            getA().add(new A_20_60_GRAY((a20_60), getInputData(), getContext(), 1.24F));
            getA().add(new E_2_DIAGNOST_GRAY(E2, getInputData(), getContext()));
            getA().add(new S_30_60_GRAY((s30_60), getInputData(), getContext(), 1.63F));
        }

        getA().add(new E_80(en, getInputData(), getContext()));
        getA().add(new Result_L(l, getInputData(), getContext()));

    }

    private void createParametersFor_Animals(double a20_30, double a20_60, double a_40_70,
                                             Double s20_30, Double s20_60, Double s30_60, Double en,
                                             Double e2, final int tau) {

        val I = new ResultA_First40_70_Animals(a_40_70, getInputData(), getContext(), 1.81F);
        val II = new ResultA_Second1_2_Animals(oneSensorLongMeasure.getSecondA1_2(), getInputData(), getContext(), 1.19F);
        val III = new ResultA_First1_2_Animals(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), "III");
        val IV = new A_20_30_Animals(a20_30, getInputData(), getContext(), "IV");
        val V = new A_20_60_Animals((a20_60), getInputData(), getContext(), "V");
        val VI = new ResultA_Second1_3_4_60_Animals(oneSensorShortMeasure.getFirstA3_2(), getInputData(), getContext(), 0.39F);
        val E = new E_60_Animals(en, getInputData(), getContext());
        val E2 = new E_2_60_Animals(e2, getInputData(), getContext());
        val S_30_60 = new S_30_60_Animals((s30_60), getInputData(), getContext(), 1);
        val TAU = new TAU_60_Animals(tau, getInputData(), getContext());

        getA().add(II);
        getA().add(III);
        getA().add(V);
        getA().add(I);
        getA().add(IV);
        getA().add(VI);
        getA().add(TAU);
        getA().add(E);
        getA().add(S_30_60);
        getA().add(E2);
    }

//    private double round(double value) {
//        if (value > 1.0) {
//            return new BigDecimal(value).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
//        } else {
//            return new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
//        }
//    }

    @Override
    public boolean calculateStressResultA() {

        if (getMaxSensResult() != null && !getMaxSensResult().isEmpty()) {

            val algorithm = BluetoothDimensionAlgorithm.getByAlgorithmId(getInputData().getAlgorithmId());

            val PS_2435 = getPS2435(getMaxSensResult(), Const.SHORT);
            val PS_3425 = getPS3425(getMaxSensResult(), Const.SHORT);

            double si = (PS_2435 != 0) ? PS_3425 / PS_2435 : -9999;

            OneSensorResultParametersDto oneSensorResultParameters;

            var a_20_30 = 0d;
            var a_20_60 = 0d;
            var a_40_70 = 0d;

            val a20 = getData(getMaxSensResult(), 20);
            val a30 = getData(getMaxSensResult(), 30);
            val a40 = getData(getMaxSensResult(), 40);
            val a60 = getData(getMaxSensResult(), 60);
            val a70 = getData(getMaxSensResult(), 70);

            a_20_30 = a30 == 0 ? -9999 : round((double) a20 / a30);
            a_20_60 = a60 == 0 ? -9999 : round((double) a20 / a60);
            a_40_70 = a70 == 0 ? -9999 : round((double) a40 / a70);

            oneSensorResultParameters = getOneSensorResultParameters(getMaxSensResult(), algorithm);

            val s20_30 = oneSensorResultParameters.getS20_30();
            val s20_60 = oneSensorResultParameters.getS20_60();
            val s30_60 = oneSensorResultParameters.getS30_60();
            val L = oneSensorResultParameters.getL();
            val En = oneSensorResultParameters.getEn();
            val E2 = oneSensorResultParameters.getE2();

            try {
                val tau = calculateDeltaTau(algorithm.getMaxSignalTime(), getMaxSensResult());
                val T = calculateT(algorithm.getMaxSignalTime(), getMaxSensResult());

                if (getInputData().getSensorType().equals(Const.DIAGNOST)) {
                    createParametersForDiagnost(si, a_20_30, a_20_60, s30_60, L, En, E2);
                } else {
                    if (getInputData().isAnimalsSelected()) {
                        createParametersFor_Animals(a_20_30, a_20_60, a_40_70, s20_30, s20_60, s30_60, En, E2, tau);
                        return true;
                    }
                    if (algorithm.equals(BluetoothDimensionAlgorithm._200)) {
                        createParametersFor_80(si, a_20_30, a_20_60, s30_60, L, En, E2, tau);
                    } else if (algorithm.equals(BluetoothDimensionAlgorithm.BASE)) {
                        val S_60 = BaseMeasureService.getAreaByMask(Const.MASK_60, getMaxSensResult());
                        val halfOfArea_60 = S_60 / 2.0d;

                        val r1_2 = ListUtils.findClosestValueIndex(getMaxSensResult(), halfOfArea_60);

                        createStressParametersFor_60(a_20_30, a_20_60, a_40_70, s30_60, En, E2, r1_2, tau, T);
                    } else if (algorithm.equals(BluetoothDimensionAlgorithm.SIMPLE)) {
                        createParametersFor_30(tau, En, E2, s20_30);
                    } else if (algorithm.equals(BluetoothDimensionAlgorithm.ADVANCED)) {
                        createParametersFor_160(a_20_30, a_20_60, a_40_70, s20_30, s20_60, s30_60, En, E2, 0, tau);
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
    private void createStressParametersFor_60(double a20_30, double a20_60, double a_40_70, Double s30_60, Double en, Double e2, int r1_2, final int tau, final int T) {

        val a15 = getData(getMaxSensResult(), 15);
        val a20 = getData(getMaxSensResult(), 20);
        val a25 = getData(getMaxSensResult(), 25);
        val a30 = getData(getMaxSensResult(), 30);
        val a40 = getData(getMaxSensResult(), 40);
        val a45 = getData(getMaxSensResult(), 45);
        val a60 = getData(getMaxSensResult(), 60);
        val a70 = getData(getMaxSensResult(), 70);

        val a_15_20 = a20 == 0 ? -9999 : round((double) a15 / a20);
        val a_25_45 = a45 == 0 ? -9999 : round((double) a25 / a45);
        val a_30_60 = a60 == 0 ? -9999 : round((double) a30 / a60);
        val a_40_60 = a60 == 0 ? -9999 : round((double) a40 / a60);


        val I = new A_40_70(a_40_70, getInputData(), getContext(), 1.81F);
        val II = new A_40_60(a_40_60, getInputData(), getContext(), 1.19F);
        II.setStressResult();
        val III = new A_30_60(a_30_60, getInputData(), getContext(), "III");
        III.setStressResult();
        val IV = new A_20_30(a20_30, getInputData(), getContext(), "IV");
        IV.setStressResult();
        val V = new A_20_60((a20_60), getInputData(), getContext(), "V");
        V.setStressResult();
        val VI = new A_90_60(oneSensorShortMeasure.getFirstA3_2(), getInputData(), getContext(), 0.39F);
        val E = new E_60(en, getInputData(), getContext());
//        val E2 = new E_2_60(e2, getInputData(), getContext());
        val S_30_60 = new S_30_60(s30_60, getInputData(), getContext(), 1);
        S_30_60.setStressResult();
        val VI_L = new A_25_45(a_25_45, getInputData(), getContext(), 1);
        VI_L.setStressResult();
        val VII_L = new A_15_20(a_15_20, getInputData(), getContext(), 1);
        VII_L.setStressResult();
        val TAU = new TAU_60(tau, getInputData(), getContext());
        val T_60 = new T_60(T, getInputData(), getContext());
        T_60.setStressResult();
        val R1_2 = new R1_2(r1_2, getInputData(), getContext());
        val S_15_30 = new S_15_30(sensorValueAttitudeFor30.calculateAndGetS15_30(), getInputData(), getContext(), 1.81F);

        val I_30 = new A_10_20(sensorValueAttitudeFor30.getA10_20(), getInputData(), getContext(), 1.81F, "I_30");
        val II_30 = new A_15_30(sensorValueAttitudeFor30.getA15_30(), getInputData(), getContext(), 1.81F, "II_30");

        S_15_30.setStressResult();
        I_30.setStressResult();
        II_30.setStressResult();
        getA().add(II);
        getA().add(III);
        getA().add(V);
//        getA().add(I);
        getA().add(IV);
//        getA().add(VI);
        getA().add(T_60);
//        getA().add(E);
//        getA().add(R1_2);
        //getA().add(S_30_60);
        //getA().add(S_15_30);
        getA().add(VI_L);
        getA().add(VII_L);
        getA().add(II_30);
        getA().add(I_30);

//        getA().add(E2);

        double SP = (getColorCoefficient(II.getViewColor()))
                + (getColorCoefficient(III.getViewColor()))
                + (getColorCoefficient(V.getViewColor()))
                + (getColorCoefficient(I_30.getViewColor()) * 0.9d)
                + (getColorCoefficient(IV.getViewColor()))
                + (getColorCoefficient(T_60.getViewColor()))
                + (getColorCoefficient(S_30_60.getViewColor()))
                + (getColorCoefficient(II_30.getViewColor()) * 0.95d)
                + (getColorCoefficient(VII_L.getViewColor()) * 0.9d)
                + (getColorCoefficient(VI_L.getViewColor()))
                + (getColorCoefficient(S_15_30.getViewColor()));

        double SPN = (SP - 10.65) / (25.7 - 10.65);

        double YZ = (1 - SPN) * 100;
        ArrayList<ResultBySens> list = getA();
        double sum =0 ;
        for (ResultBySens resultBySens:list
                ) {
            sum+= resultBySens.getA()/resultBySens.Normalise();
        }
        if (!list.isEmpty()) {
            sum/=list.size();
        }

        setSummaryResult((1-sum)*100);


//        val totalResult = new TotalResult_60(getContext(), I, II, III, IV, V, VI, E, S_30_60, TAU, hand);

//        totalIndicators = totalResult.createAndGetDescription();

        if (getInputData().isExpert()) {
            getA().add(new ResultA_First2_3_Gray(a_40_70, getInputData(), getContext(), 1));
            getA().add(new ResultA_First1_2_Gray(a_30_60, getInputData(), getContext(), "III_G"));
            getA().add(new A_20_30_GRAY((a20_30), getInputData(), getContext(), "IV_G"));
            getA().add(new A_20_60_GRAY((a20_60), getInputData(), getContext(), "V_G"));
            getA().add(new S_30_60_GRAY((s30_60), getInputData(), getContext(), 1.63F));
            getA().add(new E_2_60_GREY(e2, getInputData(), getContext()));
        }
    }
}
