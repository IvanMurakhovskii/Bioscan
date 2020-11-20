package com.murik.enose.presentation.presenter.oneSensorMeasure;

import android.graphics.Color;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.RadarEntry;
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.Screens;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.presentation.view.oneSensorMeasure.OneSensorMeasureView;
import com.murik.enose.service.Impl.BaseMeasureService;
import com.murik.enose.utils.ListUtils;

import java.util.ArrayList;

import lombok.val;

@InjectViewState
public class OneSensorMeasurePresenter extends MvpPresenter<OneSensorMeasureView> {

    public void initRadarChart(int[] mask, int colorLeft, int colorRight, DataByMaxParcelable dataByMaxParcelable) {

        ArrayList<Integer> leftHandData = new ArrayList<>();
        ArrayList<Integer> rightHandData = new ArrayList<>();

        for (int key : mask) {

            val sensorDataLeft = dataByMaxParcelable.getLeftHandDataSensor();
            if (ListUtils.listSizeCondition(sensorDataLeft, key)) {
                leftHandData.add(sensorDataLeft.get(key));
            }

            val sensorDataRight = dataByMaxParcelable.getRightHandDataSensor();
            if (ListUtils.listSizeCondition(sensorDataRight, key)) {
                rightHandData.add(sensorDataRight.get(key));
            }
        }

        for (int i = 0; i < leftHandData.size(); i++) {
            if (leftHandData.get(i) < 0) {
                leftHandData.set(i, 0);
            }
        }
        for (
                int i = 0; i < rightHandData.size(); i++) {
            if (rightHandData.get(i) < 0) {
                rightHandData.set(i, 0);
            }
        }

        ArrayList<RadarEntry> entryLeftHand = new ArrayList<>();
        ArrayList<RadarEntry> entryRightHand = new ArrayList<>();

        for (
                int i = 0; i < leftHandData.size(); i++) {
            entryLeftHand.add(new RadarEntry(leftHandData.get(i), i));
        }
        for (
                int i = 0; i < rightHandData.size(); i++) {
            entryRightHand.add(new RadarEntry(rightHandData.get(i), i));
        }

        getViewState().
                initRadarChart(
                        entryLeftHand,
                        entryRightHand,
                        dataByMaxParcelable.getDescriptions(),
                        colorLeft,
                        colorRight
                );
    }

    public void createRadarChart(int mPage, final DataByMaxParcelable dataByMaxParcelable, final BaseMeasureService measureService) {
        float areaLeft = 0;
        float areaRight = 0;

        float areaBodyLeft = measureService.getAreaByMask(Const.BODY, dataByMaxParcelable.getLeftHandDataSensor());
        float areaBodyRight = measureService.getAreaByMask(Const.BODY, dataByMaxParcelable.getRightHandDataSensor());

        switch (mPage) {
            case Const.PAGE_BODY:
                initRadarChart(Const.BODY, Color.RED, Color.BLUE, dataByMaxParcelable);
                areaLeft = areaBodyLeft;
                areaRight = areaBodyRight;
                break;
            case Const.PAGE_DISCRETE:
                if (dataByMaxParcelable.getTimeRegistrationMaxSignal() == 80) {
                    initRadarChart(Const.DISCRETE, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = measureService.getAreaByMask(Const.DISCRETE, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = measureService.getAreaByMask(Const.DISCRETE, dataByMaxParcelable.getRightHandDataSensor());
                } else {
                    initRadarChart(Const.DISCRETE_60, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = measureService.getAreaByMask(Const.DISCRETE_60, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = measureService.getAreaByMask(Const.DISCRETE_60, dataByMaxParcelable.getRightHandDataSensor());
                }
//                areaLeft = areaLeftDanger;
//                areaRight = areaRightDanger;
                break;
            case Const.PAGE_ENERGY_ONE_SENSOR:
                if (dataByMaxParcelable.getTimeRegistrationMaxSignal() == 80) {
                    initRadarChart(Const.ENERGY, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = measureService.getAreaByMask(Const.ENERGY, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = measureService.getAreaByMask(Const.ENERGY, dataByMaxParcelable.getRightHandDataSensor());
                    break;
                } else {
                    initRadarChart(Const.ENERGY_60, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = measureService.getAreaByMask(Const.ENERGY_60, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = measureService.getAreaByMask(Const.ENERGY_60, dataByMaxParcelable.getRightHandDataSensor());
                    break;
                }


        }

        if (areaLeft != 0 && areaRight != 0) {
            getViewState().setAreaDifference(measureService.calculateDifference(areaLeft, areaRight));
        }

        getViewState().setLeftArea(areaLeft);
        getViewState().setRightArea(areaRight);
    }

    public void btnResultClickListener(DataByMaxParcelable dataByMaxParcelable) {
        dataByMaxParcelable.setMeasureType(Const.ONE_SENSOR_MEASURE);
        App.INSTANCE.getRouter().navigateTo(Screens.RESULT_FRAGMENT, dataByMaxParcelable);
    }
}