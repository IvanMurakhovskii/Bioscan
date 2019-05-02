package com.murik.enose.model.dto;

import android.os.Parcel;
import android.os.Parcelable;
import com.murik.enose.Const;
import java.util.ArrayList;



public class DataByMaxParcelable implements Parcelable {


  private String descriptions;
  private ArrayList<Integer> rightHandDataSensor;
  private ArrayList<Integer> leftHandDataSensor;
  private int gender = Const.GENDER_MALE;
  private boolean isPractice;
  private Float differenceArea;

  public DataByMaxParcelable(){

  }
  public void setDifferenceArea(Float differenceArea) {
    this.differenceArea = differenceArea;
  }

  public Float getDifferenceArea() {
    return differenceArea;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public int getGender() {
    return gender;
  }

  public void setDescriptions(String descriptions) {
    this.descriptions = descriptions;
  }

  public String getDescriptions() {
    return descriptions;
  }

  public void setRightHandDataSensor(ArrayList<Integer> rightHandDataSensor) {
    this.rightHandDataSensor = rightHandDataSensor;
  }

  public void setLeftHandDataSensor(ArrayList<Integer> leftHandDataSensor) {
    this.leftHandDataSensor = leftHandDataSensor;
  }

  public ArrayList<Integer> getRightHandDataSensor() {
    return rightHandDataSensor;
  }

  public ArrayList<Integer> getLeftHandDataSensor() {
    return leftHandDataSensor;
  }

  public void setPractice(boolean practice) {
    isPractice = practice;
  }

  public boolean isPractice(){
    return isPractice;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(descriptions);
    dest.writeInt(gender);
    dest.writeFloat(differenceArea);
    dest.writeList(rightHandDataSensor);
    dest.writeList(leftHandDataSensor);
    dest.writeByte((byte) (isPractice ? 1 : 0));
  }
  public static final Parcelable.Creator<DataByMaxParcelable> CREATOR = new Parcelable.Creator<DataByMaxParcelable>() {

    @Override
    public DataByMaxParcelable createFromParcel(Parcel source) {
      return new DataByMaxParcelable(source);
    }

    @Override
    public DataByMaxParcelable[] newArray(int size) {
      return new DataByMaxParcelable[size];
    }
  };

  private DataByMaxParcelable(Parcel parcel){
    rightHandDataSensor = new ArrayList<>();
    leftHandDataSensor = new ArrayList<>();
    isPractice = parcel.readByte() != 0;
    parcel.readList(rightHandDataSensor, Integer.class.getClassLoader());
    parcel.readList(leftHandDataSensor, Integer.class.getClassLoader());
    descriptions = parcel.readString();
    gender = parcel.readInt();
    differenceArea = parcel.readFloat();
  }

}
