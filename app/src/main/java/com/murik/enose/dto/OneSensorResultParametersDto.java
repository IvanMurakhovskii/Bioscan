package com.murik.enose.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OneSensorResultParametersDto {
    private Float s20_30;
    private Float s20_60;
    private Float s30_60;
    private Float L;
    private Float En;
    private Float E2;
}
