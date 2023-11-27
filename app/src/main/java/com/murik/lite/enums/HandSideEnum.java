package com.murik.lite.enums;

import android.support.annotation.NonNull;

import lombok.Getter;

@Getter
public enum HandSideEnum {
    LEFT("Левая сторона"),
    RIGHT("Правая сторона");

    private String description;

    HandSideEnum(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }
}
