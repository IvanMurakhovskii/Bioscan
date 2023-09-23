package com.murik.lite.configuration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.murik.lite.App;
import com.murik.lite.enums.SummaryTheme;

public class SettingsService {
    private static SettingsService instance;
    private SharedPreferences sharedPreferences;

    private SettingsService() {
        sharedPreferences = App.getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public static SettingsService getInstance() {
        if (instance == null) {
            instance = new SettingsService();
        }

        return instance;
    }

    @SuppressLint("CommitPrefEdits")
    public void setSummaryTheme(SummaryTheme theme){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("summary_theme", theme.toString());
        editor.apply();
    }

    public SummaryTheme getSummaryTheme() {
        String theme = sharedPreferences.getString("summary_theme", null);
        return theme != null ? SummaryTheme.valueOf(theme) : SummaryTheme.ABSTRACT;
    }
}
