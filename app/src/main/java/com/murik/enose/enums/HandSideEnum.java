package com.murik.enose.enums;

import lombok.Getter;

@Getter
public enum HandSideEnum {
    LEFT("Левая рука"),
    RIGHT("Правая рука");

    private String description;

    HandSideEnum(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
