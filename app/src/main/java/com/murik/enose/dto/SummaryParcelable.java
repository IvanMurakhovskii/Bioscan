package com.murik.enose.dto;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SummaryParcelable implements Parcelable {

    private Double summary;
    private Integer timeRegistrationMaxSignal;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(summary);
        dest.writeInt(timeRegistrationMaxSignal);
    }

    public static final Creator<SummaryParcelable> CREATOR = new Creator<SummaryParcelable>() {

        @Override
        public SummaryParcelable createFromParcel(Parcel source) {
            return new SummaryParcelable(source);
        }

        @Override
        public SummaryParcelable[] newArray(int size) {
            return new SummaryParcelable[size];
        }
    };

    private SummaryParcelable(Parcel parcel) {
        summary = parcel.readDouble();
        timeRegistrationMaxSignal = parcel.readInt();
    }
}
