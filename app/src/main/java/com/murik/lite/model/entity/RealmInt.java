package com.murik.lite.model.entity;

import io.realm.RealmObject;

public class RealmInt extends RealmObject {

  private int value;

  public void setValue(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
