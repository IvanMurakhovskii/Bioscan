package com.murik.lite.configuration;

import android.accounts.AuthenticatorException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.common.hash.Hashing;
import com.murik.lite.App;
import com.murik.lite.Screens;
import com.murik.lite.enums.Role;
import com.murik.lite.model.entity.Users;

import java.nio.charset.StandardCharsets;

import io.realm.Realm;
import lombok.val;

public class AuthService {
    private static AuthService instance;
    private Users user;
    private Realm realm;

    private AuthService() {
        this.realm = Realm.getDefaultInstance();
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("login", null);

        if (login != null) {
            this.user = realm.where(Users.class).equalTo("login", login).findFirst();
        }
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }

        return instance;
    }

    @SuppressLint("CommitPrefEdits")
    public void login(String login, String password) throws AuthenticatorException {
        if (login == null || password == null) {
            throw new AuthenticatorException("Не указан логин или пароль!");
        }

        val dbUser = realm.where(Users.class).equalTo("login", login).findFirst();

        val passwordHash = Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();

        if (dbUser == null) {
            throw new AuthenticatorException("Пользователь не найден!");
        }

        if (!passwordHash.equals(dbUser.getPassword())) {
            throw new AuthenticatorException("Неверный пароль!");
        }

        this.user = dbUser;

        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login", login);
        editor.putString("password", login);

        editor.apply();
    }

    public void logout() {
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove("login");
        editor.remove("password");

        editor.apply();

        this.user = null;

        App.INSTANCE.getRouter().navigateTo(Screens.REALM_FRAGMENT);
    }

    public boolean isAuth() {
        return user != null;
    }

    public boolean isAdmin() {
        return getUserRole() == Role.ADMIN;
    }


    public Role getUserRole() {
        if (user == null) {
            return Role.USER;
        }

        return Role.valueOf(user.getRole());
    }

}
