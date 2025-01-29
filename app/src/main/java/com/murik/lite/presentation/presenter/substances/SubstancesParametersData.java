package com.murik.lite.presentation.presenter.substances;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SubstancesParametersData {
    private final double II;
    private final double III;
    private final double V;
    private final double IV;
    private final int T_60;
    private final Double S_30_60;
    private final Double S_15_30;
    private final double VI_L;
    private final double VII_L;
    private final double II_30;
    private final double I_30;
}
