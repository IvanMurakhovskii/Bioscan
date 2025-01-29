package com.murik.lite.model.stress;

import com.murik.lite.R;
import com.murik.lite.model.substances.ValueInRange;
import com.murik.lite.utils.MathUtils;

import lombok.Getter;

@Getter
public class Stress {

    private final String name;
    private final Float percentProbability;
    private final int color;

    public Stress(String name, int color, ValueInRange... parameters) {
        this.name = name;
        this.color = color;
        this.percentProbability = calculatePercentProbability(parameters);
    }

    private float calculatePercentProbability(ValueInRange... parameters) {
        int count = 0;

        for (ValueInRange parameter : parameters) {
            if (parameter.isValueInRange()) {
                count++;
            }
        }

        return  count == 0 ? 0 : (float) MathUtils.round((float) (count * 100)/parameters.length);
    }


    public int getColor() {
        if (percentProbability > 80) {
            return R.color.colorResultGreen;
        }

        if (percentProbability > 40) {
            return R.color.colorResultYellow;
        }

        return R.color.colorResultRed;
    }

}
