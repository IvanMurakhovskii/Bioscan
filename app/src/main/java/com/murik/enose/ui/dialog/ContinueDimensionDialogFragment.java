package com.murik.enose.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.SwitchCompat;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.murik.enose.Const;
import com.murik.enose.R;

public class ContinueDimensionDialogFragment extends DialogFragment{


  private DialogListener mNoticeContinueDialogListener;

  public void setDialogListener(
      DialogListener mNoticeContinueDialogListener) {
    this.mNoticeContinueDialogListener = mNoticeContinueDialogListener;
  }

  AlertDialog.Builder builder;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    builder = new AlertDialog.Builder(getActivity());
    builder
        .setTitle("Хотите продолжить измерение другой руки?")
        .setPositiveButton("Да", (DialogInterface dialog, int id) ->
                mNoticeContinueDialogListener.onDialogPositiveClick(2))
        .setNegativeButton("Нет",
            (DialogInterface dialog, int id) ->
                mNoticeContinueDialogListener.onDialogNegativeClick(2)
        );
    return builder.create();
  }
}
