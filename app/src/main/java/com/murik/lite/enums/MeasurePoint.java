package com.murik.lite.enums;

import android.support.annotation.NonNull;

import lombok.Getter;

@Getter
public enum MeasurePoint {

    FOREARM(10,"Предплечье"),
    WRIST(20, "Запястье"),
    PALM(30, "Ладонь"),
    LIVER_FACE(40, "Печень  (лицо)"),
    HEART_FACE(45, "Сердце (лицо)"),
    SMALL_INTESTINE_FACE(50, "Тонкий кишечник (лицо)"),
    LARGE_INTESTINE_FACE(60, "Толстый  кишечник (лицо)"),
    RECTUM_FACE(70, "Прямая кишка (лицо)"),
    SMALL_INTESTINE_STOMACH(80, "Тонкий кишечник (живот)"),
    LARGE_INTESTINE_STOMACH(90, "Толстый  кишечник (живот)"),
    PELVIC_ORGANS(100, "Органы малого таза"),
    THYROID_GLAND(110, "Щитовидка"),
    BLADDER_NECK(120, "Мочевой пузырь (шея)"),
    TEMPLE(130, "Висок"),
    STERNUM(140, "Грудина"),
    IMMUNITY(150, "Иммунитет"),
    SOLAR_PLEXUS(160, "Солнечное сплетение"),
    BUD(170, "Почка"),
    NOSE(180, "Нос"),
    HYPOCHONDRIUM(190, "Подреберье"),
    HIP(200, "Бедро"),
    UTERUS_OR_MALE_ORGAN(210, "Матка / мужской орган"),
    LUNG(220, "Легкое"),
    BRONCHI_LUNGS_NECK(230, "Бронхи легкие (шея)"),
    ADRENAL(240, "Надпочечник"),
    GALLBLADDER_ABOVE_RIGHT_COLLARBONE(250, "Желчный пузырь (над правой ключицей)"),
    PANCREAS_ABOVE_RIGHT_COLLARBONE(260, "Поджелудочная железа (над левой ключицей)");

    private Integer id;
    private String description;

    MeasurePoint(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static MeasurePoint getById(Integer id) {
        if (id == null) {
            return null;
        }

        for (MeasurePoint e : MeasurePoint.values()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }

        return null;
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }
}
