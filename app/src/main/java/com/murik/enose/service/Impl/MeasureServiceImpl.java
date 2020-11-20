package com.murik.enose.service.Impl;

import com.murik.enose.Const;
import com.murik.enose.dto.SensorDataFullParcelable;
import com.murik.enose.service.MeasureService;
import com.murik.enose.utils.ListUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import lombok.val;

public class MeasureServiceImpl extends BaseMeasureService implements MeasureService {


    private SensorDataFullParcelable sensorDataFullParcelable;

    private int[] bad = {20, 30};

    private List<Float> areaTotal;
    private List<Float> areaHealth;
    private List<Float> areaEndokrin;
    private List<Float> areaEnergy;
    private List<Float> areaBad;
    private List<Float> areaBad180;
    private Float areaTotalAverage = 0.0f;
    private Float areaTotalDifference;

    private List<Float> deltaHealth;
    private List<Float> deltaEndokrin;
    private List<Float> deltaEnergy;
    private List<Float> deltaBad;
    private List<Float> deltaBad180;
    private List<Float> areaForBadDeltaFull;
    private List<Float> areaForBadDeltaWithoutEight;

    public MeasureServiceImpl(
            SensorDataFullParcelable sensorDataFullParcelable) {
        this.sensorDataFullParcelable = sensorDataFullParcelable;
        init();
    }

    public MeasureServiceImpl() {
    }

    private void init() {
        areaTotal = calculateArea(Const.TOTAL, Const.allSens, sensorDataFullParcelable);
        areaHealth = calculateArea(Const.HEALTH, Const.allSens, sensorDataFullParcelable);
        areaEndokrin = calculateArea(Const.ENDOKRIN, Const.endocrinSens, sensorDataFullParcelable);
        areaEnergy = calculateArea(Const.ENERGY, Const.energySens, sensorDataFullParcelable);
        areaBad = calculateArea(Const.BAD, Const.allSens, sensorDataFullParcelable);
        areaBad180 = calculateArea(Const.BAD, Const.allSens, sensorDataFullParcelable);
        areaForBadDeltaFull = calculateAreaFor180(Const.BAD, Const.allSens, sensorDataFullParcelable);
        areaForBadDeltaWithoutEight = calculateAreaFor180(bad, Const.allSens, sensorDataFullParcelable);

        deltaHealth = calculateDelta(areaHealth, areaTotal);
        deltaEndokrin = calculateDelta(areaEndokrin, areaTotal);
        deltaEnergy = calculateDelta(areaEnergy, areaTotal);
        deltaBad = calculateDelta(areaBad, areaTotal);
        deltaBad180 = calculateDeltaBad(areaForBadDeltaWithoutEight, areaForBadDeltaFull);

        if (areaTotal.get(0) != 0 && areaTotal.get(1) != 0) {
            areaTotalAverage = (areaTotal.get(0) + areaTotal.get(1)) / 2;
            areaTotalDifference = 2 * ((areaTotal.get(0) - areaTotalAverage) / areaTotalAverage) * 100;
            BigDecimal bd = new BigDecimal(areaTotalDifference).setScale(1, RoundingMode.FLOOR);
            areaTotalDifference = Math.abs(bd.floatValue());
        }
    }


    private List<Float> calculateAreaFor180(int[] mask, List<String> sensNumber, SensorDataFullParcelable sensorDataFullParcelable) {
        float areaLeft = 0f;
        float areaRight = 0f;
        float dx = 1f;
        int y1 = 1;
        int y2 = 1;

        for (int key = 0; key < mask.length; key++) {
            for (String sensor : sensNumber) {
                if (!sensorDataFullParcelable.getDataSensorMapLeftHand().isEmpty()) {
                    if (sensorDataFullParcelable.getDataSensorMapLeftHand().get(sensor) != null) {
                        val dataSensorLeftHand = sensorDataFullParcelable.getDataSensorMapLeftHand().get(sensor);
                        areaLeft += ListUtils.listSizeCondition(dataSensorLeftHand, mask[key]) ? dataSensorLeftHand.get(mask[key]) : 0;
                    }
                }
                if (!sensorDataFullParcelable.getDataSensorMapRightHand().isEmpty()) {
                    if (sensorDataFullParcelable.getDataSensorMapRightHand().get(sensor) != null) {
                        val dataSensorRightHand = sensorDataFullParcelable.getDataSensorMapRightHand().get(sensor);
                        areaRight += ListUtils.listSizeCondition(dataSensorRightHand, mask[key]) ? dataSensorRightHand.get(mask[key]) : 0;
                    }
                }
            }
        }

        List<Float> area = new ArrayList<>();
        area.add(areaLeft);
        area.add(areaRight);
        return area;
    }

