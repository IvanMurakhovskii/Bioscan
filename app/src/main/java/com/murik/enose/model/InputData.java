package com.murik.enose.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class InputData implements Parcelable{

  private ArrayList<Integer> datasens;
  private boolean isPractice;

   public InputData(){

  }

  public void setDatasens(ArrayList<Integer> datasens) {
    this.datasens = datasens;
  }

  public ArrayList<Integer> getDatasens() {
    return datasens;
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
     dest.writeList(datasens);
     dest.writeByte((byte) (isPractice ? 1 : 0));
  }
  public static final Parcelable.Creator<InputData> CREATOR = new Parcelable.Creator<InputData>() {

    @Override
    public InputData createFromParcel(Parcel source) {
      return new InputData(source);
    }

    @Override
    public InputData[] newArray(int size) {
      return new InputData[size];
    }
  };

  private InputData(Parcel parcel){
    datasens = new ArrayList<>();
     parcel.readList(datasens, Integer.class.getClassLoader());
     isPractice = parcel.readByte() != 0;
  }

}
