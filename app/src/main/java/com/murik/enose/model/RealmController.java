package com.murik.enose.model;

import com.murik.enose.model.dto.DataSensor;
import com.murik.enose.model.dto.DataSensorRealm;
import com.murik.enose.model.dto.InputDataParcelable;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.Date;

public class RealmController{

  private Realm realm = Realm.getDefaultInstance();

  public RealmController() {

  }

  public void addInfo(InputDataParcelable dataSens) {

    Realm.getDefaultInstance().executeTransaction( realm -> {
      DataSensorRealm data = realm.createObject(DataSensorRealm.class, getNextKey());
      Date date = new Date();
      long time = date.getTime();
      data.setTime(time);
      data.setDescriptions(dataSens.getDescriptions());
      data.setPractice(dataSens.isPractice());
      data.setGender(dataSens.getGender());

      DataSensor rightHandDataSensor = realm.createObject(DataSensor.class);
      DataSensor leftHandDataSensor = realm.createObject(DataSensor.class);

      if (!dataSens.getRightHandDataSensor().isEmpty()) {
        rightHandDataSensor.setDataSens1(dataSens.getRightHandDataSensor().get(0));
        rightHandDataSensor.setDataSens2(dataSens.getRightHandDataSensor().get(1));
        rightHandDataSensor.setDataSens3(dataSens.getRightHandDataSensor().get(2));
        rightHandDataSensor.setDataSens4(dataSens.getRightHandDataSensor().get(3));
        rightHandDataSensor.setDataSens5(dataSens.getRightHandDataSensor().get(4));
        rightHandDataSensor.setDataSens6(dataSens.getRightHandDataSensor().get(5));
        rightHandDataSensor.setDataSens7(dataSens.getRightHandDataSensor().get(6));
        rightHandDataSensor.setDataSens8(dataSens.getRightHandDataSensor().get(7));
      }

      if (!dataSens.getLeftHandDataSensor().isEmpty()) {
        leftHandDataSensor.setDataSens1(dataSens.getLeftHandDataSensor().get(0));
        leftHandDataSensor.setDataSens2(dataSens.getLeftHandDataSensor().get(1));
        leftHandDataSensor.setDataSens3(dataSens.getLeftHandDataSensor().get(2));
        leftHandDataSensor.setDataSens4(dataSens.getLeftHandDataSensor().get(3));
        leftHandDataSensor.setDataSens5(dataSens.getLeftHandDataSensor().get(4));
        leftHandDataSensor.setDataSens6(dataSens.getLeftHandDataSensor().get(5));
        leftHandDataSensor.setDataSens7(dataSens.getLeftHandDataSensor().get(6));
        leftHandDataSensor.setDataSens8(dataSens.getLeftHandDataSensor().get(7));
      }
      data.setLeftHandData(leftHandDataSensor);
      data.setRightHandData(rightHandDataSensor);
    });
  }

  public RealmResults<DataSensorRealm> getInfo(){
    return realm.where(DataSensorRealm.class).findAll();
  }

  private int getNextKey() {
    if(realm.where(DataSensorRealm.class).max("id") == null){
      return 0;
    }
    return realm.where(DataSensorRealm.class).max("id").intValue() + 1;
  }
}
