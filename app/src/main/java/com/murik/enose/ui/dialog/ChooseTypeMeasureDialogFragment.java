package com.murik.enose.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.murik.enose.Const;
import com.murik.enose.R;

import java.util.Objects;

public class ChooseTypeMeasureDialogFragment extends DialogFragment {

    private DialogListener mDialogListener;

    private RadioGroup rgChooseMeasureType;
    private RadioGroup rgChooseSensor;
    private RadioGroup rgAlgorithm;
    private RadioGroup rgSensorType;
    private RadioGroup rgExpert;
    private LinearLayout llOneSensorSettings;

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
        View view = inflater.inflate(R.layout.dialog_choose_type_measure, null);
        rgChooseMeasureType = view.findViewById(R.id.rg_choose_type_measure);
        rgChooseSensor = view.findViewById(R.id.rg_sensors);
        rgAlgorithm = view.findViewById(R.id.rg_registration_max_signal);
        rgSensorType = view.findViewById(R.id.rg_sensor_type);
        rgExpert = view.findViewById(R.id.rg_expert_type);
        llOneSensorSettings = view.findViewById(R.id.ll_one_sensor_settings);

        rgChooseMeasureType.setOnCheckedChangeListener((group, checkedId) -> {

            switch (checkedId) {
                case R.id.rb_standard_type:
                    llOneSensorSettings.setVisibility(View.GONE);
                    break;
                case R.id.rb_one_sensor_type:
                    llOneSensorSettings.setVisibility(View.VISIBLE);
                    break;
            }
        });

        builder.setView(view)
                .setTitle("Выбор алгоритма")
                .setPositiveButton(R.string.submit, (DialogInterface dialog, int id) -> mDialogListener.onDialogPositiveClick(1))
                .setNegativeButton(R.string.vertical_form_stepper_form_discard_cancel,
                        (DialogInterface dialog, int id) ->
                                ChooseTypeMeasureDialogFragment.this.getDialog().cancel());

        return builder.create();
    }

    public String getSelectedSensor() {
        int checkedId = rgChooseSensor.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_sens1:
                return Const.SENSOR_1;
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

    public int getTimeRegistrationMaxSignal() {
        int checkedId = rgAlgorithm.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_max_80:
                return 80;
            case R.id.rb_max_60:
                return 60;
            case R.id.rb_max_30:
                return 30;
            default:
                return 80;
        }
    }

    public int getSensorType() {
        int checkedId = rgSensorType.getCheckedRadioButtonId();
        switch (checkedId) {
           /* case R.id.rb_sensor_5_sm:
                return Const.SENSOR_5_SM;*/
            case R.id.rb_sensor_7_sm:
                return Const.SENSOR_7_SM;
            default:
                return Const.SENSOR_7_SM;
        }
    }

    public boolean getExpertType() {
        int checkedId = rgExpert.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_expert:
                return true;
            case R.id.rb_user:
                return false;
            default:
                return true;
        }
    }


    public int getMeasureType() {
        if (rgChooseMeasureType.getCheckedRadioButtonId() == R.id.rb_one_sensor_type) {
            return Const.ONE_SENSOR_MEASURE_TYPE;
        }
        return Const.STANDARD_MEASURE_TYPE;
    }
}
