package com.murik.lite.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

public class TakeAwayHandDialogFragment extends DialogFragment {

    AlertDialog.Builder builder;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Снимите прибор с кожи!").create();

        AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null) {
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
        }

        dialog.show();
        TextView messageText = dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        messageText.setTypeface(null ,Typeface.BOLD);

        return dialog;
    }
}