    private List<Float> calculateDeltaBad(List<Float> areaForBadDeltaWithoutEight, List<Float> areaForBadDeltaFull) {
        List<Float> delta = new ArrayList<>();
        float deltaLeft = 0f;
        float deltaRight = 0f;

        deltaLeft = ((areaForBadDeltaFull.get(0) - areaForBadDeltaWithoutEight.get(0)) / areaForBadDeltaFull.get(0)) * 100;
        deltaRight = ((areaForBadDeltaFull.get(1) - areaForBadDeltaWithoutEight.get(1)) / areaForBadDeltaFull.get(1)) * 100;

        if (Float.isInfinite(deltaLeft) || Float.isNaN(deltaLeft)) {
            deltaLeft = 0;
        }
        if (Float.isInfinite(deltaRight) || Float.isNaN(deltaRight)) {
            deltaRight = 0;
        }

        delta.add(deltaLeft);
        delta.add(deltaRight);

        return delta;
    }

    public List<Float> calculateArea(int[] mask, List<String> sensNumber, SensorDataFullParcelable sensorDataFullParcelable) {

        float areaLeft = 0f;
        float areaRight = 0f;
        float dx = 1f;
        int y1 = 1;
        int y2 = 1;

        for (String sensor : sensNumber) {
            for (int key = 0; key < mask.length - 1; key++) {
                dx = mask[key] - mask[key + 1];
                if (!sensorDataFullParcelable.getDataSensorMapLeftHand().isEmpty()) {
                    if (sensorDataFullParcelable.getDataSensorMapLeftHand().get(sensor) != null) {
                        val dataSensorLeftHand = sensorDataFullParcelable.getDataSensorMapLeftHand().get(sensor);

                        y1 = ListUtils.listSizeCondition(dataSensorLeftHand, mask[key]) ? dataSensorLeftHand.get(mask[key]) : 0;
                        y2 = ListUtils.listSizeCondition(dataSensorLeftHand, mask[key + 1]) ? dataSensorLeftHand.get(mask[key + 1]) : 0;
                        if (y1 * y2 > 0) {
                            areaLeft += Math.abs((y1 + y2) / 2f * dx);
                        } else {
                            float dx1 = (1f + Math.abs(y2) * dx);
                            areaLeft += Math.abs((dx1 * Math.abs(y1) + (dx - dx1) * Math.abs(y2)) / 2f);
                        }
                    }
                }
                if (!sensorDataFullParcelable.getDataSensorMapRightHand().isEmpty()) {
                    if (sensorDataFullParcelable.getDataSensorMapRightHand().get(sensor) != null) {
                        val dataSensorRightHand = sensorDataFullParcelable.getDataSensorMapRightHand().get(sensor);

                        y1 = ListUtils.listSizeCondition(dataSensorRightHand, mask[key]) ? dataSensorRightHand.get(mask[key]) : 0;
                        y2 = ListUtils.listSizeCondition(dataSensorRightHand, mask[key + 1]) ? dataSensorRightHand.get(mask[key + 1]) : 0;

                        if (y1 * y2 > 0) {
                            areaRight += Math.abs((y1 + y2) / 2f * dx);
                        } else {
                            float dx1 = (1f + Math.abs(y2) * dx);
                            areaRight += Math.abs((dx1 * Math.abs(y1) + (dx - dx1) * Math.abs(y2)) / 2f);
                        }
                    }
                }
            }
        }
        List<Float> area = new ArrayList<>();
        area.add(areaLeft);
        area.add(areaRight);
        return area;
    }

    public List<Float> calculateDelta(List<Float> area, List<Float> areaTotal) {

        float deltaLeft = 0f;
        float deltaRight = 0f;

        deltaRight = (area.get(1) / areaTotal.get(1)) * 100;
        deltaLeft = (area.get(0) / areaTotal.get(0)) * 100;

        if (Float.isInfinite(deltaLeft) || Float.isNaN(deltaLeft)) {
            deltaLeft = 0;
        }
        if (Float.isInfinite(deltaRight) || Float.isNaN(deltaRight)) {
            deltaRight = 0;
        }
        List<Float> delta = new ArrayList<>();
        delta.add(deltaLeft);
        delta.add(deltaRight);
        return delta;

    }

    public List<Float> getAreaTotal() {
        return areaTotal;
    }

    public List<Float> getAreaHealth() {
        return areaHealth;
    }

    public List<Float> getAreaEndokrin() {
        return areaEndokrin;
    }

    public List<Float> getAreaEnergy() {
        return areaEnergy;
    }

    public List<Float> getAreaBad() {
        return areaBad;
    }

    public List<Float> getDeltaHealth() {
        return deltaHealth;
    }

    public List<Float> getDeltaEndokrin() {
        return deltaEndokrin;
    }

    public List<Float> getDeltaEnergy() {
        return deltaEnergy;
    }

    public List<Float> getDeltaBad() {
        return deltaBad;
    }

    public List<Float> getDeltaBad180() {
        return deltaBad180;
    }

    @Override
    public Float getDifferenceArea() {

        return areaTotalDifference;
    }

}
