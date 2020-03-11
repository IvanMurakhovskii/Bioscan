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
import android.widget.RadioGroup;

import com.murik.enose.Const;
import com.murik.enose.R;

import java.util.Objects;

public class ChooseTypeMeasureDialogFragment extends DialogFragment {

    private DialogListener mDialogListener;

    private RadioGroup rgChooseMeasureType;
    private RadioGroup rgChooseSensor;

    private int choosenSensor = 0;

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
        builder.setView(view)
                .setTitle("Выбор алгоритма")
                .setPositiveButton(R.string.submit, (DialogInterface dialog, int id) -> mDialogListener.onDialogPositiveClick(1))
                .setNegativeButton(R.string.vertical_form_stepper_form_discard_cancel,
                        (DialogInterface dialog, int id) ->
                                ChooseTypeMeasureDialogFragment.this.getDialog().cancel());

        return builder.create();
    }

    public String getChoosenSensor() {
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

    public int getMeasureType() {
        if (rgChooseMeasureType.getCheckedRadioButtonId() == R.id.rb_one_sensor_type) {
            return Const.ONE_SENSOR_MEASURE_TYPE;
        }
        return Const.STANDARD_MEASURE_TYPE;
    }
}
