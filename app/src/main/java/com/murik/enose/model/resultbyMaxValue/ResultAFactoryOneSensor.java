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
 import com.murik.enose.model.A_animals.A_20_30_Animals;
 import com.murik.enose.model.A_animals.A_20_60_Animals;
 import com.murik.enose.model.A_animals.E_2_60_Animals;
 import com.murik.enose.model.A_animals.E_60_Animals;
 import com.murik.enose.model.A_animals.ResultA_First1_2_Animals;
 import com.murik.enose.model.A_animals.ResultA_First40_70_Animals;
 import com.murik.enose.model.A_animals.ResultA_Second1_2_Animals;
 import com.murik.enose.model.A_animals.ResultA_Second1_3_4_60_Animals;
 import com.murik.enose.model.A_animals.S_30_60_Animals;
 import com.murik.enose.model.A_animals.TAU_60_Animals;
 import com.murik.enose.model.OneSensorLongMeasure;
 import com.murik.enose.model.OneSensorShortMeasure;
 import com.murik.enose.model.R1_2;
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
 import com.murik.enose.model.total.TotalResult;
 import com.murik.enose.model.total.TotalResult_30;
 import com.murik.enose.model.total.TotalResult_60;
 import com.murik.enose.model.Е.E_2_30;
 import com.murik.enose.model.Е.E_2_60;
 import com.murik.enose.model.Е.E_2_60_GREY;
 import com.murik.enose.model.Е.E_2_80;
 import com.murik.enose.model.Е.E_2_DIAGNOST;
 import com.murik.enose.model.Е.E_2_DIAGNOST_GRAY;
 import com.murik.enose.model.Е.E_30;
 import com.murik.enose.model.Е.E_60;
 import com.murik.enose.model.Е.E_80;
 import com.murik.enose.service.Impl.BaseMeasureService;
 import com.murik.enose.utils.ListUtils;

 import java.math.BigDecimal;
 import java.math.RoundingMode;
 import java.util.ArrayList;
 import java.util.List;

 import lombok.Getter;
 import lombok.val;
 import lombok.var;

 import static com.murik.enose.service.Impl.BaseMeasureService.calculateDeltaTau;
 import static com.murik.enose.service.Impl.BaseMeasureService.calculateDifferenceLeftRight;
 import static com.murik.enose.service.Impl.BaseMeasureService.getAreaByMask;
 import static com.murik.enose.service.Impl.BaseMeasureService.getOneSensorResultParameters;
 import static com.murik.enose.service.Impl.BaseMeasureService.getPS2435;
 import static com.murik.enose.service.Impl.BaseMeasureService.getPS3425;
 import static com.murik.enose.utils.SummaryColorCoefficientUtils.getColorCoefficient;

@Getter
public class ResultAFactoryOneSensor extends ResultAFactory {

    private OneSensorShortMeasure oneSensorShortMeasure;
    private OneSensorLongMeasure oneSensorLongMeasure;
    private SensorValueAttitudeFor30 sensorValueAttitudeFor30;

    private List<TotalResult> totalIndicators = new ArrayList<>();

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
            val isAnimalsSelected = getInputData().isAnimalsSelected();

            val PS_2435 = getPS2435(getMaxSensResult(), Const.SHORT);
            val PS_3425 = getPS3425(getMaxSensResult(), Const.SHORT);

            float si = (PS_2435 != 0) ? PS_3425 / PS_2435 : -9999;

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
            val E2 = oneSensorResultParameters.getE2();

