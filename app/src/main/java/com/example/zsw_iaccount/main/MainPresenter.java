package com.example.zsw_iaccount.main;

import com.example.zsw_iaccount.data.AccountDataSource;
import com.example.zsw_iaccount.data.AccountRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class MainPresenter implements  MainContract.Presenter{

    private final AccountRepository mRepository;
    private final MainContract.View mView;

    public MainPresenter(AccountRepository repository,MainContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);//将视图设置为这个presenter，实现联系
    }

    @Override
    public void start() {

    }

    @Override
    public void showInfo(String year, String month) {
        String start=year+"-"+month+"-01";
        String end;
        if(month.equals("01")==true||month.equals("03")==true||month.equals("05")==true||month.equals("07")==true||month.equals("08")==true||month.equals("10")==true||month.equals("12")==true) {
            end = year + "-" + month + "-31";
        }
        else if (month.equals("04")==true||month.equals("06")==true||month.equals("09")==true||month.equals("11")==true)
        {
            end=year + "-" + month + "-30";
        }
        else if(Integer.parseInt(year)%4==0 &&month.equals("02")==true){
            end=year + "-" + month + "-29";
        }
        else {
            end=year + "-" + month + "-28";
        }
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date  = null;
        try {
            date = sdf.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1  = null;
        try {
            date1 = sdf1.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        mRepository.showInfo(start,end,new AccountDataSource.ShowInfoCallback() {

            @Override
            public void showInfoSuccess(double inMoney, double exMoney, double restMoney) {
                mView.showInfoSuccess(inMoney,exMoney,restMoney);
            }

            @Override
            public void showInfoFail(Error e) {
                mView.showInfoFail(e);
            }
        });
    }
}
