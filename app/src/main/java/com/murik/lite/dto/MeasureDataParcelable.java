package com.murik.lite.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.murik.lite.Const;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MeasureDataParcelable implements Parcelable {

    private ArrayList<Integer> data;
    private Integer algorithmId;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        if (algorithmId != null) {
            dest.writeInt(algorithmId);
        }
    }

    public static final Creator<MeasureDataParcelable> CREATOR = new Creator<MeasureDataParcelable>() {

        @Override
        public MeasureDataParcelable createFromParcel(Parcel source) {
            return new MeasureDataParcelable(source);
        }

        @Override
        public MeasureDataParcelable[] newArray(int size) {
            return new MeasureDataParcelable[size];
        }
    };

    private MeasureDataParcelable(Parcel parcel) {
        data = new ArrayList<>();
        algorithmId = parcel.readInt();
    }

}
