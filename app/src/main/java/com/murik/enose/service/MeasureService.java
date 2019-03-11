package com.murik.enose.service;

import java.util.List;

public interface MeasureService {
  public List<Float> calculateArea(int[] mask, List<String> sensNumber);
  public List<Float> calculateDelta(List<Float> area, boolean isAreaBad);
}
