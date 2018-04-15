package com.example.zsw_iaccount.login;

import android.text.TextUtils;

import com.example.zsw_iaccount.data.UserDataSource;
import com.example.zsw_iaccount.data.UserRepository;
import com.example.zsw_iaccount.entity.User;
import com.example.zsw_iaccount.util.ToastUtils;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class LoginPresenter implements  LoginContract.Presenter{

    private final UserRepository mRepository;
    private final LoginContract.View mView;

    public LoginPresenter(UserRepository repository,LoginContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);//将视图设置为这个presenter，实现联系
    }

    //登录
    public void login(User user){
        mRepository.login(user, new UserDataSource.LoginCallback() {
            @Override
            public void loginSuccess() {
                mView.showLoginSuccess();
            }

            @Override
            public void loginFail(Error e) {
                mView.showLoginFail(e);
            }
        });
    }

    //验证手机号存不存在
    @Override
    public void verifyPhone(String phone) {
        mRepository.verifyPhone(phone, new UserDataSource.VerifyPhoneCallback() {
            @Override
            public void verifySuccess() {
                mView.showVerifyPhoneSuccess();
            }

            @Override
            public void verifyFail(Error e) {
                mView.showVerifyPhoneFail(e);
            }
        });

    }

    public boolean checkUserInfo(String phone,String password) {
        //检查用户信息是否为空
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)){
            return false;
        }
        return true;
    }

    @Override
    public void start() {

    }
}
