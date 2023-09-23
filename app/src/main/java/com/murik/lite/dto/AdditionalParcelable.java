package com.murik.lite.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.murik.lite.Const;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdditionalParcelable implements Parcelable {

    private int foreArmLeft;
    private int foreArmRight;
    private int liver;
    private int heart;

    private String sensorType = Const.DIAGNOST;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(foreArmLeft);
        dest.writeInt(foreArmRight);
        dest.writeInt(liver);
        dest.writeInt(heart);
    }

    public static final Creator<AdditionalParcelable> CREATOR = new Creator<AdditionalParcelable>() {

        @Override
        public AdditionalParcelable createFromParcel(Parcel source) {
            return new AdditionalParcelable(source);
        }

        @Override
        public AdditionalParcelable[] newArray(int size) {
            return new AdditionalParcelable[size];
        }
    };

    private AdditionalParcelable(Parcel parcel) {
        foreArmLeft = parcel.readInt();
        foreArmRight = parcel.readInt();
        liver = parcel.readInt();
        heart = parcel.readInt();
    }

}
