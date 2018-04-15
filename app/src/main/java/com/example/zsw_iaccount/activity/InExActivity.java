package com.example.zsw_iaccount.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.adapter.ListInExAdapter;
import com.example.zsw_iaccount.entity.Account;
import com.example.zsw_iaccount.main.MainActivity;
import com.example.zsw_iaccount.util.ToastUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 赵舒文 on 2018-3-10.
 */

public class InExActivity extends AppCompatActivity {
    /*@BindView(R.id.ex_selectDateShow)
    TextView mDate;
    private Unbinder unbinder;*/
    private int year,month,day;
    private TextView mDate;
    private android.support.v7.widget.Toolbar mToolbar;
    private ListView mListView;
    private List<Account> exAccount;
    private List<Account> inAccount;
    private Boolean mInEx;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inex_activity);
        setupToolbar();
        mListView=(ListView) findViewById(R.id.lv_show);
        //unbinder= ButterKnife.bind(this);
        mButton=findViewById(R.id.inex_doInEx);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Account> query1 = new BmobQuery<>();
                query1.addWhereEqualTo("author", BmobUser.getCurrentUser().getUsername());
                //返回50条数据，如果不加上这条语句，默认返回10条数据
                query1.setLimit(50);
                query1.order("date");
                //执行查询方法
                query1.findObjects(new FindListener<Account>() {
                    @Override
                    public void done(List<Account> object, BmobException e) {
                        if (e == null) {
                            if (object != null && object.size() > 0) {
                                for (Account account : object) {
                                    //ToastUtils.show(getApplicationContext(), mMonth);
                                    if (account.getDate().substring(5, 6).equals("3") && account.getDate().substring(0, 4).equals("2018") && account.getInex() == false) {
                                        exAccount.add(account);
                                    }
                                    if (account.getDate().substring(5, 6).equals("3") && account.getDate().substring(0, 4).equals("2018") && account.getInex() == true) {
                                        inAccount.add(account);
                                    }
                                }
                                if (mInEx == false) {
                                    mListView.setAdapter(new ListInExAdapter(InExActivity.this, exAccount));
                                } else if (mInEx == true) {
                                    mListView.setAdapter(new ListInExAdapter(InExActivity.this, inAccount));
                                }
                            } else {
                                ToastUtils.show(getApplicationContext(), "查询成功，无数据");
                            }
                        } else {
                            ToastUtils.show(getApplicationContext(), "失败：" + e.getMessage() + "," + e.getErrorCode());
                            //Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });
            }
        });
    }

    public void doInEx(View v){

    }

    public void chooseEx(View v){
        mInEx=false;
        ToastUtils.show(getApplicationContext(), "您选择了支出");
    }

    public void chooseIn(View v){
        mInEx=true;
        ToastUtils.show(getApplicationContext(), "您选择了收入");
    }

    private void initView() {

        // 日期
        /*mDate=findViewById(R.id.inex_selectDateShow);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                new YearPickerDialog(getApplicationContext(), AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        mDate.setText(DateUtil.clanderTodatetime(calendar, "yyyy-MM"));

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();
            }
        });*/
    }

    private void setupToolbar() {
        mToolbar=findViewById(R.id.tb_inex_account);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InExActivity.this, MainActivity.class));
            }
        });
    }
}
