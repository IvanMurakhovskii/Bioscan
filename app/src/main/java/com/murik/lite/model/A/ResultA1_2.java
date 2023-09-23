package com.murik.lite.model.A;

import android.content.Context;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;

public class ResultA1_2 extends BaseResult {


  public ResultA1_2(double A, DataByMaxParcelable inputData, Context context) {
    super(A, inputData, context);
    setLegend("1_2");
  }

    public void setResult() {
        if (getA() >= 0.9 && getA() <= 1.14 && getInputData().isPractice()) {
                setColorCRIMSON();
                setPossibleReasons(getResources(R.string.A1_2_CRIMSON));
        } else if (getA() >= 0.9 && getA() <= 1.14) {
            setColorYELLOW();
            setPossibleReasons(getResources(R.string.A1_2_YELLOW));
        } else if (getA() > 1.3 && getA() < 1.7) {
            setColorBLUE();
            setPossibleReasons(getResources(R.string.A1_2_BLUE));
        } else if (getA() > 1.1 && getA() <= 2) {
            setColorGREEN();
        } else if (getA() < 0.9) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A1_2_RED));
        } else if (getA() > 2) {
            setColorBURGUNDY();
            setPossibleReasons(getResources(R.string.A1_2_BURGUNDY));
        } else if (getA() > 1.5 && getA() <= 1.9) {
            setColorRED();
            setPossibleReasons(getResources(R.string.A1_2_RED_R));
        } else if (getA() > 0.30 && getA() <= 0.40) {
            setColorGRAY();
            setPossibleReasons(getResources(R.string.A1_2_GRAY));
        }
    }
}
