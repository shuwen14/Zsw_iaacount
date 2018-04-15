package com.example.zsw_iaccount.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.addaccount.AddAccountActivity;
import com.example.zsw_iaccount.entity.Account;
import com.example.zsw_iaccount.util.TimeUtils;
import com.example.zsw_iaccount.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class MainFragment extends Fragment implements MainContract.View{

    private MainContract.Presenter mPresenter;

    @BindView(R.id.sp_select_month)
    Spinner mSpinner;
    @BindView(R.id.tv_in)
    TextView mtv_in;
    @BindView(R.id.tv_balance)
    TextView mtv_balance;
    @BindView(R.id.tv_ex)
    TextView mtv_ex;
    private Unbinder unbinder;

    private String mYear;
    private String mMonth;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    //获取月份
    private void setupSpinner() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] months = getResources().getStringArray(R.array.month);

                mMonth=months[pos].toString().substring(1,2);
                showInfo();//选中spinner的某一月份，显示收入、当月剩余、支出
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @OnClick(R.id.ib_doaddaccount)
    public void doAddAccount(View v){
        Intent intent=new Intent(getApplicationContext(), AddAccountActivity.class);
        startActivity(intent);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment,container,false);
        unbinder= ButterKnife.bind(this,root);
        setupSpinner();
        return root;
    }

    //回收
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void showInfo(){
        if(mMonth!=null)
        {
            mYear=TimeUtils.getYear();
            //mPresenter.showInfo(mYear,mMonth);
            //ToastUtils.show(getApplicationContext(), BmobUser.getCurrentUser().getUsername());
            BmobQuery<Account> query = new BmobQuery<>();
            query.addWhereEqualTo("author", BmobUser.getCurrentUser().getUsername());
            //返回50条数据，如果不加上这条语句，默认返回10条数据
            query.setLimit(50);
            query.order("date");
            //执行查询方法
            query.findObjects(new FindListener<Account>() {
                @Override
                public void done(List<Account> object, BmobException e) {
                    if(e==null){
                        if(object!=null&&object.size()>0) {
                            double exMoney = 0, inMoney = 0, restMoney;
                            for (Account account : object) {
                                //ToastUtils.show(getApplicationContext(), mMonth);
                                if (account.getDate().substring(5,6).equals(mMonth) && account.getDate().substring(0, 4).equals(mYear) && account.getInex() == false) {
                                    exMoney = exMoney + account.getMoney();
                                }
                                if (account.getDate().substring(5,6).equals(mMonth) && account.getDate().substring(0, 4).equals(mYear) && account.getInex() == true) {
                                    inMoney = inMoney + account.getMoney();
                                }
                            }
                            restMoney = inMoney - exMoney;
                            mtv_in.setText(String.valueOf(inMoney));
                            mtv_ex.setText(String.valueOf(exMoney));
                            mtv_balance.setText(String.valueOf(restMoney));
                        }else {
                            ToastUtils.show(getApplicationContext(), "查询成功，无数据");
                        }
                    }else{
                        ToastUtils.show(getApplicationContext(), "失败："+e.getMessage()+","+e.getErrorCode());
                        //Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    }
                }
            });
        }
    }

    @Override
    public void showInfoSuccess(double inMoney, double exMoney, double restMoney) {
        mtv_in.setText(String.valueOf(inMoney));
        mtv_ex.setText(String.valueOf(exMoney));
        mtv_balance.setText(String.valueOf(restMoney));
    }

    @Override
    public void showInfoFail(Error e) {
        ToastUtils.show(getApplicationContext(), e.getMessage());
    }

}
