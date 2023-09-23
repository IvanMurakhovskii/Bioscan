package com.murik.lite.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.murik.lite.Const;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorDataFullParcelable implements Parcelable {

  private String descriptions;
  Map<String, ArrayList<Integer>> dataSensorMapLeftHand = new HashMap<>();
  Map<String, ArrayList<Integer>> dataSensorMapRightHand = new HashMap<>();
  private int gender = Const.GENDER_MALE;
  private boolean isPractice;
  private boolean isFullData;
  private Integer algorithmId;

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(descriptions);
    dest.writeInt(gender);
    dest.writeInt(algorithmId);
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
    dataSensorMapRightHand = new HashMap<>();
    dataSensorMapLeftHand = new HashMap<>();
    isPractice = parcel.readByte() != 0;
    isFullData = parcel.readByte() != 0;
    parcel.readMap(dataSensorMapRightHand, Map.class.getClassLoader());
    parcel.readMap(dataSensorMapLeftHand, Map.class.getClassLoader());
    descriptions = parcel.readString();
    gender = parcel.readInt();
    algorithmId = parcel.readInt();
  }
}
