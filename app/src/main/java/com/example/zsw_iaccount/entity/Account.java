package com.example.zsw_iaccount.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 赵舒文 on 2018-3-25.
 */

public class Account extends BmobObject {

    private String author;//这笔账的发起人
    private String type;//消费类型
    private String remark;//备注
    private BmobFile photo;//账单照片
    private int pay;//支付方式：0为现金，1为银行卡，2为支付宝，3为微信，4为财付通，5为其他
    private double money;//金额
    private Boolean inex;//支出还是收入：支出为false，收入为true
    //private BmobGeoPoint address;//地址
    private String spaddress;//精确地址
    private String date;//日期
    /*private Date createdAt;//时间*/

    public Account(){
        super();
    }


    public Account(String author, String type, String remark, BmobFile photo,int pay, double money, Boolean inex,String date,String spaddress) {
        this.author = author;
        this.type = type;
        this.remark = remark;
        this.photo = photo;
        this.pay = pay;
        this.money = money;
        this.inex = inex;
        //this.address = address;
        this.date=date;
        this.spaddress=spaddress;
        /*super.setCreatedAt(createdAt);*/
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BmobFile getPhoto() {
        return photo;
    }

    public void setPhoto(BmobFile photo) {
        this.photo = photo;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Boolean getInex() {
        return inex;
    }

    public void setInex(Boolean inex) {
        this.inex = inex;
    }

   /* public BmobGeoPoint getAddress() {
        return address;
    }

    public void setAddress(BmobGeoPoint address) {
        this.address = address;
    }*/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpaddress() {
        return spaddress;
    }

    public void setSpaddress(String spaddress) {
        this.spaddress = spaddress;
    }
}
