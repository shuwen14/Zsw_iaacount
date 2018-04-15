package com.example.zsw_iaccount.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.entity.User;
import com.example.zsw_iaccount.main.MainActivity;
import com.example.zsw_iaccount.util.RegexUtils;
import com.example.zsw_iaccount.util.ToastUtils;
import com.example.zsw_iaccount.util.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class LoginFragment extends Fragment implements LoginContract.View{

        private LoginContract.Presenter mPresenter;

        @BindView(R.id.et_register_phone)
        EditText mEtPhone;
        @BindView(R.id.et_register_password)
        EditText mEtPassword;
        @BindView(R.id.bt_login)
        Button mlogin;
        private Unbinder unbinder;
/*    private EditText mPhone;
    private EditText mPassword;*/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment,container,false);
        unbinder= ButterKnife.bind(this,root);
        return root;
    }

/*    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreateFragment(savedInstanceState);
    }

    public void onCreateFragment(Bundle savedInstanceState) {
      *//*  mPhone =(EditText) mEtPhone.getText();
        mPassword = (EditText) mEtPassword.getText();*//*
        //if(mPassword==null) return;

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
    }*/

    //回收
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }


  /*  public void doLogin(View v){
        login();//就是下面的login方法
    }*/
  //点击登录按钮触发，检验各种，成功后转入主界面
    @OnClick(R.id.bt_login)
    public void doLogin(View v) {
        login();//就是下面的login方法
    }

    public void login() {
        String phone=mEtPhone.getText().toString();
        String password = mEtPassword.getText().toString();


        if (mPresenter.checkUserInfo(phone,password)) {
            //获取到user的值
            User user = new User();
            user.setUsername(phone);
            user.setPassword(password);
            Log.d("sween","sween");
            //用户信息检查正确的话就调用presenter里的登录方法，然后presenter再去调model里的登录操作
            mPresenter.login(user);//为true，用户信息不为空，则login
        } else {
            ToastUtils.show(getApplicationContext(), UiUtils.getString(R.string.hint_right_phone_or_password));
        }
    }

    @Override
    public void showLoginSuccess() {
        ToastUtils.show(getApplicationContext(), UiUtils.getString(R.string.login_success));
        /*Log.d("sween","sween");*/
        //获取自定义用户信息 获取登录成功后的本地用户信息
        //User localUser = BmobUser.getCurrentUser(User.class);
        //登录成功转到主界面
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        //intent.putExtra("user",localUser);
        startActivity(intent);
    }

    @Override
    public void showLoginFail(Error e) {
        ToastUtils.show(getApplicationContext(),"loginFail:"+e.getMessage());
        String phone = mEtPhone.getText().toString().trim();
        if (!RegexUtils.checkPhone(phone)) {
            //如果手机号格式有误，提示一下
            ToastUtils.show(getApplicationContext(), UiUtils.getString(R.string.hint_right_phone));
        } else {
            //如果格式没有误登录失败，就去验证Bmob云数据库中有没有这个手机号
            mPresenter.verifyPhone(phone);
        }
    }

    @Override
    public void showVerifyPhoneSuccess() {
        ToastUtils.show(getApplicationContext(), UiUtils.getString(R.string.toast_verify_phone_success));
    }

    @Override
    public void showVerifyPhoneFail(Error e) {
        ToastUtils.show(getApplicationContext(), e.getMessage());
    }
}
