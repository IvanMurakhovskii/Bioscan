package com.murik.enose.service.Impl;

import com.murik.enose.utils.ListUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BaseMeasureService {
    public Float getAreaByMask(int[] mask, List<Integer> data) {
        float area = 0f;
        float dx;
        int y1;
        int y2;
        if (data != null) {
            for (int key = 0; key < mask.length - 1; key++) {
                if (data.size() >= mask[key]) {
                    dx = mask[key] - mask[key + 1];
                    if (!data.isEmpty()) {
//                        y1 = data.get(mask[key]);
//                        y2 = data.get(mask[key + 1]);

                        y1 = ListUtils.listSizeCondition(data, mask[key]) ? data.get(mask[key]) : 0;
                        y2 = ListUtils.listSizeCondition(data, mask[key + 1]) ? data.get(mask[key + 1]) : 0;
                        if (y1 * y2 > 0) {
                            area += Math.abs((y1 + y2) / 2f * dx);
                        } else {
                            float dx1 = (1f + Math.abs(y2) * dx);
                            area += Math.abs((dx1 * Math.abs(y1) + (dx - dx1) * Math.abs(y2)) / 2f);
                        }
                    }
                }
            }
        }
        return area;
    }

    public Float calculateDelta(final Float area, final Float areaTotal) {

        float delta = 0f;

        delta = (area / areaTotal) * 100;

        if (Float.isInfinite(delta) || Float.isNaN(delta)) {
            delta = 0;
        }
        return delta;
    }

    public Float calculateDifference(final float a, final float b) {
        float result = 0f;
        if (a > b) {
            result = ((a - b) / a) * 100;
        } else {
            result = ((b - a) / b) * 100;
        }

        if (Float.isInfinite(result) || Float.isNaN(result)) {
            result = 0;
        }

        return result;
    }

    public Float calculateDifferenceLeftRight(final float left, final float right) {
        float result = ((left - right) / ((left + right) / 2)) * 100;

        if (Float.isInfinite(result) || Float.isNaN(result)) {
            result = 0;
        }

        return result;
    }

    public Float getPS3425(final List<Integer> data, final int[] mask) {
        if (data != null && !data.isEmpty() && mask.length >= 4 && data.size() > mask[mask.length - 1]) {
            return ((float) (data.get(mask[2]) * data.get(mask[3])) / (float) (data.get(mask[1]) * data.get(mask[4])));
        }
        return 0f;
    }

    public Float getPS2435(final List<Integer> data, final int[] mask) {
        if (data != null && !data.isEmpty() && mask.length >= 4 && data.size() > mask[mask.length - 1]) {
            return ((float) (data.get(mask[1]) * data.get(mask[3])) / (float) (data.get(mask[2]) * data.get(mask[4])));
        }
        return 0f;
    }

    public Float getAreaDifference(final Float areaLeft, final Float areaRight) {
        if (areaLeft != 0 && areaRight != 0) {
            Float areaTotalAverage = (areaLeft + areaRight) / 2;
            Float areaTotalDifference = 2 * ((areaLeft - areaTotalAverage) / areaTotalAverage) * 100;
            BigDecimal bd = new BigDecimal(areaTotalDifference).setScale(1, RoundingMode.FLOOR);
            return Math.abs(bd.floatValue());
        }
        return 0F;
    }
}
