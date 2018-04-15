package com.example.zsw_iaccount.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.example.zsw_iaccount.entity.Account;

import java.util.List;

import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * Created by 赵舒文 on 2018-4-12.
 */

public class DialogAddress extends DialogFragment {
    private String title;
    private String[] address;
    private DialogInterface.OnClickListener onClickListener;

    public void show(String title, String[] address, DialogInterface.OnClickListener onClickListener,
                     FragmentManager fragmentManager) {
        this.title = title;
        this.address = address;
        this.onClickListener = onClickListener;
        show(fragmentManager, "DialogAddress");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setItems(address,onClickListener);
        return builder.create();
    }
}
