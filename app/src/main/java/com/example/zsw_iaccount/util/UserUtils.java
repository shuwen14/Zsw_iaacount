package com.example.zsw_iaccount.util;

import com.example.zsw_iaccount.entity.User;

import cn.bmob.v3.BmobUser;

/**
 * Created by 赵舒文 on 2018-3-20.
 */

public class UserUtils {
    private UserUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 校验用户是否已经登录。
     * @return true: 已登录 false: 未登录
     */
    public static boolean checkLogin(){
        return null != BmobUser.getCurrentUser(User.class);
    }

    /**
     * 获取当前登录 User 对象。
     * @return User 对象
     */
    public static User getUser(){
        return BmobUser.getCurrentUser(User.class);
    }

}
