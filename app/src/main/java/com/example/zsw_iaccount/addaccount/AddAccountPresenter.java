package com.example.zsw_iaccount.addaccount;

import com.example.zsw_iaccount.data.AccountDataSource;
import com.example.zsw_iaccount.data.AccountRepository;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class AddAccountPresenter implements  AddAccountContract.Presenter{

    private final AccountRepository mRepository;
    private final AddAccountContract.View mView;

    public AddAccountPresenter(AccountRepository repository,AddAccountContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);//将视图设置为这个presenter，实现联系
    }

    @Override
    public void start() {

    }

    @Override
    public void updatePhotoAdd(String path, double money, String remark, String date, String author, Boolean inex, String type, int mPay, String addr) {
        mRepository.updatePhoto(path,money,remark,date,author,inex,type,mPay,addr, new AccountDataSource.AddAccountCallback() {
            @Override
            public void addAccountSuccess() {
                mView.addAccountSuccess();
            }

            @Override
            public void addAccountFail(Error e) {
                mView.addAccountFail(e);
            }
        });
    }


   /* @Override
    public void addAccount(Double money, String remark, Date date, User author, Boolean inex, String type, int mPay, BmobFile file, String addr) {
        mRepository.addAccount(money, remark, date, author, inex, type, mPay, file, addr, new AccountDataSource.AddAccountCallback() {
            @Override
            public void addAccountSuccess() {
                mView.addAccountSuccess();
            }

            @Override
            public void addAccountFail(Error e) {
                mView.addAccountFail(e);
            }
        });
    }*/
}
