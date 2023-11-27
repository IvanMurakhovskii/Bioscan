package com.murik.lite.presentation.presenter.dimension;


import android.hardware.Sensor;
import android.hardware.SensorEvent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.App;
import com.murik.lite.Const;
import com.murik.lite.model.RealmController;
import com.murik.lite.dto.SensorDataFullParcelable;
import com.murik.lite.presentation.view.dimension.BluetoothDimensionView;
import com.murik.lite.utils.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@InjectViewState
public class BluetoothDimensionPresenter extends MvpPresenter<BluetoothDimensionView> {

    private Map<Integer, Integer> initial = new HashMap<>();
    private Map<String, ArrayList<Integer>> data = new HashMap<>();
    private ArrayList<Integer> arrDataSens1 = new ArrayList<>();


    private String description;
    private boolean isPractice;
    private int gender = Const.GENDER_MALE;
    private Integer algorithmId;
    private boolean isLeftHand = false;

    private List<Integer> sens1LeftHand = new ArrayList<>();
    private List<Integer> sens1RightHand = new ArrayList<>();
    private List<Integer> sens2 = new ArrayList<>();
    private List<Integer> sens3 = new ArrayList<>();
    private List<Integer> sens4 = new ArrayList<>();
    private List<Integer> sens5 = new ArrayList<>();
    private List<Integer> sens6 = new ArrayList<>();
    private List<Integer> sens7 = new ArrayList<>();
    private List<Integer> sens8 = new ArrayList<>();

    Map<String, ArrayList<Integer>> dataLeftHand = new HashMap<>();
    Map<String, ArrayList<Integer>> dataRightHand = new HashMap<>();


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }


    public void setDimensionParameters(Dimension dimension) {
        this.description = dimension.getDescription();
        this.gender = dimension.getGender();
        this.isPractice = dimension.isPractice();
        this.algorithmId = dimension.getAlgorithmId();
    }

    public int addSensorData(boolean isLeftHand, int sensorNumber, int value) {
        String sensorLabel = "SID000" + sensorNumber;
        if(isLeftHand){
            return addSensorValueToMap(dataLeftHand, sensorLabel, value);
        } else {
            return addSensorValueToMap(dataRightHand, sensorLabel, value);
        }
    }

    private int addSensorValueToMap(Map<String, ArrayList<Integer>> data, String sensorLabel, Integer value) {
        List<Integer> sensorData = data.get(sensorLabel);
        if (sensorData == null) {
            ArrayList<Integer> values = new ArrayList<>();
            values.add(value);
            data.put(sensorLabel, values);
        } else {
            sensorData.add(value);
        }

        return sensorData != null ? sensorData.size() : 0;
    }

    public void save() {
        SensorDataFullParcelable sensorDataFullParcelable = new SensorDataFullParcelable();
        sensorDataFullParcelable.setDescriptions(description);
        sensorDataFullParcelable.setFullData(true);
        sensorDataFullParcelable.setGender(gender);
        sensorDataFullParcelable.setGender(gender);
        sensorDataFullParcelable.setPractice(isPractice);
        sensorDataFullParcelable.setAlgorithmId(algorithmId);

//        ListUtils.inverseListValueIfMiddleValueBelowZero(sens1LeftHand);
//        ListUtils.inverseListValueIfMiddleValueBelowZero(sens1RightHand);

        for (List<Integer> sensData : dataLeftHand.values()) {
            ListUtils.inverseListValueIfMiddleValueBelowZero(sensData);
        }

        for (List<Integer> sensData : dataRightHand.values()) {
            ListUtils.inverseListValueIfMiddleValueBelowZero(sensData);
        }

//        dataLeftHand.put(Const.SENSOR_1, new ArrayList<>(sens1LeftHand));
//        dataRightHand.put(Const.SENSOR_1, new ArrayList<>(sens1RightHand));

        sensorDataFullParcelable.setDataSensorMapLeftHand(dataLeftHand);
        sensorDataFullParcelable.setDataSensorMapRightHand(dataRightHand);

        App.INSTANCE.getRealmController().addInfoFull(sensorDataFullParcelable);
    }

    public void addSens1DataLeftHand(int value) {
        sens1LeftHand.add(value);
    }

    public List<Integer> getSens1DataLeftHand() {
        return dataLeftHand.get(Const.SENSOR_1);
    }

    public void addSens1DataRightHand(int value) {
        sens1RightHand.add(value);
    }

    public List<Integer> getSens1DataRightHand() {
        return dataRightHand.get(Const.SENSOR_1);
    }

    public int getLastDataSens2() {
        if (sens2.size() > 1) {
            return sens2.get(sens2.size() - 1);
        }
        return 0;
    }

    public int getLastDataSens3() {
        if (sens3.size() > 1) {
            return sens3.get(sens3.size() - 1);
        }
        return 0;
    }

    public int getLastDataSens4() {
        if (sens4.size() > 1) {
            return sens4.get(sens4.size() - 1);
        }
        return 0;
    }

    public int getLastDataSens5() {
        if (sens5.size() > 1) {
            return sens5.get(sens5.size() - 1);
        }
        return 0;
    }

    public int getLastDataSens6() {
        if (sens6.size() > 1) {
            return sens6.get(sens6.size() - 1);
        }
        return 0;
    }

    public int getLastDataSens7() {
        if (sens7.size() > 1) {
            return sens7.get(sens7.size() - 1);
        }
        return 0;
    }

    public int getLastDataSens8() {
        if (sens8.size() > 1) {
            return sens8.get(sens8.size() - 1);
        }
        return 0;
    }
}
