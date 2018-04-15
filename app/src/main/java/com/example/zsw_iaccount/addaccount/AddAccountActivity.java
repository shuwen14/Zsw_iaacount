package com.example.zsw_iaccount.addaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.data.AccountRepository;
import com.example.zsw_iaccount.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class AddAccountActivity extends AppCompatActivity {
    @BindView(R.id.tb_add_account)
    Toolbar mToolbar;
    /*@BindView(R.id.bt_ex)
    Button mEx;
    @BindView(R.id.bt_in)
    Button mIn;*/
    private AddAccountExFragment addAccountExFragment;
    private AddAccountInFragment addAccountInFragment;
    FragmentManager fm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addaccount_activity);
        ButterKnife.bind(this);
        setupToolbar();
        fm = getSupportFragmentManager();

        // 创建Fragment
        //addAccountInFragment = new AddAccountInFragment();
        addAccountExFragment = new AddAccountExFragment();

        // 添加到container
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.add(R.id.fl_add_account, addAccountInFragment);
        transaction.add(R.id.fl_add_account, addAccountExFragment);

        // 默认显示支出
        transaction.show(addAccountExFragment).commit();
    }

    //切换不同的fragment
    @OnClick({R.id.bt_in, R.id.bt_ex})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_ex:
                setTabSelection(0);
                break;
            case R.id.bt_in:
                setTabSelection(1);
                break;
        }
    }
    private void setTabSelection(int index){
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 0:
                if(addAccountExFragment==null){
                    addAccountExFragment = new AddAccountExFragment();
                    ft.add(R.id.fl_add_account, addAccountExFragment);
                }else
                {
                    ft.show(addAccountExFragment);
                }
                //创建Presenter ，fragment作为参数传入，实现联系
                new AddAccountPresenter(new AccountRepository(), addAccountExFragment);
                break;
            case 1:
                if(addAccountInFragment==null){
                    addAccountInFragment = new AddAccountInFragment();
                    ft.add(R.id.fl_add_account, addAccountInFragment);
                }else{
                    ft.show(addAccountInFragment);
                }
                //创建Presenter ，fragment作为参数传入，实现联系
                new AddAccountPresenter(new AccountRepository(), addAccountInFragment);
                break;
        }
        ft.commit();
    }
    //用于隐藏fragment
    private void hideFragment(FragmentTransaction ft){
        if(addAccountInFragment!=null){
            ft.hide(addAccountInFragment);
        }if(addAccountExFragment!=null){
            ft.hide(addAccountExFragment);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddAccountActivity.this, MainActivity.class));
            }
        });
    }
}
