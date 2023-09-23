package com.murik.lite.ui.fragment.additionalCalculation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.lite.App;
import com.murik.lite.R;
import com.murik.lite.Screens;
import com.murik.lite.dto.AdditionalParcelable;
import com.murik.lite.presentation.presenter.additionalCalculation.AdditionalCalculationPresenter;
import com.murik.lite.presentation.view.additionalCalculation.AdditionalCalculationView;

public class AdditionalCalculationFragment extends MvpAppCompatFragment implements
        AdditionalCalculationView {

    public static final String ADDITIONAL_CALCULATION_TAG = "ADDITIONAL_CALCULATION";

    @InjectPresenter
    AdditionalCalculationPresenter additionalCalculationPresenter;

    private EditText foreArmLeft;
    private EditText foreArmRight;
    private EditText liver;
    private EditText heart;
    private Button calculateResultButton;

    public static Fragment newInstance() {
        return new AdditionalCalculationFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_addition_info, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foreArmLeft = view.findViewById(R.id.foreArmLeft);
        foreArmRight = view.findViewById(R.id.foreArmRight);
        liver = view.findViewById(R.id.liver);
        heart = view.findViewById(R.id.heart);
        calculateResultButton = view.findViewById(R.id.btn_calculate_additions);

        foreArmLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        foreArmRight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        liver.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        heart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        calculateResultButton.setOnClickListener(v -> {
            AdditionalParcelable additionalParcelable = new AdditionalParcelable();
            additionalParcelable.setForeArmLeft(Integer.parseInt(foreArmLeft.getText().toString()));
            additionalParcelable.setForeArmRight(Integer.parseInt(foreArmRight.getText().toString()));
            additionalParcelable.setLiver(Integer.parseInt(liver.getText().toString()));
            additionalParcelable.setHeart(Integer.parseInt(heart.getText().toString()));

            App.INSTANCE.getRouter().navigateTo(Screens.ADDITIONAL_RESULT_FRAGMENT, additionalParcelable);
        });
    }

    private void validate() {
        if (foreArmLeft.getText().length() > 0 && foreArmRight.length() > 0 &&
                liver.length() > 0 && heart.length() > 0) {
            calculateResultButton.setEnabled(true);
        } else {
            calculateResultButton.setEnabled(false);
        }
    }
}
