package com.murik.lite.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.murik.lite.R;

public class StartDimensionDialogFragment extends AppCompatDialogFragment {

    private StartDialogListener mDialogListener;

    public void setDialogListener(
            StartDialogListener mNoticeDialogListener) {
        this.mDialogListener = mNoticeDialogListener;
    }

    AlertDialog.Builder builder;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);

        builder.setCancelable(false)
                .setMessage("Дождитесь стабилизации сигнала сенсора и нажмите \"начать измерение\"")
                .setPositiveButton("Начать измерение", (DialogInterface dialog, int id) -> mDialogListener. onStartDimensionClick());

        AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null)
        {
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
        }

        dialog.show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        LinearLayout parent = (LinearLayout) positiveButton.getParent();
        parent.setGravity(Gravity.CENTER_HORIZONTAL);
        View leftSpacer = parent.getChildAt(1);
        leftSpacer.setVisibility(View.GONE);

        return dialog;
    }

}
