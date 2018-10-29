package com.murik.enose.model.dto;

import android.os.Parcel;
import android.os.Parcelable;
import com.murik.enose.Const;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SensorDataFullParcelable implements Parcelable {

  private String descriptions;
  Map<String, ArrayList<Integer>> dataSensorMapLeftHand = new HashMap<String, ArrayList<Integer>>();
  Map<String, ArrayList<Integer>> dataSensorMapRightHand = new HashMap<String, ArrayList<Integer>>();
  private int gender = Const.GENDER_MALE;
  private boolean isPractice;
  private boolean isFullData;

  public SensorDataFullParcelable(){

  }


  public boolean isFullData() {
    return isFullData;
  }

  public void setFullData(boolean fullData) {
    isFullData = fullData;
  }

  public void setDescriptions(String descriptions) {
    this.descriptions = descriptions;
  }

  public String getDescriptions() {
    return descriptions;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public int getGender() {
    return gender;
  }

  public void setPractice(boolean practice) {
    isPractice = practice;
  }

  public boolean isPractice() {
    return isPractice;
  }

  public void setDataSensorMapLeftHand(
      Map<String, ArrayList<Integer>> dataSensorMapLeftHand) {
    this.dataSensorMapLeftHand = dataSensorMapLeftHand;
  }

  public Map<String, ArrayList<Integer>> getDataSensorMapLeftHand() {
    return dataSensorMapLeftHand;
  }

  public void setDataSensorMapRightHand(
      Map<String, ArrayList<Integer>> dataSensorMapRightHand) {
    this.dataSensorMapRightHand = dataSensorMapRightHand;
  }

  public Map<String, ArrayList<Integer>> getDataSensorMapRightHand() {
    return dataSensorMapRightHand;
  }

  @Override
  public int describeContents() {
    return 0;
  }



  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(descriptions);
    dest.writeInt(gender);
    dest.writeByte((byte) (isPractice ? 1 : 0));
    dest.writeByte((byte) (isFullData ? 1 : 0));
    dest.writeMap(dataSensorMapLeftHand);
    dest.writeMap(dataSensorMapRightHand);
  }

  public static final Parcelable.Creator<SensorDataFullParcelable> CREATOR = new Parcelable.Creator<SensorDataFullParcelable>() {

    @Override
    public SensorDataFullParcelable createFromParcel(Parcel source) {
      return new SensorDataFullParcelable(source);
    }

    @Override
    public SensorDataFullParcelable[] newArray(int size) {
      return new SensorDataFullParcelable[size];
    }
  };

  private SensorDataFullParcelable(Parcel parcel){
    dataSensorMapRightHand = new HashMap<String, ArrayList<Integer>>();
    dataSensorMapLeftHand = new HashMap<String, ArrayList<Integer>>();
    isPractice = parcel.readByte() != 0;
    isFullData = parcel.readByte() != 0;
    parcel.readMap(dataSensorMapRightHand, Map.class.getClassLoader());
    parcel.readMap(dataSensorMapLeftHand, Map.class.getClassLoader());
    descriptions = parcel.readString();
    gender = parcel.readInt();
  }
}
