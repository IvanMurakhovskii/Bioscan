package com.murik.lite.dto;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StressSummaryFullParcelable implements Parcelable {

    private Double summaryRun;
    private Double summaryStop;
    private Integer gender;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(summaryRun);
        dest.writeDouble(summaryStop);
        dest.writeDouble(gender);
    }

    public static final Creator<StressSummaryFullParcelable> CREATOR = new Creator<StressSummaryFullParcelable>() {

        @Override
        public StressSummaryFullParcelable createFromParcel(Parcel source) {
            return new StressSummaryFullParcelable(source);
        }

        @Override
        public StressSummaryFullParcelable[] newArray(int size) {
            return new StressSummaryFullParcelable[size];
        }
    };

    private StressSummaryFullParcelable(Parcel parcel) {
        summaryRun = parcel.readDouble();
        summaryStop = parcel.readDouble();
        gender = parcel.readInt();
    }
}
