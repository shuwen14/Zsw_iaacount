package com.example.zsw_iaccount.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by 赵舒文 on 2018-4-15.
 */

public class DialogAdd extends DialogFragment {

    private DialogInterface.OnClickListener positiveCallback;

    private String title;

    private String message;

    public void show(String title, String message, DialogInterface.OnClickListener positiveCallback,
                     FragmentManager fragmentManager) {
        this.title = title;
        this.message = message;
        this.positiveCallback = positiveCallback;
        show(fragmentManager, "ButtonDialogFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", positiveCallback);
        return builder.create();
    }

}
