package com.murik.enose.service.Impl;

import com.murik.enose.Const;
import com.murik.enose.model.dto.SensorDataFullParcelable;
import com.murik.enose.service.MeasureService;
import java.util.ArrayList;
import java.util.List;

public class MeasureServiceImpl implements MeasureService {

  private SensorDataFullParcelable sensorDataFullParcelable;

  private List<Float> areaTotal;
  private List<Float> areaBad180;

  public MeasureServiceImpl(
      SensorDataFullParcelable sensorDataFullParcelable) {
    this.sensorDataFullParcelable = sensorDataFullParcelable;
    int[] bad = {170,180};
    areaTotal = calculateArea(Const.TOTAL, Const.allSens);
    areaBad180 = calculateArea(bad, Const.allSens);
  }

  public List<Float> calculateArea(int[] mask, List<String> sensNumber){

    float areaLeft = 0f;
    float areaRight = 0f;
    float dx = 1f;
    int y1 = 1;
    int y2 = 1;

    for(String sensor : sensNumber){
      for (int key = 0; key < mask.length - 1; key++) {
        dx = mask[key] - mask[key + 1];
        if (!sensorDataFullParcelable.getDataSensorMapLeftHand().isEmpty()) {
          if(sensorDataFullParcelable.getDataSensorMapLeftHand().get(sensor) != null){
            y1 = sensorDataFullParcelable.getDataSensorMapLeftHand().get(sensor).get(mask[key]);
            y2 = sensorDataFullParcelable.getDataSensorMapLeftHand().get(sensor).get(mask[key + 1]);
            if(y1*y2 > 0){
              areaLeft +=  Math.abs((y1 + y2) / 2f * dx);
            } else {
              float dx1 = (1f + Math.abs(y2) * dx);
              areaLeft += Math.abs( (dx1 * Math.abs(y1) + (dx - dx1) * Math.abs(y2)) / 2f);
            }
          }
        }
        if (!sensorDataFullParcelable.getDataSensorMapRightHand().isEmpty()) {
          if(sensorDataFullParcelable.getDataSensorMapRightHand().get(sensor) != null){
            y1 = sensorDataFullParcelable.getDataSensorMapRightHand().get(sensor).get(mask[key]);
            y2 = sensorDataFullParcelable.getDataSensorMapRightHand().get(sensor).get(mask[key + 1]);
            if(y1*y2 > 0){
              areaRight +=  Math.abs((y1 + y2) / 2f * dx);
            } else {
              float dx1 = (1f + Math.abs(y2) * dx);
              areaRight += Math.abs( (dx1 * Math.abs(y1) + (dx - dx1) * Math.abs(y2)) / 2f);
            }
          }
        }
      }
    }
    List<Float> area = new ArrayList<>();
    area.add(areaLeft);
    area.add(areaRight);
    return area;
  }

  public List<Float> calculateDelta(List<Float> area,boolean isBadMask){

    float deltaLeft = 0f;
    float deltaRight = 0f;
    if(isBadMask){
      deltaRight = (areaBad180.get(1)/area.get(1))*100;
      deltaLeft = (areaBad180.get(0)/area.get(0))*100 ;
    } else {
      deltaRight = (area.get(1)/areaTotal.get(1))*100;
      deltaLeft = (area.get(0)/areaTotal.get(0))*100 ;
    }


    if(Float.isInfinite(deltaLeft) || Float.isNaN(deltaLeft)){
     deltaLeft = 0;
    }
    if(Float.isInfinite(deltaRight) || Float.isNaN(deltaRight)){
      deltaRight = 0;
    }
    List<Float> delta = new ArrayList<>();
    delta.add(deltaLeft);
    delta.add(deltaRight);
    return delta;

  }

}
