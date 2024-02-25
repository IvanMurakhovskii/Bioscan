package com.murik.lite.service;

import com.murik.lite.App;
import com.murik.lite.enums.BluetoothDimensionAlgorithm;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

import java.util.ArrayList;
import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import lombok.val;

import static com.murik.lite.utils.ListUtils.isDataSensorAllZero;

public abstract class SummaryService {

    private final int dimensionId;

    SummaryService(int dimensionId) {
        this.dimensionId = dimensionId;

        val data = App.INSTANCE.getRealmController().findById(dimensionId);

        val algorithm = data.getAlgorithmId() == null ? null : BluetoothDimensionAlgorithm.getByAlgorithmId(data.getAlgorithmId());

        ArrayList<Integer> rightHandData = new ArrayList<>();
        ArrayList<Integer> leftHandData = new ArrayList<>();

        if (data.getLeftHandData() != null) {
            if (data.getLeftHandData().getDataSens1() != null) {
                if (!data.getLeftHandData().getDataSens1().isEmpty()) {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    for (int i = 0; i < data.getLeftHandData().getDataSens1().size(); i++) {
                        arrayList.add(Objects.requireNonNull(data.getLeftHandData().getDataSens1().get(i)).getValue());
                    }
                    if (!isDataSensorAllZero(arrayList)) {
                        leftHandData = arrayList;
                    }
                }
            }
        }



    }

    public abstract BaseResult getSummary();
}


@Getter
@Builder
class DimensionDto {
    private String descriptions;
    private ArrayList<Integer> rightHandData;
    private ArrayList<Integer> leftHandData;
    private int gender;
    private boolean isPractice;
    private BluetoothDimensionAlgorithm algorithm;
}