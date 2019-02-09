package com.murik.enose.model.Entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class DataSensor extends RealmObject {


  private RealmList<RealmInt> dataSens1;
  private RealmList<RealmInt> dataSens2;
  private RealmList<RealmInt> dataSens3;
  private RealmList<RealmInt> dataSens4;
  private RealmList<RealmInt> dataSens5;
  private RealmList<RealmInt> dataSens6;
  private RealmList<RealmInt> dataSens7;
  private RealmList<RealmInt> dataSens8;


  public void setDataSens1(RealmList<RealmInt> dataSens1) {
    this.dataSens1 = dataSens1;
  }

  public void setDataSens2(RealmList<RealmInt> dataSens2) {
    this.dataSens2 = dataSens2;
  }

  public void setDataSens3(RealmList<RealmInt> dataSens3) {
    this.dataSens3 = dataSens3;
  }

  public void setDataSens4(RealmList<RealmInt> dataSens4) {
    this.dataSens4 = dataSens4;
  }

  public void setDataSens5(RealmList<RealmInt> dataSens5) {
    this.dataSens5 = dataSens5;
  }

  public void setDataSens6(RealmList<RealmInt> dataSens6) {
    this.dataSens6 = dataSens6;
  }

  public void setDataSens7(RealmList<RealmInt> dataSens7) {
    this.dataSens7 = dataSens7;
  }

  public void setDataSens8(RealmList<RealmInt> dataSens8) {
    this.dataSens8 = dataSens8;
  }

  public RealmList<RealmInt> getDataSens1() {
    return dataSens1;
  }

  public RealmList<RealmInt> getDataSens2() {
    return dataSens2;
  }

  public RealmList<RealmInt> getDataSens3() {
    return dataSens3;
  }

  public RealmList<RealmInt> getDataSens4() {
    return dataSens4;
  }

  public RealmList<RealmInt> getDataSens5() {
    return dataSens5;
  }

  public RealmList<RealmInt> getDataSens6() {
    return dataSens6;
  }

  public RealmList<RealmInt> getDataSens7() {
    return dataSens7;
  }

  public RealmList<RealmInt> getDataSens8() {
    return dataSens8;
  }
}
