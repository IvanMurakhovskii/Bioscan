package com.murik.lite.ui.fragment.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpView;
import com.murik.lite.App;
import com.murik.lite.R;
import com.murik.lite.Screens;
import com.murik.lite.configuration.SettingsService;
import com.murik.lite.enums.SummaryTheme;

public class SettingsFragment extends MvpAppCompatFragment implements
        MvpView {

    private RadioGroup summaryThemeRg;
    private Button btnSave;

    public static Fragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        summaryThemeRg = view.findViewById(R.id.summary_theme_rg);
        btnSave = view.findViewById(R.id.btn_settings_save);

        btnSave.setOnClickListener((e) -> {
            switch (summaryThemeRg.getCheckedRadioButtonId()) {
                case R.id.lion:
                    SettingsService.getInstance().setSummaryTheme(SummaryTheme.LION);
                    break;
                case R.id.abstraction:
                    SettingsService.getInstance().setSummaryTheme(SummaryTheme.ABSTRACT);
                    break;
                case R.id.dream:
                    SettingsService.getInstance().setSummaryTheme(SummaryTheme.DREAM);
                    break;
                case R.id.apricot:
                    SettingsService.getInstance().setSummaryTheme(SummaryTheme.APRICOT);
                    break;
                case R.id.marshmallow:
                    SettingsService.getInstance().setSummaryTheme(SummaryTheme.MARSHMALLOW);
                    break;
                case R.id.gnomes:
                    SettingsService.getInstance().setSummaryTheme(SummaryTheme.GNOMES);
                    break;
                case R.id.santa_letter:
                    SettingsService.getInstance().setSummaryTheme(SummaryTheme.SANTA_LETTER);
                    break;
                case R.id.glass_lion:
                    SettingsService.getInstance().setSummaryTheme(SummaryTheme.GLASS_LION);
                    break;
                default:
                    SettingsService.getInstance().setSummaryTheme(SummaryTheme.ABSTRACT);
                    break;
            }

            App.INSTANCE.getRouter().navigateTo(Screens.REALM_FRAGMENT);
        });

        if (SettingsService.getInstance().getSummaryTheme().equals(SummaryTheme.ABSTRACT)) {
            summaryThemeRg.check(R.id.abstraction);
        } else {
            summaryThemeRg.check(R.id.lion);
        }

    }

}
