package com.example.zsw_iaccount.common;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by 赵舒文 on 2018-4-11.
 */

public class DialogDatePicker extends DialogFragment {
    public DialogDatePicker() {
    }

    private  DatePickerDialog.OnDateSetListener mListener;

    public void setOnDataSetListener(DatePickerDialog.OnDateSetListener listener){
        mListener=listener;
    }

    public static DialogDatePicker newInstance() {
        DialogDatePicker dialog = new DialogDatePicker();
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK,mListener, year, month, day);
        return dialog;
    }
}
