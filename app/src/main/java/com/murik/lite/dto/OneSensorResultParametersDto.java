package com.murik.lite.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OneSensorResultParametersDto {
    private Double s20_30;
    private Double s20_60;
    private Double s30_60;
    private Double L;
    private Double En;
    private Double E2;
}
