package com.example.zsw_iaccount.register;

import android.text.TextUtils;

import com.example.zsw_iaccount.data.UserDataSource;
import com.example.zsw_iaccount.data.UserRepository;
import com.example.zsw_iaccount.entity.User;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class RegisterPresenter implements  RegisterContract.Presenter{

    private final UserRepository mRepository;
    private final RegisterContract.View mView;

    public RegisterPresenter(UserRepository repository,RegisterContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);//将视图设置为这个presenter，实现联系
    }

    //注册
    public void registerUser(User user){
        mRepository.register(user, new UserDataSource.RegisterCallback() {
            @Override
            public void registerSuccess() {
                mView.showRegisterSuccess();
            }

            @Override
            public void registerFail(Error e) {
                mView.showRegisterFail(e);
            }
        });
    }

    //验证手机号存不存在
    @Override
    public void verifyPhone(String phone) {
        mRepository.verifyPhone(phone, new UserDataSource.VerifyPhoneCallback() {
            @Override
            public void verifySuccess() {
                mView.showVerifyPhoneFail();
            }//因为查到了，所以应该显示失败了

            @Override
            public void verifyFail(Error e) {
                mView.showVerifyPhoneSuccess(e);
            }
        });

    }

    public boolean checkPhoneEmailPasswordInfo(String phone,String email,String password) {
        //检查用户信息是否为空
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)||TextUtils.isEmpty(email)){
            return false;
        }
        return true;
    }

    @Override
    public void start() {

    }
}
