package com.example.zsw_iaccount.data;

import android.content.Intent;
import android.widget.Toast;

import com.example.zsw_iaccount.entity.User;
import com.example.zsw_iaccount.login.LoginActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class UserRepository implements UserDataSource{
    /**
     * 登录
     *
     * @param user
     * @param callback
     */
    public void login(User user, final LoginCallback callback) {
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    //成功
                    callback.loginSuccess();
                } else {
                    //失败
                    callback.loginFail(new Error(e));
                }
            }
        });
    }

    /**
     * 验证手机号
     *
     * @param phone
     * @param callback
     */
    public void verifyPhone(String phone, final VerifyPhoneCallback callback) {
        BmobQuery<User> query=new BmobQuery<User>();
        query.addWhereEqualTo("username",phone);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e==null)
                {//查到了 就回调验证成功
                    callback.verifySuccess();
                }
                else
                {//查不到 就回调验证失败
                    callback.verifyFail(new Error(e));
                }
            }
        });
    }

    /**
     * 注册
     *
     * @param user
     * @param callback
     */
    public void register(User user, final RegisterCallback callback) {
        user.signUp(new SaveListener<User>() {

            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    callback.registerSuccess();
                }else{
                    callback.registerFail(new Error(e));
                }
            }
        });
    }
}
