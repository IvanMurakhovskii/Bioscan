package com.murik.enose.model.dto;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ResultDto extends RealmObject{

  @PrimaryKey
  private long id;
  private String description;
  private long date;
  private RealmList<DataSensorRealm> dataSensor;

  public RealmList<DataSensorRealm> getDataSensor() {
    return dataSensor;
  }
  public long getDate() {
    return date;
  }
  public void setDate(long date) {
    this.date = date;
  }
  public long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(long id) {
    this.id = id;
  }
}
