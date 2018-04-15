package com.example.zsw_iaccount.login;

import com.example.zsw_iaccount.base.BaseView;
import com.example.zsw_iaccount.base.BasePresenter;
import com.example.zsw_iaccount.entity.User;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void login();//获取user并登录
        void showLoginSuccess();//登录成功
        void showLoginFail(Error e);//登录失败
        void showVerifyPhoneSuccess();//验证Phone成功,查是否有这个phone号码存在Bmob云数据库中
        void showVerifyPhoneFail(Error e);//验证Phone失败
    }
    interface Presenter extends BasePresenter{
        boolean checkUserInfo(String phone,String password);
        void login(User user);
        void verifyPhone(String phone);
    }
}
