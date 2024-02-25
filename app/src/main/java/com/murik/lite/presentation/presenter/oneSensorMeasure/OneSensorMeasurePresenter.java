package com.murik.lite.presentation.presenter.oneSensorMeasure;

import android.content.Context;
import android.graphics.Color;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.RadarEntry;
import com.murik.lite.App;
import com.murik.lite.Const;
import com.murik.lite.Screens;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.enums.BluetoothDimensionAlgorithm;
import com.murik.lite.model.AreaDifferenceDiscrete;
import com.murik.lite.model.AreaDifferenceEnergy;
import com.murik.lite.model.AreaDifferenceTotal;
import com.murik.lite.model.resultbyMaxValue.AreaDifference;
import com.murik.lite.presentation.view.oneSensorMeasure.OneSensorMeasureView;
import com.murik.lite.service.Impl.BaseMeasureService;
import com.murik.lite.utils.ListUtils;

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

    public void createRadarChart(int mPage, final DataByMaxParcelable dataByMaxParcelable, final Context context) {
        double areaLeft = 0;
        double areaRight = 0;

        double difference;

        AreaDifference areaDifference = null;

        val algorithm = BluetoothDimensionAlgorithm.getByAlgorithmId(dataByMaxParcelable.getAlgorithmId());

        switch (mPage) {
            case Const.PAGE_BODY:
                if (algorithm.equals(BluetoothDimensionAlgorithm.SIMPLE)) {
                    initRadarChart(Const.BODY_30, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = BaseMeasureService.getAreaByMask(Const.BODY_30, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = BaseMeasureService.getAreaByMask(Const.BODY_30, dataByMaxParcelable.getRightHandDataSensor());

                    difference = BaseMeasureService.calculateDifferenceLeftRight(areaLeft, areaRight);
                    areaDifference = new AreaDifferenceTotal(difference, dataByMaxParcelable.getGender(), context);
                } else {
                    initRadarChart(Const.BODY, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = BaseMeasureService.getAreaByMask(Const.BODY, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = BaseMeasureService.getAreaByMask(Const.BODY, dataByMaxParcelable.getRightHandDataSensor());

                    difference = BaseMeasureService.calculateDifferenceLeftRight(areaLeft, areaRight);
                    areaDifference = new AreaDifferenceTotal(difference, dataByMaxParcelable.getGender(), context);
                }
                break;
            case Const.PAGE_DISCRETE:
                if (algorithm.equals(BluetoothDimensionAlgorithm._200)) {
                    initRadarChart(Const.DISCRETE, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = BaseMeasureService.getAreaByMask(Const.DISCRETE, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = BaseMeasureService.getAreaByMask(Const.DISCRETE, dataByMaxParcelable.getRightHandDataSensor());
                } else if (algorithm.equals(BluetoothDimensionAlgorithm.ADVANCED)) {
                    initRadarChart(Const.DISCRETE_160, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = BaseMeasureService.getAreaByMask(Const.DISCRETE_160, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = BaseMeasureService.getAreaByMask(Const.DISCRETE_160, dataByMaxParcelable.getRightHandDataSensor());
                } else if (algorithm.equals(BluetoothDimensionAlgorithm.SIMPLE)) {
                    initRadarChart(Const.DISCRETE_30, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = BaseMeasureService.getAreaByMask(Const.DISCRETE_30, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = BaseMeasureService.getAreaByMask(Const.DISCRETE_30, dataByMaxParcelable.getRightHandDataSensor());
                }

                difference = BaseMeasureService.calculateDifferenceLeftRight(areaLeft, areaRight);
                areaDifference = new AreaDifferenceDiscrete(difference, dataByMaxParcelable.getGender(), context);
                break;
            case Const.PAGE_ENERGY_ONE_SENSOR:
                if (algorithm.equals(BluetoothDimensionAlgorithm._200)) {
                    initRadarChart(Const.ENERGY, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = BaseMeasureService.getAreaByMask(Const.ENERGY, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = BaseMeasureService.getAreaByMask(Const.ENERGY, dataByMaxParcelable.getRightHandDataSensor());
                } else if (algorithm.equals(BluetoothDimensionAlgorithm.ADVANCED)) {
                    initRadarChart(Const.ENERGY_160, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = BaseMeasureService.getAreaByMask(Const.ENERGY_160, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = BaseMeasureService.getAreaByMask(Const.ENERGY_160, dataByMaxParcelable.getRightHandDataSensor());
                } else if (algorithm.equals(BluetoothDimensionAlgorithm.SIMPLE)) {
                    initRadarChart(Const.ENERGY_30, Color.RED, Color.BLUE, dataByMaxParcelable);
                    areaLeft = BaseMeasureService.getAreaByMask(Const.ENERGY_30, dataByMaxParcelable.getLeftHandDataSensor());
                    areaRight = BaseMeasureService.getAreaByMask(Const.ENERGY_30, dataByMaxParcelable.getRightHandDataSensor());
                }
                difference = BaseMeasureService.calculateDifferenceLeftRight(areaLeft, areaRight);
                areaDifference = new AreaDifferenceEnergy(difference, dataByMaxParcelable.getGender(), context);
                break;
        }

        if (areaLeft != 0 && areaRight != 0) {
            getViewState().setAreaDifference(areaDifference);
        }

        int timeRegistrationMaxSignal = algorithm.getMaxSignalTime();
        val algorithmId = dataByMaxParcelable.getAlgorithmId();

        if (algorithmId != null) {
            val a = BluetoothDimensionAlgorithm.getByAlgorithmId(algorithmId);
            if (a != null) {
                timeRegistrationMaxSignal =  a.getMaxSignalTime();
            }
        } else {
            if (timeRegistrationMaxSignal == 160) {
                timeRegistrationMaxSignal = 60;
            }
            if (timeRegistrationMaxSignal == 200) {
                timeRegistrationMaxSignal = 80;
            }
        }

        if (dataByMaxParcelable.getLeftHandDataSensor() != null && dataByMaxParcelable.getLeftHandDataSensor().size() > timeRegistrationMaxSignal) {
            val maxLeft = dataByMaxParcelable.getLeftHandDataSensor().get(timeRegistrationMaxSignal);
            getViewState().setMaxLeft(maxLeft);
        }

        if (dataByMaxParcelable.getRightHandDataSensor() != null && dataByMaxParcelable.getRightHandDataSensor().size() > timeRegistrationMaxSignal) {
            val maxRight = dataByMaxParcelable.getRightHandDataSensor().get(timeRegistrationMaxSignal);
            getViewState().setMaxRight(maxRight);

        }

        getViewState().setLeftArea(areaLeft);
        getViewState().setRightArea(areaRight);

    }

    public void btnResultClickListener(DataByMaxParcelable dataByMaxParcelable) {
        dataByMaxParcelable.setMeasureType(Const.ONE_SENSOR_MEASURE);
        App.INSTANCE.getRouter().navigateTo(Screens.RESULT_FRAGMENT, dataByMaxParcelable);
    }
}