            try {
                val tau = calculateDeltaTau(maxSignalTime, getMaxSensResult());

                if (getInputData().getSensorType().equals(Const.DIAGNOST)) {
                    createParametersForDiagnost(si, a_20_30, a_20_60, s30_60, L, En, E2);
                } else {
                    if (isAnimalsSelected) {
                        createParametersFor_Animals(a_20_30, a_20_60, a_40_70, s20_30, s20_60, s30_60, En, E2, tau);
                        return true;
                    }
                    if (maxSignalTime == 80) {
                        createParametersFor_80(si, a_20_30, a_20_60, s30_60, L, En, E2, tau);
                    } else if (maxSignalTime == 60) {
                        val S_60 = BaseMeasureService.getAreaByMask(Const.MASK_60, getMaxSensResult());
                        val halfOfArea_60 = S_60/2.0d;

                        val r1_2 = ListUtils.findClosestValueIndex(getMaxSensResult(), halfOfArea_60);

                        createParametersFor_60(a_20_30, a_20_60, a_40_70, s20_30, s20_60, s30_60, En, E2, r1_2, tau);
                    } else if (maxSignalTime == 30) {
                        createParametersFor_30(tau, En, E2, s20_30);
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

    private void createParametersFor_30(final int tau, final Float En, Float e2, final double a20_30) {

        val I = new A_10_20(sensorValueAttitudeFor30.getA10_20(), getInputData(), getContext(), 1.81F);
        val II = new A_15_30(sensorValueAttitudeFor30.getA15_30(), getInputData(), getContext(), 1.81F);
        val III = new A_15_45(sensorValueAttitudeFor30.calculateA(15, 45), getInputData(), getContext(), 1.81F);
        val IV = new A_20_30_for_30(a20_30, getInputData(), getContext(), "IV");
        val V = new A_20_40(sensorValueAttitudeFor30.getA20_40(), getInputData(), getContext(), 1.81F);
        val VI = new A_50_30(sensorValueAttitudeFor30.getA50_30(), getInputData(), getContext(), 1.81F);
        val tau_30 = new TAU_30(tau, getInputData(), getContext());
        val E = new E_30(En, getInputData(), getContext());
        val S = new S_15_30(sensorValueAttitudeFor30.calculateAndGetS15_30(), getInputData(), getContext(), 1.81F);
        val E2 = new E_2_30(e2, getInputData(), getContext());

        getA().add(I);
        getA().add(II);
        getA().add(IV);
        getA().add(V);
        getA().add(III);
        getA().add(VI);
        getA().add(tau_30);
        getA().add(E);

        getA().add(S);
        getA().add(E2);

        double SP = (0.95 * getColorCoefficient(II.getViewColor()))
                + (0.95 * getColorCoefficient(III.getViewColor()))
                + (0.9 * getColorCoefficient(V.getViewColor()))
                + (0.9 * getColorCoefficient(I.getViewColor()))
                + (1 * getColorCoefficient(IV.getViewColor()))
                + (1 * getColorCoefficient(VI.getViewColor()))
                + (1 * getColorCoefficient(S.getViewColor()))
                + (1 * getColorCoefficient(tau_30.getViewColor()));

        double SPN = (SP - 7.7)/(18.8 - 7.7);

        double YZ = (1 - SPN) * 100;

        setSummaryResult(YZ);

        val totalResult = new TotalResult_30(getContext(), I, II, III, IV, V, VI, E, S, tau_30, hand);

        totalIndicators = totalResult.createAndGetDescription();

        if (getInputData().isExpert()) {
            getA().add(new A_15_30_GRAY(sensorValueAttitudeFor30.getA15_30(), getInputData(), getContext(), 1.81F));
            getA().add(new A_20_30_GRAY(sensorValueAttitudeFor30.getA20_30(), getInputData(), getContext(), 1.81F));
            getA().add(new S_15_30_GRAY(sensorValueAttitudeFor30.calculateAndGetS15_30(), getInputData(), getContext(), 1.81F));
        }
    }

    private void createParametersFor_Animals(double a20_30, double a20_60, double a_40_70, Float s20_30, Float s20_60, Float s30_60, Float en, Float e2, final int tau) {

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

    private void createParametersFor_60(double a20_30, double a20_60, double a_40_70, Float s20_30, Float s20_60, Float s30_60, Float en, Float e2, int r1_2, final int tau) {

        val I = new ResultA_First40_70(a_40_70, getInputData(), getContext(), 1.81F);
        val II = new ResultA_Second1_2(oneSensorLongMeasure.getSecondA1_2(), getInputData(), getContext(), 1.19F);
        val III = new ResultA_First1_2(oneSensorShortMeasure.getFirstA1_2(), getInputData(), getContext(), "III");
        val IV = new A_20_30(a20_30, getInputData(), getContext(), "IV");
        val V = new A_20_60((a20_60), getInputData(), getContext(), "V");
        val VI = new ResultA_Second1_3_4_60(oneSensorShortMeasure.getFirstA3_2(), getInputData(), getContext(), 0.39F);
        val E = new E_60(en, getInputData(), getContext());
        val E2 = new E_2_60(e2, getInputData(), getContext());
        val S_30_60 = new S_30_60((s30_60), getInputData(), getContext(), 1);
        val TAU = new TAU_60(tau, getInputData(), getContext());
        val R1_2 = new R1_2(r1_2, getInputData(), getContext());

        getA().add(II);
        getA().add(III);
        getA().add(V);
        getA().add(I);
        getA().add(IV);
        getA().add(VI);
        getA().add(TAU);
        getA().add(E);
//        getA().add(R1_2);
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

        double SPN = (SP - 9.5)/(23.1 - 9.5);

        double YZ = (1 - SPN)*100;

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

    private void createParametersFor_80(float si, double a20_30, double a20_60, Float s30_60, Float l, Float en, Float E2, final int tau) {
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
        getA().add(new E_2_80(E2, getInputData(), getContext()));
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

    private void createParametersForDiagnost(float si, double a20_30, double a20_60, Float s30_60, Float l, Float en, Float E2) {
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

    private double round(double value) {
        if(value > 1.0){
            return new BigDecimal(value).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
        } else {
            return new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        }
    }
}
