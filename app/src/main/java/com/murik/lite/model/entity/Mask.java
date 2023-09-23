package com.murik.lite.model.entity;

import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mask {
    @PrimaryKey
    private long id;

    private String name;

    private RealmList<Integer> values;
}
