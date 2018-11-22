package com.murik.enose.model;

import com.murik.enose.Const;
import com.murik.enose.model.dto.DataSensor;
import com.murik.enose.model.dto.DataSensorRealm;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.dto.RealmInt;
import com.murik.enose.model.dto.SensorDataFullParcelable;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class RealmController {

  private Realm realm = Realm.getDefaultInstance();

  public RealmController() {

  }


  public void addInfoFull(SensorDataFullParcelable dataSens) {

    realm.executeTransaction(realm -> {
      DataSensorRealm data = realm.createObject(DataSensorRealm.class, getNextKey());
      Date date = new Date();
      long time = date.getTime();
      data.setTime(time);
      data.setDescriptions(dataSens.getDescriptions());
      data.setPractice(dataSens.isPractice());
      data.setGender(dataSens.getGender());
      data.setFullData(true);




      DataSensor rightHandDataSensor = realm.createObject(DataSensor.class);
      DataSensor leftHandDataSensor = realm.createObject(DataSensor.class);




      if(!dataSens.getDataSensorMapRightHand().isEmpty()){
        if (!dataSens.getDataSensorMapRightHand().get(Const.SENSOR_1).isEmpty()) {
          RealmList<RealmInt> realmInts = new RealmList<>();
          for (int i = 0; i < dataSens.getDataSensorMapRightHand().get(Const.SENSOR_1).size();
              i++) {
            RealmInt realmInt = realm.createObject(RealmInt.class);
            realmInt.setValue(dataSens.getDataSensorMapRightHand().get(Const.SENSOR_1).get(i));
            realmInts.add(realmInt);
          }
          rightHandDataSensor.setDataSens1(realmInts);
        }

          if (!dataSens.getDataSensorMapRightHand().get(Const.SENSOR_2).isEmpty()) {
            RealmList<RealmInt> realmInts = new RealmList<>();
            for (int i = 0; i < dataSens.getDataSensorMapRightHand().get(Const.SENSOR_2).size(); i++) {
              RealmInt realmInt = realm.createObject(RealmInt.class);
              realmInt.setValue(dataSens.getDataSensorMapRightHand().get(Const.SENSOR_2).get(i));
              realmInts.add(realmInt);
            }
            rightHandDataSensor.setDataSens2(realmInts);
          }
          if (!dataSens.getDataSensorMapRightHand().get(Const.SENSOR_3).isEmpty()) {
            RealmList<RealmInt> realmInts = new RealmList<>();
            for (int i = 0; i < dataSens.getDataSensorMapRightHand().get(Const.SENSOR_3).size(); i++) {
              RealmInt realmInt = realm.createObject(RealmInt.class);
              realmInt.setValue(dataSens.getDataSensorMapRightHand().get(Const.SENSOR_3).get(i));
              realmInts.add(realmInt);
            }
            rightHandDataSensor.setDataSens3(realmInts);
          }
          if (!dataSens.getDataSensorMapRightHand().get(Const.SENSOR_4).isEmpty()) {
            RealmList<RealmInt> realmInts = new RealmList<>();
            for (int i = 0; i < dataSens.getDataSensorMapRightHand().get(Const.SENSOR_4).size(); i++) {
              RealmInt realmInt = realm.createObject(RealmInt.class);
              realmInt.setValue(dataSens.getDataSensorMapRightHand().get(Const.SENSOR_4).get(i));
              realmInts.add(realmInt);
            }
            rightHandDataSensor.setDataSens4(realmInts);
          }
          if (!dataSens.getDataSensorMapRightHand().get(Const.SENSOR_5).isEmpty()) {
            RealmList<RealmInt> realmInts = new RealmList<>();
            for (int i = 0; i < dataSens.getDataSensorMapRightHand().get(Const.SENSOR_5).size(); i++) {
              RealmInt realmInt = realm.createObject(RealmInt.class);
              realmInt.setValue(dataSens.getDataSensorMapRightHand().get(Const.SENSOR_5).get(i));
              realmInts.add(realmInt);
            }
            rightHandDataSensor.setDataSens5(realmInts);
          }
          if (!dataSens.getDataSensorMapRightHand().get(Const.SENSOR_6).isEmpty()) {
            RealmList<RealmInt> realmInts = new RealmList<>();
            for (int i = 0; i < dataSens.getDataSensorMapRightHand().get(Const.SENSOR_6).size(); i++) {
              RealmInt realmInt = realm.createObject(RealmInt.class);
              realmInt.setValue(dataSens.getDataSensorMapRightHand().get(Const.SENSOR_6).get(i));
              realmInts.add(realmInt);
            }
            rightHandDataSensor.setDataSens6(realmInts);

          }
          if (!dataSens.getDataSensorMapRightHand().get(Const.SENSOR_7).isEmpty()) {
            RealmList<RealmInt> realmInts = new RealmList<>();
            for (int i = 0; i < dataSens.getDataSensorMapRightHand().get(Const.SENSOR_7).size(); i++) {
              RealmInt realmInt = realm.createObject(RealmInt.class);
              realmInt.setValue(dataSens.getDataSensorMapRightHand().get(Const.SENSOR_7).get(i));
              realmInts.add(realmInt);
            }
            rightHandDataSensor.setDataSens7(realmInts);
          }
          if (!dataSens.getDataSensorMapRightHand().get(Const.SENSOR_8).isEmpty()) {
            RealmList<RealmInt> realmInts = new RealmList<>();
            for (int i = 0; i < dataSens.getDataSensorMapRightHand().get(Const.SENSOR_8).size(); i++) {
              RealmInt realmInt = realm.createObject(RealmInt.class);
              realmInt.setValue(dataSens.getDataSensorMapRightHand().get(Const.SENSOR_8).get(i));
              realmInts.add(realmInt);
            }
            rightHandDataSensor.setDataSens8(realmInts);
          }
      }

      if(!dataSens.getDataSensorMapLeftHand().isEmpty()){
        if (!dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_1).isEmpty()) {
          RealmList<RealmInt> realmInts = new RealmList<>();
          for (int i = 0; i < dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_1).size(); i++) {
            RealmInt realmInt = realm.createObject(RealmInt.class);
            realmInt.setValue(dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_1).get(i));
            realmInts.add(realmInt);
          }
          leftHandDataSensor.setDataSens1(realmInts);
        }

        if (!dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_2).isEmpty()) {
          RealmList<RealmInt> realmInts = new RealmList<>();
          for (int i = 0; i < dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_2).size(); i++) {
            RealmInt realmInt = realm.createObject(RealmInt.class);
            realmInt.setValue(dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_2).get(i));
            realmInts.add(realmInt);
          }
          leftHandDataSensor.setDataSens2(realmInts);
        }
        if (!dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_3).isEmpty()) {
          RealmList<RealmInt> realmInts = new RealmList<>();
          for (int i = 0; i < dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_3).size(); i++) {
            RealmInt realmInt = realm.createObject(RealmInt.class);
            realmInt.setValue(dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_3).get(i));
            realmInts.add(realmInt);
          }
          leftHandDataSensor.setDataSens3(realmInts);
        }
        if (!dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_4).isEmpty()) {
          RealmList<RealmInt> realmInts = new RealmList<>();
          for (int i = 0; i < dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_4).size(); i++) {
            RealmInt realmInt = realm.createObject(RealmInt.class);
            realmInt.setValue(dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_4).get(i));
            realmInts.add(realmInt);
          }
          leftHandDataSensor.setDataSens4(realmInts);
        }
        if (!dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_5).isEmpty()) {
          RealmList<RealmInt> realmInts = new RealmList<>();
          for (int i = 0; i < dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_5).size(); i++) {
            RealmInt realmInt = realm.createObject(RealmInt.class);
            realmInt.setValue(dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_5).get(i));
            realmInts.add(realmInt);
          }
          leftHandDataSensor.setDataSens5(realmInts);
        }
        if (!dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_6).isEmpty()) {
          RealmList<RealmInt> realmInts = new RealmList<>();
          for (int i = 0; i < dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_6).size(); i++) {
            RealmInt realmInt = realm.createObject(RealmInt.class);
            realmInt.setValue(dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_6).get(i));
            realmInts.add(realmInt);
          }
          leftHandDataSensor.setDataSens6(realmInts);
        }
        if (!dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_7).isEmpty()) {
          RealmList<RealmInt> realmInts = new RealmList<>();
          for (int i = 0; i < dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_7).size(); i++) {
            RealmInt realmInt = realm.createObject(RealmInt.class);
            realmInt.setValue(dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_7).get(i));
            realmInts.add(realmInt);
          }
          leftHandDataSensor.setDataSens7(realmInts);
        }
        if (!dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_8).isEmpty()) {
          RealmList<RealmInt> realmInts = new RealmList<>();
          for (int i = 0; i < dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_8).size(); i++) {
            RealmInt realmInt = realm.createObject(RealmInt.class);
            realmInt.setValue(dataSens.getDataSensorMapLeftHand().get(Const.SENSOR_8).get(i));
            realmInts.add(realmInt);
          }
          leftHandDataSensor.setDataSens8(realmInts);
        }
      }

      data.setLeftHandData(leftHandDataSensor);
      data.setRightHandData(rightHandDataSensor);
      realm.close();
    });
  }

  public void addInfoMax(DataByMaxParcelable dataSens) {
    realm.executeTransaction(realm -> {
      DataSensorRealm data = realm.createObject(DataSensorRealm.class, getNextKey());
      Date date = new Date();
      long time = date.getTime();
      data.setTime(time);
      data.setDescriptions(dataSens.getDescriptions());
      data.setPractice(dataSens.isPractice());
      data.setGender(dataSens.getGender());
      data.setFullData(false);

      DataSensor rightHandDataSensor = realm.createObject(DataSensor.class);
      DataSensor leftHandDataSensor = realm.createObject(DataSensor.class);

      if (!dataSens.getRightHandDataSensor().isEmpty()) {

        RealmInt sensorInt1 = realm.createObject(RealmInt.class);
        sensorInt1.setValue(dataSens.getRightHandDataSensor().get(0));
        RealmList<RealmInt> sens1 = new RealmList<>();
        sens1.add(sensorInt1);
        rightHandDataSensor.setDataSens1(sens1);

        RealmInt sensorInt2 = realm.createObject(RealmInt.class);
        sensorInt2.setValue(dataSens.getRightHandDataSensor().get(1));
        RealmList<RealmInt> sens2 = new RealmList<>();
        sens2.add(sensorInt2);
        rightHandDataSensor.setDataSens2(sens2);

        RealmInt sensorInt3 = realm.createObject(RealmInt.class);
        sensorInt3.setValue(dataSens.getRightHandDataSensor().get(2));
        RealmList<RealmInt> sens3 = new RealmList<>();
        sens3.add(sensorInt3);
        rightHandDataSensor.setDataSens3(sens3);

        RealmInt sensorInt4 = realm.createObject(RealmInt.class);
        sensorInt4.setValue(dataSens.getRightHandDataSensor().get(3));
        RealmList<RealmInt> sens4 = new RealmList<>();
        sens4.add(sensorInt4);
        rightHandDataSensor.setDataSens4(sens4);

        RealmInt sensorInt5 = realm.createObject(RealmInt.class);
        sensorInt5.setValue(dataSens.getRightHandDataSensor().get(4));
        RealmList<RealmInt> sens5 = new RealmList<>();
        sens5.add(sensorInt5);
        rightHandDataSensor.setDataSens5(sens5);

        RealmInt sensorInt6 = realm.createObject(RealmInt.class);
        sensorInt6.setValue(dataSens.getRightHandDataSensor().get(5));
        RealmList<RealmInt> sens6 = new RealmList<>();
        sens6.add(sensorInt6);
        rightHandDataSensor.setDataSens6(sens6);

        RealmInt sensorInt7 = realm.createObject(RealmInt.class);
        sensorInt7.setValue(dataSens.getRightHandDataSensor().get(6));
        RealmList<RealmInt> sens7 = new RealmList<>();
        sens7.add(sensorInt7);
        rightHandDataSensor.setDataSens7(sens7);

        RealmInt sensorInt8 = realm.createObject(RealmInt.class);
        sensorInt8.setValue(dataSens.getRightHandDataSensor().get(7));
        RealmList<RealmInt> sens8 = new RealmList<>();
        sens8.add(sensorInt8);
        rightHandDataSensor.setDataSens8(sens8);
      }

      if (!dataSens.getLeftHandDataSensor().isEmpty()) {

        RealmInt sensorInt1 = realm.createObject(RealmInt.class);
        sensorInt1.setValue(dataSens.getLeftHandDataSensor().get(0));
        RealmList<RealmInt> sens1 = new RealmList<>();
        sens1.add(sensorInt1);
        leftHandDataSensor.setDataSens1(sens1);

        RealmInt sensorInt2 = realm.createObject(RealmInt.class);
        sensorInt2.setValue(dataSens.getLeftHandDataSensor().get(1));
        RealmList<RealmInt> sens2 = new RealmList<>();
        sens2.add(sensorInt2);
        leftHandDataSensor.setDataSens2(sens2);

        RealmInt sensorInt3 = realm.createObject(RealmInt.class);
        sensorInt3.setValue(dataSens.getLeftHandDataSensor().get(2));
        RealmList<RealmInt> sens3 = new RealmList<>();
        sens3.add(sensorInt3);
        leftHandDataSensor.setDataSens3(sens3);

        RealmInt sensorInt4 = realm.createObject(RealmInt.class);
        sensorInt4.setValue(dataSens.getLeftHandDataSensor().get(3));
        RealmList<RealmInt> sens4 = new RealmList<>();
        sens4.add(sensorInt4);
        leftHandDataSensor.setDataSens4(sens4);

        RealmInt sensorInt5 = realm.createObject(RealmInt.class);
        sensorInt5.setValue(dataSens.getLeftHandDataSensor().get(4));
        RealmList<RealmInt> sens5 = new RealmList<>();
        sens5.add(sensorInt5);
        leftHandDataSensor.setDataSens5(sens5);

        RealmInt sensorInt6 = realm.createObject(RealmInt.class);
        sensorInt6.setValue(dataSens.getLeftHandDataSensor().get(5));
        RealmList<RealmInt> sens6 = new RealmList<>();
        sens6.add(sensorInt6);
        leftHandDataSensor.setDataSens6(sens6);

        RealmInt sensorInt7 = realm.createObject(RealmInt.class);
        sensorInt7.setValue(dataSens.getLeftHandDataSensor().get(6));
        RealmList<RealmInt> sens7 = new RealmList<>();
        sens7.add(sensorInt7);
        leftHandDataSensor.setDataSens7(sens7);

        RealmInt sensorInt8 = realm.createObject(RealmInt.class);
        sensorInt8.setValue(dataSens.getLeftHandDataSensor().get(7));
        RealmList<RealmInt> sens8 = new RealmList<>();
        sens8.add(sensorInt8);
        leftHandDataSensor.setDataSens8(sens8);
      }

      data.setLeftHandData(leftHandDataSensor);
      data.setRightHandData(rightHandDataSensor);
      realm.close();
    });
  }

  public RealmList<Integer> createRealmList(ArrayList<Integer> sens) {

    RealmList<Integer> sensorRealmList = new RealmList<>();
    Iterator iter = sens.iterator();
    while (iter.hasNext()) {
      sensorRealmList.add((Integer) iter.next());
    }
    return sensorRealmList;

  }


  public DataSensorRealm getInfoById(int id){
   return realm.where(DataSensorRealm.class).equalTo("id", id).findFirst();
  }

  public RealmResults<DataSensorRealm> getInfo() {
    return realm.where(DataSensorRealm.class).findAllAsync();

  }

  private int getNextKey() {
    if (realm.where(DataSensorRealm.class).max("id") == null) {
      return 0;
    } else {
      return realm.where(DataSensorRealm.class).max("id").intValue() + 1;
    }
  }
}
