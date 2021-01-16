package com.murik.enose.model.TAU;

import android.content.Context;
import android.graphics.Color;

import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;

public class TAU_30 extends BaseResult {

  public TAU_30(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("Δτ");
  }

    public void setResult() {
        if(getA() >= 26 && getA() <= 34) {
            setColorGREEN();
        } else if (getA() >= 20 && getA() <= 25) {
                setColorYELLOW();
            setPossibleReasons(getResources(R.string.TAU_30_YELLOW));
        } else if (getA() >= 35 && getA() <= 40) {
            setColorRED();
            setPossibleReasons(getResources(R.string.TAU_30_RED));
        } else if (getA() <= 16) {
            setColor(Color.WHITE);
            setPossibleReasons(getResources(R.string.TAU_30_RED));
        } else {
            setColor(Color.WHITE);
        }
    }
}
