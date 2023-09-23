package com.murik.lite.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.murik.lite.R;

public class RemoveDialogFragment extends DialogFragment {


  private DialogListener mNoticeContinueDialogListener;

  public void setDialogListener(
      DialogListener mNoticeContinueDialogListener) {
    this.mNoticeContinueDialogListener = mNoticeContinueDialogListener;
  }

  AlertDialog.Builder builder;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogInitDimension);
    builder.setMessage("Вы действительно хотите удалить измерение?")
        .setPositiveButton("Да", (DialogInterface dialog, int id) ->
                mNoticeContinueDialogListener.onDialogPositiveClick(2))
        .setNegativeButton("Нет",
            (DialogInterface dialog, int id) ->
                mNoticeContinueDialogListener.onDialogNegativeClick(2)
        );
    return builder.create();
  }
}
