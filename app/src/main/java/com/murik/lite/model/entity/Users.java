package com.murik.lite.model.entity;

import com.murik.lite.enums.Role;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends RealmObject {

    @PrimaryKey
    private long id;

    private String login;
    private String password;
    private String role = Role.USER.name();
}
