package com.example.zsw_iaccount.main;

import com.example.zsw_iaccount.base.BasePresenter;
import com.example.zsw_iaccount.base.BaseView;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showInfo();//选中spinner的某一月份，显示收入、当月剩余、支出
        void showInfoSuccess(double inMoney,double exMoney,double restMoney);//显示成功
        void showInfoFail(Error e);//显示失败
    }
    interface Presenter extends BasePresenter {
        void showInfo(String year,String month);
    }
}
