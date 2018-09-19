package com.murik.enose.model;

import com.murik.enose.model.dto.DataSensorRealm;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.Date;

public class RealmController{

  private Realm realm = Realm.getDefaultInstance();

  public RealmController() {

  }

  public void addInfo(String description, InputData dataSens) {

    Realm.getDefaultInstance().executeTransaction( realm -> {
        DataSensorRealm data = realm.createObject(DataSensorRealm.class, getNextKey());
        Date date = new Date();
        long time = date.getTime();
        data.setTime(time);
        data.setDescriptions(description);
        data.setPractice(dataSens.isPractice());
      /*DataSensor dataSensor = new DataSensor();
      dataSensor.setDataSens1(dataSens.getDatasens().get(0));
        /*data.setDataSens1(dataSens.getDatasens().get(0));
        data.setDataSens2(dataSens.getDatasens().get(1));
        data.setDataSens3(dataSens.getDatasens().get(2));
        data.setDataSens4(dataSens.getDatasens().get(3));
        data.setDataSens5(dataSens.getDatasens().get(4));
        data.setDataSens6(dataSens.getDatasens().get(5));
        data.setDataSens7(dataSens.getDatasens().get(6));
        data.setDataSens8(dataSens.getDatasens().get(7));*/
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
