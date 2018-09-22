package com.murik.enose.model.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class DataSensorRealm extends RealmObject {

  @PrimaryKey
  private long id;
  private String descriptions;
  /*private int dataSens1;
  private int dataSens2;
  private int dataSens3;
  private int dataSens4;
  private int dataSens5;
  private int dataSens6;
  private int dataSens7;
  private int dataSens8;*/

  private boolean isPractice;
  private long time;

  private DataSensor leftHandData;
  private DataSensor rightHandData;
  private int gender;
  private int hand;

  public void setGender(int gender) {
    this.gender = gender;
  }

  public int getGender() {
    return gender;
  }

  public void setHand(int hand) {
    this.hand = hand;
  }

  public int getHand() {
    return hand;
  }

  public void setLeftHandData(DataSensor leftHandData) {
    this.leftHandData = leftHandData;
  }

  public void setRightHandData(DataSensor rightHandData) {
    this.rightHandData = rightHandData;
  }

  public DataSensor getLeftHandData() {
    return leftHandData;
  }

  public DataSensor getRightHandData() {
    return rightHandData;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public long getTime() {
    return time;
  }

  public void setPractice(boolean practice) {
    isPractice = practice;
  }

  public boolean isPractice() {
    return isPractice;
  }

  public void setDescriptions(String discriptions) {
    this.descriptions = discriptions;
  }

  public String getDescriptions() {
    return descriptions;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  /* void setDataSens1(int dataSens1) {
    this.dataSens1 = dataSens1;
  }

  public void setDataSens2(int dataSens2) {
    this.dataSens2 = dataSens2;
  }

  public void setDataSens3(int dataSens3) {
    this.dataSens3 = dataSens3;
  }

  public void setDataSens4(int dataSens4) {
    this.dataSens4 = dataSens4;
  }

  public void setDataSens5(int dataSens5) {
    this.dataSens5 = dataSens5;
  }

  public void setDataSens6(int dataSens6) {
    this.dataSens6 = dataSens6;
  }

  public void setDataSens7(int dataSens7) {
    this.dataSens7 = dataSens7;
  }

  public void setDataSens8(int dataSens8) {
    this.dataSens8 = dataSens8;
  }

  public int getDataSens1() {
    return dataSens1;
  }

  public int getDataSens2() {
    return dataSens2;
  }

  public int getDataSens3() {
    return dataSens3;
  }

  public int getDataSens4() {
    return dataSens4;
  }

  public int getDataSens5() {
    return dataSens5;
  }

  public int getDataSens6() {
    return dataSens6;
  }

  public int getDataSens7() {
    return dataSens7;
  }

  public int getDataSens8() {
    return dataSens8;
  }*/
}
