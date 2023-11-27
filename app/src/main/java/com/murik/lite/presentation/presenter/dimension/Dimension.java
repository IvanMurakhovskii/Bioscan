package com.murik.lite.presentation.presenter.dimension;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Dimension {
    private String description;
    private boolean isPractice;
    private int gender;
    private Integer algorithmId;
}
