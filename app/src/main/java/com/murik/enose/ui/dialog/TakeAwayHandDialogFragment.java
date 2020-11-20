package com.murik.enose.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class TakeAwayHandDialogFragment extends DialogFragment{

  AlertDialog.Builder builder;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    builder = new AlertDialog.Builder(getActivity());
    builder
            .setTitle("Уберите руку от прибора!!!")
            .create();

    return builder.create();
  }
}
