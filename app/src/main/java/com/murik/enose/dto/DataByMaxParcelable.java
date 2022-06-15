package com.murik.enose.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.murik.enose.Const;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataByMaxParcelable implements Parcelable {

    private String descriptions;
    private ArrayList<Integer> rightHandDataSensor;
    private ArrayList<Integer> leftHandDataSensor;
    private int gender = Const.GENDER_MALE;
    private boolean isPractice;
    private boolean isExpert;
    private boolean isAnimalsSelected;
    private String measureType = Const.STANDARD_MEASURE;
    private Float differenceArea = 0.0f;
    private int timeRegistrationMaxSignal = 80;

    private String sensorType = Const.DIAGNOST;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descriptions);
        dest.writeInt(gender);
        dest.writeInt(timeRegistrationMaxSignal);
        if (differenceArea != null) {
            dest.writeFloat(differenceArea);
        }
        dest.writeString(measureType);
        dest.writeList(rightHandDataSensor);
        dest.writeList(leftHandDataSensor);
        dest.writeByte((byte) (isPractice ? 1 : 0));
        dest.writeByte((byte) (isExpert ? 1 : 0));
        dest.writeByte((byte) (isAnimalsSelected ? 1 : 0));
        dest.writeString(sensorType);
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

    private DataByMaxParcelable(Parcel parcel) {
        rightHandDataSensor = new ArrayList<>();
        leftHandDataSensor = new ArrayList<>();
        isPractice = parcel.readByte() != 0;
        isExpert = parcel.readByte() != 0;
        isAnimalsSelected = parcel.readByte() != 0;
        parcel.readList(rightHandDataSensor, Integer.class.getClassLoader());
        parcel.readList(leftHandDataSensor, Integer.class.getClassLoader());
        descriptions = parcel.readString();
        gender = parcel.readInt();
        timeRegistrationMaxSignal = parcel.readInt();
        differenceArea = parcel.readFloat();
        measureType = parcel.readString();
        sensorType = parcel.readString();
    }

}
