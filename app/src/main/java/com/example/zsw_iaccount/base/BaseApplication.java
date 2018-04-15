package com.example.zsw_iaccount.base;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by 赵舒文 on 2018-3-19.
 */

public class BaseApplication extends Application {

    public  String APP_ID="83b2c7474e0490f3e7616c87602918cc";
    public  String APP_KEY="797eca014903fb4536e1cd773e686795";

    public static BaseApplication mInstance;

    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = new BaseApplication();
        
        initBmob();
    }

    //初始化Bmob
    private void initBmob() {
        Bmob.initialize(this,"83b2c7474e0490f3e7616c87602918cc");
    }
}
