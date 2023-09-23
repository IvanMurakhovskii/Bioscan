package com.murik.lite.dto;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SummaryFullParcelable implements Parcelable {

    private Double summaryLeft;
    private Double summaryRight;
    private Integer timeRegistrationMaxSignal;
    private Integer gender;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(summaryLeft);
        dest.writeDouble(summaryRight);
        dest.writeDouble(gender);
        dest.writeInt(timeRegistrationMaxSignal);
    }

    public static final Creator<SummaryFullParcelable> CREATOR = new Creator<SummaryFullParcelable>() {

        @Override
        public SummaryFullParcelable createFromParcel(Parcel source) {
            return new SummaryFullParcelable(source);
        }

        @Override
        public SummaryFullParcelable[] newArray(int size) {
            return new SummaryFullParcelable[size];
        }
    };

    private SummaryFullParcelable(Parcel parcel) {
        summaryLeft = parcel.readDouble();
        summaryRight = parcel.readDouble();
        timeRegistrationMaxSignal = parcel.readInt();
        gender = parcel.readInt();
    }
}
