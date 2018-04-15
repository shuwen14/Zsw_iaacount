package com.example.zsw_iaccount.register;

import com.example.zsw_iaccount.base.BasePresenter;
import com.example.zsw_iaccount.base.BaseView;
import com.example.zsw_iaccount.entity.User;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void registerUser();
        void showRegisterSuccess();//注册成功
        void showRegisterFail(Error e);//注册失败
        void showVerifyPhoneSuccess(Error e);//手机号未存在
        void showVerifyPhoneFail();//手机号已存在

    }
    interface Presenter extends BasePresenter {
        boolean checkPhoneEmailPasswordInfo(String phone,String email,String password);
        void registerUser(User user);
        void verifyPhone(String phone);
    }
}
