package com.example.zsw_iaccount.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class User extends BmobUser {
   /* public String phone;//手机号
    public String email;//邮箱号
    public String password;//密码
    public String username;//姓名*/
    public int age = 0;//年龄
    public int sex = 0;//性别：0未设置，1男 ，2女
    public String name;

    /*public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }*/

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String phone) {
        this.name = name;
    }

    public User(){
        super();
    }

    public User(String phone,String email,String password,String username,int age,int sex,String name)
    {
        /*this.phone=phone;
        this.email=email;
        this.password=password;
        this.username=username;*/
        super.setMobilePhoneNumber(phone);
        super.setUsername(username);
        super.setEmail(email);
        super.setPassword(password);
        this.age=age;
        this.sex=sex;
        this.name=name;
    }
}
