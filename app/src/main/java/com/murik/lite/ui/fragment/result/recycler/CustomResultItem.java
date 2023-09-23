package com.murik.lite.ui.fragment.result.recycler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomResultItem {
    private int color;
    private String legend;
    private String description;
    private Double value;
}
