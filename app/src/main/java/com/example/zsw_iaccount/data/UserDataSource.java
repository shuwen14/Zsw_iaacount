package com.example.zsw_iaccount.data;

import com.example.zsw_iaccount.entity.User;

/**
 * Created by 赵舒文 on 2018-3-18.
 */

public interface UserDataSource {
    interface LoginCallback{
        void loginSuccess();
        void loginFail(Error e);
    }

    interface VerifyPhoneCallback{
        void verifySuccess();
        void verifyFail(Error e);
    }

    interface RegisterCallback{
        void registerSuccess();
        void registerFail(Error e);
    }
}
