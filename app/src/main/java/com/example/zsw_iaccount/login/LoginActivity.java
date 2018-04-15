package com.example.zsw_iaccount.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.activity.ForgetPasswordActivity;
import com.example.zsw_iaccount.data.UserRepository;
import com.example.zsw_iaccount.register.RegisterActivity;
import com.example.zsw_iaccount.util.ActivityUtils;

import butterknife.BindView;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //创建View LoginFragment
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fl_login);
        //如果没有就实例化一个加上去
        if (fragment == null) {
            fragment=new LoginFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.fl_login);
        }
        //创建Presenter LoginPresenter，fragment作为参数传入，实现联系
        new LoginPresenter(new UserRepository(), fragment);
    }

    //转到忘记密码
    public void doForgetPassword(View v){
        Intent intent=new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    //转到注册
    public void doRegister(View v){
        Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


}
