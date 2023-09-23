package com.murik.lite.presentation.presenter.resultRadarChart;


import android.graphics.Color;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.RadarEntry;
import com.murik.lite.Const;
import com.murik.lite.dto.SensorDataFullParcelable;
import com.murik.lite.presentation.view.resultRadarChart.ResultRadarChartView;
import com.murik.lite.service.Impl.MeasureServiceImpl;
import com.murik.lite.service.MeasureService;
import com.murik.lite.utils.ListUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.val;

@InjectViewState
public class ResultRadarChartPresenter extends MvpPresenter<ResultRadarChartView> {


    public final static String TAG = "resultRadarPresenter";
    private SensorDataFullParcelable sensorDataFullParcelable;
    private MeasureService measureService;

    private List<Float> area = new ArrayList<>();
    private List<Float> delta = new ArrayList<>();
    private List<Float> delta180 = new ArrayList<>();


    @Override
    public void attachView(ResultRadarChartView view) {
        super.attachView(view);
    }

    public void setData(SensorDataFullParcelable sensorDataFullParcelable) {
        this.sensorDataFullParcelable = sensorDataFullParcelable;
        measureService = new MeasureServiceImpl(sensorDataFullParcelable);

    }

    public SensorDataFullParcelable getSensorDataFullParcelable() {
        return sensorDataFullParcelable;
    }

    public void createRadarChart(int mPage) {
        switch (mPage) {
            case Const.PAGE_TOTAL:
                initRadarChart(Const.TOTAL, Color.RED, Color.BLUE);
                area = measureService.getAreaTotal();
                delta.add(100f);
                delta.add(100f);
                break;
            case Const.PAGE_HEALTH:
                initRadarChart(Const.HEALTH, Color.RED, Color.BLUE);
                area = measureService.getAreaHealth();
                delta = measureService.getDeltaHealth();
                break;
            case Const.PAGE_ENERGY:
                initRadarChart(Const.ENERGY, Color.RED, Color.BLUE);
                area = measureService.getAreaEnergy();
                delta = measureService.getDeltaEnergy();
                break;
            case Const.PAGE_BAD:
                initRadarChart(Const.BAD, Color.RED, Color.BLUE);
                area = measureService.getAreaBad();
                delta = measureService.getDeltaBad();
                delta180 = measureService.getDeltaBad180();
                getViewState().setTvDeltaLeft180(BigDecimal.valueOf(delta180.get(0)).setScale(1, RoundingMode.DOWN).floatValue() + " %");
                getViewState().setTvDeltaRight180(BigDecimal.valueOf(delta180.get(1)).setScale(1, RoundingMode.DOWN).floatValue() + " %");
                break;
            case Const.PAGE_ENDOKRIN:
                initRadarChart(Const.ENDOKRIN, Color.RED, Color.BLUE);
                area = measureService.getAreaEndokrin();
                delta = measureService.getDeltaEndokrin();
                break;
        }
        getViewState().setTvRadarAreaLeft(Float.toString(area.get(0)));
        getViewState().setTvRadarAreaRight(Float.toString(area.get(1)));
        if (measureService.getDifferenceArea() != null) {
            getViewState().setTvDifference(Float.toString(measureService.getDifferenceArea()));
        }
        getViewState().setTvDeltaLeft(BigDecimal.valueOf(delta.get(0)).setScale(1, RoundingMode.DOWN).floatValue() + " %");
        getViewState().setTvDeltaRight(BigDecimal.valueOf(delta.get(1)).setScale(1, RoundingMode.DOWN).floatValue() + " %");
    }

    public void initRadarChart(int[] mask, int colorLeft, int colorRight) {
        ArrayList<Integer> leftHandData = new ArrayList<>();
        ArrayList<Integer> rightHandData = new ArrayList<>();

        if (Arrays.equals(mask, Const.ENERGY)) {
            for (int key : mask) {

                for (String sid : Const.SENSORS_SID_ENERGY) {
                    val sensorDataLeft = sensorDataFullParcelable.getDataSensorMapLeftHand().get(sid);
                    if (ListUtils.listSizeCondition(sensorDataLeft, key)) {
                        leftHandData.add(sensorDataLeft.get(key));
                    }

                    val sensorDataRight = sensorDataFullParcelable.getDataSensorMapRightHand().get(sid);
                    if (ListUtils.listSizeCondition(sensorDataRight, key)) {
                        rightHandData.add(sensorDataRight.get(key));
                    }
                }
            }
        } else if (Arrays.equals(mask, Const.ENDOKRIN)) {
            for (int key : mask) {

                for (String sid : Const.SENSORS_SID_ENDOKRIN) {
                    val sensorDataLeft = sensorDataFullParcelable.getDataSensorMapLeftHand().get(sid);
                    if (ListUtils.listSizeCondition(sensorDataLeft, key)) {
                        leftHandData.add(sensorDataLeft.get(key));
                    }

                    val sensorDataRight = sensorDataFullParcelable.getDataSensorMapRightHand().get(sid);
                    if (ListUtils.listSizeCondition(sensorDataRight, key)) {
                        rightHandData.add(sensorDataRight.get(key));
                    }
                }
            }
        } else {
            for (int key : mask) {

                for (String sid : Const.SENSORS_SID) {
                    val sensorDataLeft = sensorDataFullParcelable.getDataSensorMapLeftHand().get(sid);
                    if (ListUtils.listSizeCondition(sensorDataLeft, key)) {
                        leftHandData.add(sensorDataLeft.get(key));
                    }

                    val sensorDataRight = sensorDataFullParcelable.getDataSensorMapRightHand().get(sid);
                    if (ListUtils.listSizeCondition(sensorDataRight, key)) {
                        rightHandData.add(sensorDataRight.get(key));
                    }
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
                , getSensorDataFullParcelable().getDescriptions()
                , colorLeft
                , colorRight
        );
    }

    public void showAreaDelta(List<Float> area, List<Float> delta) {

        getViewState().setTvRadarAreaLeft(Float.toString(area.get(0)));
        getViewState().setTvRadarAreaRight(Float.toString(area.get(1)));

        getViewState().setTvDeltaLeft(BigDecimal.valueOf(delta.get(0)).setScale(1, RoundingMode.DOWN).floatValue() + " %");
        getViewState().setTvDeltaRight(BigDecimal.valueOf(delta.get(1)).setScale(1, RoundingMode.DOWN).floatValue() + " %");
    }

}