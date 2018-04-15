package com.example.zsw_iaccount.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class ActivityUtils {

    //把Fragment加到Activity上
    public static void addFragmentToActivity (android.support.v4.app.FragmentManager fragmentManager, android.support.v4.app.Fragment fragment,int framId) {
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(framId, fragment);
        transaction.commit();
    }
}
