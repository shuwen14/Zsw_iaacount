package com.example.zsw_iaccount.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.activity.AboutAppActivity;
import com.example.zsw_iaccount.activity.ExAccountActivity;
import com.example.zsw_iaccount.activity.ForgetPasswordActivity;
import com.example.zsw_iaccount.activity.InExActivity;
import com.example.zsw_iaccount.activity.RemindActivity;
import com.example.zsw_iaccount.activity.UserInfoActivity;
import com.example.zsw_iaccount.addaccount.AddAccountActivity;
import com.example.zsw_iaccount.data.AccountRepository;
import com.example.zsw_iaccount.login.LoginActivity;
import com.example.zsw_iaccount.queryaccount.QueryAccountActivity;
import com.example.zsw_iaccount.statistic.StatisticActivity;
import com.example.zsw_iaccount.util.ActivityUtils;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.nav_user)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    @BindView(R.id.tb_account)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setupToolbar();
        setupBottomBar();
        setupDrawerLayout();

        //创建View
        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fl_account);
        //如果没有就实例化一个加上去
        if (fragment == null) {
            fragment = new MainFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.fl_account);
        }
        //创建Presenter LoginPresenter，fragment作为参数传入，实现联系
        new MainPresenter(new AccountRepository(), fragment);


    }



    private void setupToolbar() {
        mToolbar.setTitle(" ");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_user);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //设置侧边栏
    private void setupDrawerLayout() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dn_menu_remind:
                        startActivity(new Intent(MainActivity.this, RemindActivity.class));
                        break;
                    case R.id.dn_menu_modifyPassword:
                        startActivity(new Intent(MainActivity.this, ForgetPasswordActivity.class));
                        break;
                    case R.id.dn_menu_exAccountBook:
                        startActivity(new Intent(MainActivity.this, ExAccountActivity.class));
                        break;
                    case R.id.dn_menu_userInfo:
                        startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
                        break;
                    case R.id.dn_menu_aboutApp:
                        startActivity(new Intent(MainActivity.this, AboutAppActivity.class));
                        break;
                    case R.id.dn_menu_exitUser:
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    //设置底部栏
    private void setupBottomBar() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                /*if (tabId == R.id.bb_menu_addAccount) {
                    startActivity(new Intent(MainActivity.this, AddAccountActivity.class));
                }
                if (tabId == R.id.bb_menu_queryAccount) {
                    startActivity(new Intent(MainActivity.this, QueryAccountActivity.class));
                }
                if (tabId == R.id.bb_menu_inex) {
                    startActivity(new Intent(MainActivity.this, InExActivity.class));
                }
                if (tabId == R.id.bb_menu_statistic) {
                    startActivity(new Intent(MainActivity.this, StatisticActivity.class));
                }*/
               switch (tabId){
                   case R.id.bb_menu_addAccount:
                       startActivity(new Intent(MainActivity.this, AddAccountActivity.class));
                       break;
                   case R.id.bb_menu_queryAccount:
                       startActivity(new Intent(MainActivity.this, QueryAccountActivity.class));
                       break;
                   case R.id.bb_menu_inex:
                       startActivity(new Intent(MainActivity.this, InExActivity.class));
                       break;
                   case R.id.bb_menu_statistic:
                       startActivity(new Intent(MainActivity.this, StatisticActivity.class));
                       break;
                   default:
                       break;
               }
            }
        });

        /*mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                *//*if (tabId == R.id.tab_favorites) {
                    // 已经选择了这个标签，又点击一次。即重选。
                    nearby.removeBadge();}*//*
            }
        });*/
    }
}