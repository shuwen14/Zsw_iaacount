package com.example.zsw_iaccount.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.entity.User;
import com.example.zsw_iaccount.login.LoginActivity;
import com.example.zsw_iaccount.util.RegexUtils;
import com.example.zsw_iaccount.util.ToastUtils;
import com.example.zsw_iaccount.util.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class RegisterFragment extends Fragment implements RegisterContract.View{
    private RegisterContract.Presenter mPresenter;

    @BindView(R.id.et_register_phone)
    EditText mEtPhone;
    @BindView(R.id.et_register_email)
    EditText mEtEmail;
    @BindView(R.id.et_register_password)
    EditText mEtPassword;
    @BindView(R.id.et_register_name)
    EditText mEtName;
    @BindView(R.id.et_register_age)
    EditText mEtAge;
    @BindView(R.id.rb_register_sex_boy)
    RadioButton mRbSexBoy;
    @BindView(R.id.rb_register_sex_girl)
    RadioButton mRbSexGirl;
    @BindView(R.id.bt_register)
    Button mRegister;
    private Unbinder unbinder;
    /*private EditText mPhone;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mName;
    private EditText mAge;*/
    private int mSex;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.register_fragment,container,false);
        unbinder= ButterKnife.bind(this,root);
        return root;
    }

/*    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreateFragment(savedInstanceState);
    }

    public void onCreateFragment(Bundle savedInstanceState) {
        *//*mPhone = (EditText) mEtPhone.getText();
        mEmail= (EditText) mEtEmail.getText();
        mPassword = (EditText) mEtPassword.getText();
        mName=(EditText)mEtName.getText();
        mAge=(EditText)mEtAge.getText();*//*
        if(mRbSexBoy.isChecked())
        {
            mSex=1;
        }
        if(mRbSexGirl.isChecked())
        {
            mSex=2;
        }
        if(mRbSexGirl.isChecked()==false&&mRbSexBoy.isChecked()==false)
        {
            mSex=0;
        }
        //判断手机号是否输入错误了
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phone=s.toString();
                if(!RegexUtils.checkPhone(phone)){
                    mEtPhone.setError(UiUtils.getString(R.string.hint_right_phone));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //判断密码是否输入错误了
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password=s.toString();
                if(!RegexUtils.checkPassword(password)){
                    mEtPassword.setError(UiUtils.getString(R.string.hint_right_password));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //判断邮箱是否输入错误了
        mEtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email=s.toString();
                if(!RegexUtils.isEmail(email)){
                    mEtEmail.setError(UiUtils.getString(R.string.hint_right_email));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //判断姓名是否输入错误了
        mEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name=s.toString();
                if(!RegexUtils.isChinese(name)){
                    mEtName.setError(UiUtils.getString(R.string.hint_right_name));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //判断年龄是否输入错误了
        mEtAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String age=s.toString();
                if(!RegexUtils.isPositiveInteger(age)){
                    mEtAge.setError(UiUtils.getString(R.string.hint_right_age));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }*/

    //回收
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /*public void doRegisterUser(View v){
        registerUser();//就是下面的方法
    }*/
    //点击注册按钮触发，检验各种，注册用户
    @OnClick(R.id.bt_register)
    public void doRegisterUser(View v) {
        registerUser();//就是下面的方法
    }

    public void registerUser() {

        //String username=mEtPhone.getText().toString();
        String phone=mEtPhone.getText().toString();
        String email=mEtEmail.getText().toString();
        String password = mEtPassword.getText().toString();
        String name=mEtName.getText().toString();
        int age=Integer.parseInt(mEtAge.getText().toString());
        if(mRbSexBoy.isChecked())
        {
            mSex=1;
        }
        if(mRbSexGirl.isChecked())
        {
            mSex=2;
        }
        if(mRbSexGirl.isChecked()==false&&mRbSexBoy.isChecked()==false)
        {
            mSex=0;
        }
        else
        {
            mSex=999;
        }
        int sex=mSex;

        /*String phone="18211111111";
        String email="18211111111@163.com";
        String password="a11111111";
        int age=11;
        int sex=2;
        String name="赵一";*/
        //获取到user的值
        User user = new User();
        user.setUsername(phone);
        user.setMobilePhoneNumber(phone);
        user.setEmail(email);
        user.setPassword(password);
        user.setAge(age);
        user.setSex(sex);
        user.setName(name);

        if (mPresenter.checkPhoneEmailPasswordInfo(phone,email,password)) {
            //用户信息检查正确的话就调用presenter里的注册方法，然后presenter再去调model里的注册操作
            mPresenter.registerUser(user);//为true，用户信息不为空，则register
        } else {
            ToastUtils.show(getApplicationContext(), UiUtils.getString(R.string.hint_right_phone_or_email_or_password));
        }
    }

    @Override
    public void showRegisterSuccess() {
        ToastUtils.show(getApplicationContext(), UiUtils.getString(R.string.register_success));
        //注册成功转到登录界面
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRegisterFail(Error e) {
        ToastUtils.show(getApplicationContext(),"RegisterFail:"+e.getMessage());
        String phone = mEtPhone.getText().toString().trim();
        if (!RegexUtils.checkPhone(phone)) {
            //如果手机号格式有误，提示一下
            ToastUtils.show(getApplicationContext(), UiUtils.getString(R.string.hint_right_phone));
        } else {
            //如果格式没有误登录失败，就去验证Bmob云数据库中有没有这个手机号,有的话就不能注册了
            mPresenter.verifyPhone(phone);
        }
    }

    @Override
    public void showVerifyPhoneSuccess(Error e) {
        ToastUtils.show(getApplicationContext(), e.getMessage());
    }

    @Override
    public void showVerifyPhoneFail() {
        ToastUtils.show(getApplicationContext(), UiUtils.getString(R.string.exist_phone));
    }
}
