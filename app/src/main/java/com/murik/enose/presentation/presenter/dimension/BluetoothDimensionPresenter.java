package com.murik.enose.presentation.presenter.dimension;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.Screens;
import com.murik.enose.model.RealmController;
import com.murik.enose.dto.SensorDataFullParcelable;
import com.murik.enose.presentation.view.dimension.BluetoothDimensionView;
import com.murik.enose.utils.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@InjectViewState
public class BluetoothDimensionPresenter extends MvpPresenter<BluetoothDimensionView> {

    private Map<Integer, Integer> initial = new HashMap<>();
    private Map<String, ArrayList<Integer>> data = new HashMap<>();
    private ArrayList<Integer> arrDataSens1 = new ArrayList<>();


    private String description;
    private boolean isPractice;
    private int gender = Const.GENDER_MALE;
    private boolean isLeftHand = false;

    private ArrayList<Integer> sens1LeftHand = new ArrayList<>();
    private ArrayList<Integer> sens1RightHand = new ArrayList<>();
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


    public void setDimensionParametrs(final String description, final int gender, final boolean isPractice, final boolean isLeftHand) {
        this.description = description;
        this.gender = gender;
        this.isPractice = isPractice;
        this.isLeftHand = isLeftHand;
    }

    public void save() {
        SensorDataFullParcelable sensorDataFullParcelable = new SensorDataFullParcelable();
        sensorDataFullParcelable.setDescriptions(description);
        sensorDataFullParcelable.setFullData(true);
        sensorDataFullParcelable.setGender(gender);
        sensorDataFullParcelable.setPractice(isPractice);

        ListUtils.inverseListValueIfMiddleValueBelowZero(sens1LeftHand);
        ListUtils.inverseListValueIfMiddleValueBelowZero(sens1RightHand);

        dataLeftHand.put(Const.SENSOR_1, sens1LeftHand);
        dataRightHand.put(Const.SENSOR_1, sens1RightHand);

        sensorDataFullParcelable.setDataSensorMapLeftHand(dataLeftHand);
        sensorDataFullParcelable.setDataSensorMapRightHand(dataRightHand);

        RealmController controller = new RealmController();
        controller.addInfoFull(sensorDataFullParcelable);
    }

    public void addSens1DataLeftHand(int value) {
        sens1LeftHand.add(value);
    }

    public List<Integer> getSens1DataLeftHand() {
        return sens1LeftHand;
    }

    public void addSens1DataRightHand(int value) {
        sens1RightHand.add(value);
    }

    public List<Integer> getSens1DataRightHand() {
        return sens1RightHand;
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
