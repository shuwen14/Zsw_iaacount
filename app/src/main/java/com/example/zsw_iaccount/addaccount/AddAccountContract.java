package com.example.zsw_iaccount.addaccount;

import com.example.zsw_iaccount.base.BasePresenter;
import com.example.zsw_iaccount.base.BaseView;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public interface AddAccountContract {
    interface View extends BaseView<Presenter> {
        void addAccountSuccess();//添加成功
        void addAccountFail(Error e);//添加失败

        /*void updatePhotoSuccess(BmobFile file);
        void updatePhotoFail(Error e);*/
    }
    interface Presenter extends BasePresenter {
        //上传图片
        void updatePhotoAdd(String path, double money, String remark, String date, String author, Boolean inex, String type, int mPay, String addr);
        /*//添加账目
        void addAccount(Double money, String remark, Date date, User author, Boolean inex, String type, int mPay, BmobFile file, String addr);*/
    }
}
