package com.maxpilotto.esame2015.dialogs;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.maxpilotto.esame2015.R;

public class DeleteDialog extends DialogFragment {
    private Callback callback;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.deleteWorkout))
                .setMessage(getString(R.string.deleteWorkoutMsg))
                .setPositiveButton(R.string.yes,(dialog, which) -> {
                    callback.onConfirm();

                    dialog.dismiss();
                })
                .setNegativeButton(R.string.no,(dialog, which) -> {
                    dialog.dismiss();
                })
                .create();
    }

    public DeleteDialog setCallback(Callback callback) {
        this.callback = callback;

        return this;
    }

    public interface Callback {
        void onConfirm();
    }
}
