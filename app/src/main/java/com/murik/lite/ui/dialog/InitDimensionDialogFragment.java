package com.murik.lite.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.murik.lite.Const;
import com.murik.lite.R;
import com.murik.lite.configuration.AuthService;
import com.murik.lite.enums.BluetoothDimensionAlgorithm;
import com.murik.lite.enums.MeasurePoint;
import com.murik.lite.enums.NoseType;

import java.util.Objects;

public class InitDimensionDialogFragment extends AppCompatDialogFragment {

    private DialogListener mDialogListener;
    private EditText descriptions;
    private RadioGroup rgGender;
    private RadioGroup swHand;
    private RadioGroup noseTypeRg;
    private SwitchCompat swPractice;
    private EditText dimensionTime;
    private EditText substanceDimensionTime;
    private Spinner dimensionTimeSpinner;
    private Spinner measurePointSpinner;
    private TextView tvAlgorithmDescription;
    private LinearLayout noseTypeLayout;
    private LinearLayout measurePointLayout;

    private int gender = Const.GENDER_MALE;
    private boolean isLeftHand = true;
    private NoseType noseType = NoseType.BIOSCANER;


    public void setDialogListener(
            DialogListener mNoticeDialogListener) {
        this.mDialogListener = mNoticeDialogListener;
    }

    AlertDialog.Builder builder;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogInitDimension);
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_start_dimension, null);
        descriptions = view.findViewById(R.id.start_dimension_description);
        tvAlgorithmDescription = view.findViewById(R.id.tv_algorithm_description);
        rgGender = view.findViewById(R.id.rg_gender_des);
//        swPractice = view.findViewById(R.id.sc_practice_des);
        swHand = view.findViewById(R.id.rg_hand);
        noseTypeRg = view.findViewById(R.id.rg_nose_type);
        dimensionTimeSpinner = view.findViewById(R.id.spinner_dimension_time);
        measurePointLayout = view.findViewById(R.id.measure_point_layout);
        noseTypeLayout = view.findViewById(R.id.nose_type_layout);

        dimensionTimeSpinner.setAdapter(new BluetoothDimensionSpinnerAdapter(
                Objects.requireNonNull(this.getContext()),
                android.R.layout.simple_spinner_dropdown_item,
                BluetoothDimensionAlgorithm.getValuesByRole())
        );

        measurePointSpinner = view.findViewById(R.id.spinner_measure_point);

        if (AuthService.getInstance().isAdmin()) {
            measurePointLayout.setVisibility(View.VISIBLE);
            noseTypeLayout.setVisibility(View.VISIBLE);
        }

        measurePointSpinner.setAdapter(new MeasurePointSpinnerAdapter(
                Objects.requireNonNull(this.getContext()),
                android.R.layout.simple_spinner_dropdown_item,
                MeasurePoint.values())
        );

        dimensionTimeSpinner.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        tvAlgorithmDescription.setText(BluetoothDimensionAlgorithm.values()[position].getDescription());
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );

        rgGender.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_male_des:
                    gender = Const.GENDER_MALE;
                    break;
                case R.id.rb_feminine_des:
                    gender = Const.GENDER_FEMININE;
                    break;
            }
        });

        noseTypeRg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_diagnost:
                    noseType = NoseType.DIAGNOST;
                    break;
                default:
                    noseType = NoseType.BIOSCANER;
            }
        });
        builder.setView(view)
                .setTitle("Измерение")
                .setPositiveButton("Начать", (DialogInterface dialog, int id) -> mDialogListener. onDialogPositiveClick(1))
                .setNegativeButton("Отмена",
                        (DialogInterface dialog, int id) ->
                                InitDimensionDialogFragment.this.getDialog().cancel());

        return builder.create();
    }

    public String getDescriptions() {
        return descriptions.getText().toString();
    }

    public int getGender() {
        return gender;
    }

    public boolean isLeftHand() {
        swHand.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_left_hand:
                    isLeftHand = true;
                    break;
                case R.id.rb_right_hand:
                    isLeftHand = false;
                    break;
            }
        });
        return isLeftHand;
    }

    public NoseType getNoseType() {
        if (!AuthService.getInstance().isAdmin()) {
            return NoseType.BIOSCANER;
        }

        return noseType;
    }

    public BluetoothDimensionAlgorithm getAlgorithm() {
        return (BluetoothDimensionAlgorithm) dimensionTimeSpinner.getSelectedItem();
    }

    public MeasurePoint getMeasurePoint() {
        return (MeasurePoint) measurePointSpinner.getSelectedItem();
    }
}
