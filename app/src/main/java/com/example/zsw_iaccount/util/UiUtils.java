package com.example.zsw_iaccount.util;

import android.content.Context;
import android.content.res.Resources;

import com.example.zsw_iaccount.base.BaseApplication;

/**
 * Created by 赵舒文 on 2018-3-19.
 */

public class UiUtils {
    /**
     * 获取上下文
     */
    public static Context getContext() {
        return BaseApplication.getContext();
    }
    /**
     * 获取资源操作类
     */
    public static Resources getResources() {
        return getContext().getResources();
    }
    /**
     * 获取字符串资源
     *
     * @param id 资源id
     * @return 字符串
     */
    public static String getString(int id) {
        return getResources().getString(id);
    }
}
