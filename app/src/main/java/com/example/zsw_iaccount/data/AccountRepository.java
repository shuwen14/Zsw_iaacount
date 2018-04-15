package com.example.zsw_iaccount.data;

import com.example.zsw_iaccount.entity.Account;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by 赵舒文 on 2018-3-25.
 */

public class AccountRepository implements AccountDataSource{
        /**
         * 显示spinner选中月的收入支出和剩余
         */
        public void showInfo(String start,String end, final ShowInfoCallback callback) {


            /*BmobQuery<Account> query= new BmobQuery<>();
            List<BmobQuery<Account>> and= new ArrayList<>();
            //大于date时间
            BmobQuery<Account> q1= new BmobQuery<>();
            q1.addWhereGreaterThanOrEqualTo("date",start);
            and.add(q1);
            //小于date1时间
            BmobQuery<Account> q2= new BmobQuery<>();
            q2.addWhereLessThanOrEqualTo("createdAt",new BmobDate(date1));
            and.add(q2);
            //添加复合与查询
            query.and(and);
            query.findObjects(new FindListener<Account>() {
                @Override
                public void done(List<Account> list, BmobException e) {
                    if(e==null){
                        float inMoney = 0,exMoney = 0,restMoney=0;
                        for (Account account:list){
                            if (account.getInex()==false)
                            {
                                exMoney+=account.getMoney();
                            }else if(account.getInex()==true)
                            {
                                inMoney+=account.getMoney();
                            }
                        }
                        restMoney=inMoney-exMoney;
                        callback.showInfoSuccess(inMoney,exMoney,restMoney);
                    }
                    else {
                        callback.showInfoFail(new Error(e));
                    }
                }
            });*/
        }

    /**
     * 上传图片，得到文件完整地址
     */
    public void updatePhoto(String path, double money, String remark, String date, String author, Boolean inex, String type, int mPay, String addr, final AddAccountCallback callback)
    {
        final BmobFile file = new BmobFile(new File(path));
        final Account account = new Account();
        account.setAuthor(author);
        account.setDate(date);
        account.setType(type);
        account.setPhoto(file);
        account.setInex(inex);
        account.setMoney(money);
        account.setPay(mPay);
        account.setRemark(remark);
        account.setSpaddress(addr);

        //上传文件，
        file.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                   /*//bmobFile.getFileUrl()--返回的上传文件的完整地址*//**//*
                    callback.updatePhotoSuccess(file);*/
                    account.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                callback.addAccountSuccess();
                            }else{
                                callback.addAccountFail(new Error(e));
                            }
                        }
                    });
                }else{
                    //callback.updatePhotoFail(new Error(e));
                }

            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }


    /**
     * 添加账目
     */
    /*public void addAccount(Double money, String remark, Date date, String author, Boolean inex, String type, int mPay, BmobFile file, String addr,final AddAccountCallback callback) {
        Account account = new Account();
        account.setAuthor(author);
        account.setDate(date);
        account.setPhoto(file);
        account.setInex(inex);
        account.setMoney(money);
        account.setPay(mPay);
        account.setRemark(remark);
        account.setSpaddress(addr);
        account.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    callback.addAccountSuccess();
                }else{
                    callback.addAccountFail(new Error(e));
                }
            }
        });
    }*/
}
