package com.example.zsw_iaccount;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zsw_iaccount.entity.User;
import com.example.zsw_iaccount.login.LoginActivity;
import com.example.zsw_iaccount.main.MainActivity;
import com.example.zsw_iaccount.util.SharePreference;
import com.example.zsw_iaccount.util.ToastUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

import static cn.bmob.v3.Bmob.getApplicationContext;

//欢迎页
public class SplashOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_one);
        //Bmob初始化
        Bmob.initialize(this,"83b2c7474e0490f3e7616c87602918cc");
        //判断是否第一次登录,跳转对应页面
        isLogin();
    }

    private void isLogin() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                //判断需不需要登录,判断用户是否已经登录
                BmobUser bmobUser=BmobUser.getCurrentUser();
                if (bmobUser!=null){
                    //ToastUtils.show(getApplicationContext(), "已有用户");
                    //允许用户使用应用
                    intent = new Intent(SplashOneActivity.this, MainActivity.class);
                } else {
                    //缓存用户对象为空，打开用户登录界面
                    intent = new Intent(SplashOneActivity.this, LoginActivity.class);
                    //startActivity(intent);
                }
                startActivity(intent);
                finish();
            }
            //等待时间
        }, 1500);
    }

    /*private void isLogin() {
        Intent intent;
        boolean isLoginFlag;
        SharePreference sp =new SharePreference(this);

        isLoginFlag= sp.getState();
        if(isLoginFlag){
            intent = new Intent(this,LoginActivity.class);
        }
        else {
            sp.setState();//将登陆状态设置为true，表示已经登录过。
            intent = new Intent(this,MainActivity.class);
        }
    }*/
}
