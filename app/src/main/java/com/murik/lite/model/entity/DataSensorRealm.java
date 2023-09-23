package com.murik.lite.model.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DataSensorRealm extends RealmObject {

    @PrimaryKey
    private long id;

    private String descriptions;
    private boolean isPractice;
    private Integer algorithmId;
    private boolean isFullData;
    private long time;

    private DataSensor leftHandData;
    private DataSensor rightHandData;
    private int gender;
    private int hand;
}
