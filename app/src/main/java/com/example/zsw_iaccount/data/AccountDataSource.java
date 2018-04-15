package com.example.zsw_iaccount.data;

/**
 * Created by 赵舒文 on 2018-3-25.
 */

public interface AccountDataSource {
    interface ShowInfoCallback{
        void showInfoSuccess(double inMoney,double exMoney,double restMoney);
        void showInfoFail(Error e);
    }

    /*interface UpdatePhotoCallback{
        void updatePhotoSuccess(BmobFile file);
        void updatePhotoFail(Error e);
    }*/

    interface AddAccountCallback{
        void addAccountSuccess();
        void addAccountFail(Error e);
    }
}
