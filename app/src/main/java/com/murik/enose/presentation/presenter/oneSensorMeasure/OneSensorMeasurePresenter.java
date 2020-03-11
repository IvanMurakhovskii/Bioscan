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

import java.util.ArrayList;

import lombok.val;

@InjectViewState
public class OneSensorMeasurePresenter extends MvpPresenter<OneSensorMeasureView> {

    public void initRadarChart(int[] mask, int colorLeft, int colorRight, DataByMaxParcelable dataByMaxParcelable) {

        ArrayList<Integer> leftHandData = new ArrayList<>();
        ArrayList<Integer> rightHandData = new ArrayList<>();

        for (int key : mask) {

            if (dataByMaxParcelable.getLeftHandDataSensor() != null) {
                if (dataByMaxParcelable.getLeftHandDataSensor().size() >= mask.length - 1) {
                    leftHandData.add(dataByMaxParcelable.getLeftHandDataSensor().get((key)));
                }
            }
            if (dataByMaxParcelable.getRightHandDataSensor() != null) {
                if (dataByMaxParcelable.getRightHandDataSensor().size() >= mask.length - 1) {
                    rightHandData.add(dataByMaxParcelable.getRightHandDataSensor().get((key)));
                }
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
                entryLeftHand
                , entryRightHand
                , dataByMaxParcelable.getDescriptions()
                , colorLeft
                , colorRight
        );
    }

    public void createRadarChart(int mPage, final DataByMaxParcelable dataByMaxParcelable, final BaseMeasureService measureService) {
        float areaLeft = 0;
        float areaRight = 0;
        float deltaLeft = 0;
        float deltaRight = 0;

        float areaBodyLeft = measureService.getAreaByMask(Const.BODY, dataByMaxParcelable.getLeftHandDataSensor());
        float areaBodyRight = measureService.getAreaByMask(Const.BODY, dataByMaxParcelable.getRightHandDataSensor());

        float areaLeftDanger = measureService.getAreaByMask(Const.DANGER, dataByMaxParcelable.getLeftHandDataSensor());
        float areaRightDanger = measureService.getAreaByMask(Const.DANGER, dataByMaxParcelable.getRightHandDataSensor());

        float areaLeftLungs = measureService.getAreaByMask(Const.LUNGS, dataByMaxParcelable.getLeftHandDataSensor());
        float areaRightLungs = measureService.getAreaByMask(Const.LUNGS, dataByMaxParcelable.getRightHandDataSensor());

        getViewState().setDeltaDangerOnLungsLeft(measureService.calculateDifference(areaLeftDanger, areaLeftLungs));
        getViewState().setDeltaDangerOnLungsRight(measureService.calculateDifference(areaRightDanger, areaRightLungs));


        switch (mPage) {
            case Const.PAGE_SHORT:
                initRadarChart(Const.SHORT, Color.RED, Color.BLUE, dataByMaxParcelable);
                areaLeft = measureService.getAreaByMask(Const.SHORT, dataByMaxParcelable.getLeftHandDataSensor());
                areaRight = measureService.getAreaByMask(Const.SHORT, dataByMaxParcelable.getRightHandDataSensor());

                getViewState().setLeftPs_3425(measureService.getPS3425(dataByMaxParcelable.getLeftHandDataSensor(), Const.SHORT));
                getViewState().setLeftPs_2435(measureService.getPS2435(dataByMaxParcelable.getLeftHandDataSensor(), Const.SHORT));

                getViewState().setRightPs_3425(measureService.getPS3425(dataByMaxParcelable.getRightHandDataSensor(), Const.SHORT));
                getViewState().setRightPs_2435(measureService.getPS2435(dataByMaxParcelable.getRightHandDataSensor(), Const.SHORT));

                break;
            case Const.PAGE_LONG:
                initRadarChart(Const.LONG, Color.RED, Color.BLUE, dataByMaxParcelable);
                areaLeft = measureService.getAreaByMask(Const.LONG, dataByMaxParcelable.getLeftHandDataSensor());
                areaRight = measureService.getAreaByMask(Const.LONG, dataByMaxParcelable.getRightHandDataSensor());

                getViewState().setLeftPs_3425(measureService.getPS3425(dataByMaxParcelable.getLeftHandDataSensor(), Const.LONG));
                getViewState().setLeftPs_2435(measureService.getPS2435(dataByMaxParcelable.getLeftHandDataSensor(), Const.LONG));

                getViewState().setRightPs_3425(measureService.getPS3425(dataByMaxParcelable.getRightHandDataSensor(), Const.LONG));
                getViewState().setRightPs_2435(measureService.getPS2435(dataByMaxParcelable.getRightHandDataSensor(), Const.LONG));

                break;
            case Const.PAGE_BODY:
                initRadarChart(Const.BODY, Color.RED, Color.BLUE, dataByMaxParcelable);
                areaLeft = areaBodyLeft;
                areaRight = areaBodyRight;
                break;
            case Const.PAGE_LUNGS:
                initRadarChart(Const.LUNGS, Color.RED, Color.BLUE, dataByMaxParcelable);
                areaLeft = areaLeftLungs;
                areaRight = areaRightLungs;

                deltaLeft = measureService.calculateDelta(areaBodyLeft, areaLeft);
                deltaRight = measureService.calculateDelta(areaBodyRight, areaRight);

                getViewState().setLeftDelta(deltaLeft);
                getViewState().setRightDelta(deltaRight);
                break;
            case Const.PAGE_DANGER:
                initRadarChart(Const.DANGER, Color.RED, Color.BLUE, dataByMaxParcelable);
                areaLeft = areaLeftDanger;
                areaRight = areaRightDanger;

                deltaLeft = measureService.calculateDelta(areaBodyLeft, areaLeft);
                deltaRight = measureService.calculateDelta(areaBodyRight, areaRight);

                getViewState().setLeftDelta(deltaLeft);
                getViewState().setRightDelta(deltaRight);
                break;
        }

        if(areaLeft != 0 && areaRight != 0) {
            getViewState().setAreaDifference(measureService.calculateDifference(areaLeft, areaRight));
        }

        getViewState().setLeftArea(areaLeft);
        getViewState().setRightArea(areaRight);
    }

    public void btnResultClickListener(int page, DataByMaxParcelable dataByMaxParcelable) {
        if (page == 0) {
            dataByMaxParcelable.setMeasureType(Const.FIRST_MEASURE);
        }
        if (page == 1) {
            dataByMaxParcelable.setMeasureType(Const.SECOND_MEASURE);
        }

        App.INSTANCE.getRouter().navigateTo(Screens.RESULT_FRAGMENT, dataByMaxParcelable);
    }
}