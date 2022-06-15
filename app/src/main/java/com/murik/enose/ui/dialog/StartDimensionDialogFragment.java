package com.murik.enose.ui.dialog;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.enums.BluetoothDimensionTimeEnum;

import java.util.Objects;

import static com.murik.enose.Const.BIOSCANNER_DEVICE_TYPE;
import static com.murik.enose.Const.DUAL_SENSOR_DEVICE_TYPE;

public class StartDimensionDialogFragment extends AppCompatDialogFragment {

    private DialogListener mDialogListener;
    private EditText descriptions;
    private RadioGroup rgGender;
    private RadioGroup rgDeviceType;
    private RadioGroup swHand;
    private SwitchCompat swPractice;
    private EditText dimensionTime;
    private EditText substanceDimensionTime;
    private Spinner dimensionTimeSpinner;
    private View view;

    private int gender = Const.GENDER_MALE;
    private boolean isLeftHand = true;


    public void setDialogListener(
            DialogListener mNoticeDialogListener) {
        this.mDialogListener = mNoticeDialogListener;
    }

    AlertDialog.Builder builder;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_start_dimension, null);
        descriptions = view.findViewById(R.id.start_dimension_description);
        rgGender = view.findViewById(R.id.rg_gender_des);
        rgDeviceType = view.findViewById(R.id.rg_device_type);
        swPractice = view.findViewById(R.id.sc_practice_des);
        swHand = view.findViewById(R.id.rg_hand);
        dimensionTimeSpinner = view.findViewById(R.id.spinner_dimension_time);

        dimensionTimeSpinner.setAdapter(new ArrayAdapter<>(
                Objects.requireNonNull(this.getContext()),
                android.R.layout.simple_spinner_dropdown_item,
                BluetoothDimensionTimeEnum.values())
        );
        builder.setView(view)
                .setTitle("Измерение")
                .setPositiveButton("Начать", (DialogInterface dialog, int id) -> {
                    mDialogListener.onDialogPositiveClick(1);
                })
                .setNegativeButton("Отмена",
                        (DialogInterface dialog, int id) ->
                                StartDimensionDialogFragment.this.getDialog().cancel());

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


        return builder.create();
    }

    public String getDescriptions() {
        return descriptions.getText().toString();
    }

    public int getGender() {

        return gender;
    }

    public int getDeviceType() {

        int selectedId = rgDeviceType.getCheckedRadioButtonId();

        RadioButton radioButton = view.findViewById(selectedId);
        return "Биосканер".equals(radioButton.getText()) ? BIOSCANNER_DEVICE_TYPE : DUAL_SENSOR_DEVICE_TYPE;
    }

    public boolean isLeftHand() {
        return isLeftHand;
    }
    public BluetoothDimensionTimeEnum getDimensionTime() {
        return (BluetoothDimensionTimeEnum) dimensionTimeSpinner.getSelectedItem();
    }

    public boolean isPractice() {
        return swPractice.isChecked();
    }

}
