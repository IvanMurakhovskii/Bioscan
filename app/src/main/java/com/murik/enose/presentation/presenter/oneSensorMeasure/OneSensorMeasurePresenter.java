package com.murik.enose.presentation.presenter.oneSensorMeasure;

import android.content.Context;
import android.graphics.Color;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.RadarEntry;
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.Screens;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.AreaDifferenceDiscrete;
import com.murik.enose.model.AreaDifferenceEnergy;
import com.murik.enose.model.AreaDifferenceTotal;
import com.murik.enose.model.resultbyMaxValue.AreaDifference;
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
        for (int i = 0; i < rightHandData.size(); i++) {
            if (rightHandData.get(i) < 0) {
                rightHandData.set(i, 0);
            }
        }

        ArrayList<RadarEntry> entryLeftHand = new ArrayList<>();
        ArrayList<RadarEntry> entryRightHand = new ArrayList<>();

        for (int i = 0; i < leftHandData.size(); i++) {
            entryLeftHand.add(new RadarEntry(leftHandData.get(i), i));
        }
        for (int i = 0; i < rightHandData.size(); i++) {
            entryRightHand.add(new RadarEntry(rightHandData.get(i), i));
        }

        getViewState().initRadarChart(
                entryLeftHand,
                entryRightHand,
                dataByMaxParcelable.getDescriptions(),
                colorLeft,
                colorRight
        );
    }

    public void createRadarChart(int mPage, final DataByMaxParcelable dataByMaxParcelable, final BaseMeasureService measureService, final Context context) {
        float areaLeft = 0;
        float areaRight = 0;

        float difference = 0;

        float areaBodyLeft;
        float areaBodyRight;

        if (dataByMaxParcelable.getTimeRegistrationMaxSignal() == 30) {
            areaBodyLeft = measureService.getAreaByMask(Const.BODY_30, dataByMaxParcelable.getLeftHandDataSensor());
            areaBodyRight = measureService.getAreaByMask(Const.BODY_30, dataByMaxParcelable.getRightHandDataSensor());
        } else {
            areaBodyLeft = measureService.getAreaByMask(Const.BODY, dataByMaxParcelable.getLeftHandDataSensor());
            areaBodyRight = measureService.getAreaByMask(Const.BODY, dataByMaxParcelable.getRightHandDataSensor());
        }

        AreaDifference areaDifference = null;

        switch (mPage) {
            case Const.PAGE_BODY:
                if (dataByMaxParcelable.getTimeRegistrationMaxSignal() == 30) {
                    initRadarChart(Const.BODY_30, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = areaBodyLeft;
                    areaRight = areaBodyRight;

                    difference = measureService.calculateDifferenceLeftRight(areaLeft, areaRight);
                    areaDifference = new AreaDifferenceTotal(difference, dataByMaxParcelable.getGender(), context);
                } else {
                    initRadarChart(Const.BODY, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = areaBodyLeft;
                    areaRight = areaBodyRight;

                    difference = measureService.calculateDifferenceLeftRight(areaLeft, areaRight);
                    areaDifference = new AreaDifferenceTotal(difference, dataByMaxParcelable.getGender(), context);
                }
                break;
            case Const.PAGE_DISCRETE:
                if (dataByMaxParcelable.getTimeRegistrationMaxSignal() == 80) {
                    initRadarChart(Const.DISCRETE, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = measureService.getAreaByMask(Const.DISCRETE, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = measureService.getAreaByMask(Const.DISCRETE, dataByMaxParcelable.getRightHandDataSensor());
                } else if (dataByMaxParcelable.getTimeRegistrationMaxSignal() == 60) {
                    initRadarChart(Const.DISCRETE_60, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = measureService.getAreaByMask(Const.DISCRETE_60, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = measureService.getAreaByMask(Const.DISCRETE_60, dataByMaxParcelable.getRightHandDataSensor());
                } else if (dataByMaxParcelable.getTimeRegistrationMaxSignal() == 30) {
                    initRadarChart(Const.DISCRETE_30, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = measureService.getAreaByMask(Const.DISCRETE_30, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = measureService.getAreaByMask(Const.DISCRETE_30, dataByMaxParcelable.getRightHandDataSensor());
                }

                difference = measureService.calculateDifferenceLeftRight(areaLeft, areaRight);
                areaDifference = new AreaDifferenceDiscrete(difference, dataByMaxParcelable.getGender(), context);
                break;
            case Const.PAGE_ENERGY_ONE_SENSOR:
                if (dataByMaxParcelable.getTimeRegistrationMaxSignal() == 80) {
                    initRadarChart(Const.ENERGY, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = measureService.getAreaByMask(Const.ENERGY, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = measureService.getAreaByMask(Const.ENERGY, dataByMaxParcelable.getRightHandDataSensor());
                } else if (dataByMaxParcelable.getTimeRegistrationMaxSignal() == 60) {
                    initRadarChart(Const.ENERGY_60, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = measureService.getAreaByMask(Const.ENERGY_60, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = measureService.getAreaByMask(Const.ENERGY_60, dataByMaxParcelable.getRightHandDataSensor());
                } else if (dataByMaxParcelable.getTimeRegistrationMaxSignal() == 30) {
                    initRadarChart(Const.ENERGY_30, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = measureService.getAreaByMask(Const.ENERGY_30, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = measureService.getAreaByMask(Const.ENERGY_30, dataByMaxParcelable.getRightHandDataSensor());
                }
                difference = measureService.calculateDifferenceLeftRight(areaLeft, areaRight);
                areaDifference = new AreaDifferenceEnergy(difference, dataByMaxParcelable.getGender(), context);
                break;
        }

        if (areaLeft != 0 && areaRight != 0) {
            getViewState().setAreaDifference(areaDifference);
        }

        getViewState().setLeftArea(areaLeft);
        getViewState().setRightArea(areaRight);
    }

    public void btnResultClickListener(DataByMaxParcelable dataByMaxParcelable) {
        dataByMaxParcelable.setMeasureType(Const.ONE_SENSOR_MEASURE);
        App.INSTANCE.getRouter().navigateTo(Screens.RESULT_FRAGMENT, dataByMaxParcelable);
    }
}