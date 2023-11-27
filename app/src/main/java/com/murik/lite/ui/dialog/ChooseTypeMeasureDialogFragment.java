package com.murik.lite.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.murik.lite.Const;
import com.murik.lite.R;
import com.murik.lite.configuration.AuthService;
import com.murik.lite.enums.BluetoothDimensionAlgorithm;

import java.util.Objects;

import static com.murik.lite.R.id.rb_bioscaner;

public class ChooseTypeMeasureDialogFragment extends DialogFragment {

    private DialogListener mDialogListener;

    private RadioGroup rgChooseMeasureType;
    private RadioGroup rgChooseSensor;
    private RadioGroup rgRgSensorType;
    private RadioGroup rgAlgorithm;
    private RadioGroup rgSensorType;
    private RadioGroup rgExpert;
    private LinearLayout llSensors;
    private LinearLayout llMaxSignal;
    private Switch animalsSwitch;
    private ScrollView sensorsScroll;
    private Spinner algorithmSpinner;
    private TextView algorithmDescription;

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
        View view = inflater.inflate(R.layout.dialog_choose_type_measure, null);
        rgChooseMeasureType = view.findViewById(R.id.rg_choose_type_measure);
        rgChooseSensor = view.findViewById(R.id.rg_sensors);
        rgSensorType= view.findViewById(R.id.rg_sensor_type);
        rgSensorType = view.findViewById(R.id.rg_sensor_type);
        rgExpert = view.findViewById(R.id.rg_expert_type);
        llSensors = view.findViewById(R.id.ll_sensors);
        llMaxSignal = view.findViewById(R.id.ll_max_signal);
        animalsSwitch = view.findViewById(R.id.animals);
        sensorsScroll = view.findViewById(R.id.scroll_sensors);
        algorithmSpinner = view.findViewById(R.id.spinner_algorithm);
        algorithmDescription = view.findViewById(R.id.tv_algorithm_desc);

        algorithmSpinner.setAdapter(new CustomSpinnerAdapter(
                Objects.requireNonNull(this.getContext()),
                android.R.layout.simple_spinner_dropdown_item,
                BluetoothDimensionAlgorithm.getValuesByRole())
        );

        algorithmSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        algorithmDescription.setText(BluetoothDimensionAlgorithm.values()[position].getDescription());
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );

        if (AuthService.getInstance().isAdmin()) {
            rgChooseMeasureType.setVisibility(View.VISIBLE);
            rgExpert.setVisibility(View.VISIBLE);
            rgSensorType.setVisibility(View.VISIBLE);
            animalsSwitch.setVisibility(View.VISIBLE);
            sensorsScroll.setVisibility(View.VISIBLE);
            llSensors.setVisibility(View.VISIBLE);
        }

        rgSensorType.setOnCheckedChangeListener(this::onSensorTypeChanged);

        builder.setView(view)
                .setTitle("Выбор алгоритма")
                .setPositiveButton(R.string.submit, (DialogInterface dialog, int id) -> mDialogListener.onDialogPositiveClick(1))
                .setNegativeButton(R.string.cancel,
                        (DialogInterface dialog, int id) ->
                                ChooseTypeMeasureDialogFragment.this.getDialog().cancel());

        return builder.create();
    }

    private void onSensorTypeChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case rb_bioscaner:
                llMaxSignal.setVisibility(View.VISIBLE);
                break;
            default:
                llMaxSignal.setVisibility(View.VISIBLE);
        }
    }

    public String getSelectedSensor() {
        int checkedId = rgChooseSensor.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_sens2:
                return Const.SENSOR_2;
            case R.id.rb_sens3:
                return Const.SENSOR_3;
            case R.id.rb_sens4:
                return Const.SENSOR_4;
            case R.id.rb_sens5:
                return Const.SENSOR_5;
            case R.id.rb_sens6:
                return Const.SENSOR_6;
            case R.id.rb_sens7:
                return Const.SENSOR_7;
            case R.id.rb_sens8:
                return Const.SENSOR_8;
            default:
                return Const.SENSOR_1;
        }
    }

    public String getSensorType() {
        int checkedId = rgSensorType.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_bioscaner:
                return Const.BIOSCANER;
            case R.id.rb_diagnost:
                return Const.DIAGNOST;
            default:
                return Const.BIOSCANER;
        }
    }

    public boolean getExpertType() {
        int checkedId = rgExpert.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_expert:
                return true;
            default:
                return false;
        }
    }


    public int getMeasureType() {
        int checkedId = rgChooseMeasureType.getCheckedRadioButtonId() ;
        switch (checkedId) {
            case R.id.rb_one_sensor_type: return Const.ONE_SENSOR_MEASURE_TYPE;
            default: return Const.CHART;
        }
    }

    public boolean isAnimalsSelected() {
        return animalsSwitch.isChecked();
    }

    public BluetoothDimensionAlgorithm getAlgotirhm() {
        return (BluetoothDimensionAlgorithm) algorithmSpinner.getSelectedItem();
    }
}
