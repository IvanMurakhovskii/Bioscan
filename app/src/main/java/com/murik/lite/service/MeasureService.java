package com.murik.lite.service;

import com.murik.lite.dto.SensorDataFullParcelable;
import java.util.List;

public interface MeasureService {

  List<Float> calculateArea(int[] mask, List<String> sensNumber,
      SensorDataFullParcelable sensorDataFullParcelable);

  List<Float> calculateDelta(List<Float> area, List<Float> areaTotal);

  List<Float> getAreaTotal();

  List<Float> getAreaHealth();

  List<Float> getAreaEndokrin();

  List<Float> getAreaEnergy();

  List<Float> getAreaBad();

  List<Float> getDeltaHealth();

  List<Float> getDeltaEndokrin();

  List<Float> getDeltaEnergy();

  List<Float> getDeltaBad();

  List<Float> getDeltaBad180();

  Float getDifferenceArea();

}