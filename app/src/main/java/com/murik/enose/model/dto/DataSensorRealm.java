package com.murik.enose.model.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class  DataSensorRealm extends RealmObject {

  @PrimaryKey
  private long id;
  private String descriptions;

  private boolean isPractice;
  private boolean isFullData;
  private long time;

  private DataSensor leftHandData;
  private DataSensor rightHandData;
  private int gender;
  private int hand;

  public void setFullData(boolean fullData) {
    isFullData = fullData;
  }

  public boolean isFullData() {
    return isFullData;
  }

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

  public void setDescriptions(String descriptions) {
    this.descriptions = descriptions;
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

}
