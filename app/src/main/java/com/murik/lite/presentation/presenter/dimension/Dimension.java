package com.murik.lite.presentation.presenter.dimension;

import com.murik.lite.Const;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Dimension {
    private String description;
    private boolean isPractice;
    private int gender = Const.GENDER_MALE;
    private Integer algorithmId;
}
