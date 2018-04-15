package com.example.zsw_iaccount.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.activity.ForgetPasswordActivity;
import com.example.zsw_iaccount.data.UserRepository;
import com.example.zsw_iaccount.login.LoginActivity;
import com.example.zsw_iaccount.util.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.zsw_iaccount.R.id.fl_register;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.tv_ReturnLogin)
    TextView mtv_ReturnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        //创建View
        RegisterFragment fragment=(RegisterFragment) getSupportFragmentManager().findFragmentById(fl_register);
        //如果没有就实例化一个加上去
        if (fragment == null) {
            fragment=new RegisterFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, fl_register);
        }
        //创建Presenter
        new RegisterPresenter(new UserRepository(), fragment);

        /*mtv_ReturnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/
    }

    public void doReturnLogin(View v){
        Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
