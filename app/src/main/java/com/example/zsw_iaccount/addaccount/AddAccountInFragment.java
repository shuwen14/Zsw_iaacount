package com.example.zsw_iaccount.addaccount;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.activity.AddAccountPhotoActivity;
import com.example.zsw_iaccount.common.DialogAdd;
import com.example.zsw_iaccount.common.DialogDatePicker;
import com.example.zsw_iaccount.entity.Account;
import com.example.zsw_iaccount.util.ToastUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.app.Activity.RESULT_OK;
import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by 赵舒文 on 2018-4-8.
 */

public class AddAccountInFragment extends Fragment implements AddAccountContract.View{
    private AddAccountContract.Presenter mPresenter;
    @BindView(R.id.in_sp_select_pay)
    Spinner mSpinner;
    @BindView(R.id.in_et_remark)
    EditText mRemark;
    @BindView(R.id.in_selectDateShow)
    TextView mDate;
    @BindView(R.id.in_et_money)
    EditText mMoney;
    @BindView(R.id.in_et_address)
    EditText mAddress;

    final public int CODE= 0x13A;		//定义一个请求码常量
    final public int CODE2= 0x14A;		//定义一个请求码常量
    private int year,month,day;
    private String pay;
    private int mPay;
    private double money;
    private String remark;
    private String date;
    private String author;
    private String type;
    private Boolean inex;
    private String path;//图片地址
    private Unbinder unbinder;
    private String addr;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //获取支付方式pay
    public void setupSpinner() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] pays = getResources().getStringArray(R.array.pay);

                pay=pays[pos].toString();
                if(pay.equals("现金"))
                    mPay=0;
                else if(pay.equals("银行卡"))
                    mPay=1;
                else if(pay.equals("支付宝"))
                    mPay=2;
                else if(pay.equals("微信"))
                    mPay=3;
                else if(pay.equals("财付通"))
                    mPay=4;
                else if(pay.equals("其他"))
                    mPay=5;
                else
                    mPay=999;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.addaccount_in_fragment,container,false);
        unbinder= ButterKnife.bind(this,root);
        setupSpinner();
        initView();
        return root;
    }

    private void initView() {
        // 日期
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDatePicker dialogDatePicker=DialogDatePicker.newInstance();
                dialogDatePicker.setOnDataSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mDate.setText(year+"-"+(++month)+"-"+day);
                    }
                });
                dialogDatePicker.show(getFragmentManager(),"");
            }
        });

        //记账用户
        author = BmobUser.getCurrentUser().getUsername();
        //支出还是收入
        inex=true;
        //消费类型 已定好
        //支付方式 已定好

    }

    //回收
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(AddAccountContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick({R.id.in_bt_photo, R.id.in_bt_reset,R.id.in_bt_add,R.id.in_bt_address,R.id.bt_normal, R.id.bt_baoxiao,R.id.bt_gongzi,
            R.id.bt_hongbao,R.id.bt_jianzhi,R.id.bt_jiangjin,R.id.bt_touzi,
            R.id.bt_jiangxuejin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.in_bt_photo:
                doPhoto();//上传照片
                break;
            case R.id.in_bt_reset:
                doReset();//全部清零
                break;
            case R.id.in_bt_add:
                doAdd();//添加账目
                break;
            case R.id.ex_bt_address:
                doAddress();
                break;
            case R.id.bt_normal:
                type="一般";
                ToastUtils.show(getApplicationContext(), "您选择了一般");
                break;
            case R.id.bt_baoxiao:
                type="报销";
                ToastUtils.show(getApplicationContext(), "您选择了报销");
                break;
            case R.id.bt_gongzi:
                type="工资";
                ToastUtils.show(getApplicationContext(), "您选择了工资");
                break;
            case R.id.bt_hongbao:
                type="红包";
                ToastUtils.show(getApplicationContext(), "您选择了红包");
                break;
            case R.id.bt_jianzhi:
                type="兼职";
                ToastUtils.show(getApplicationContext(), "您选择了兼职");
                break;
            case R.id.bt_jiangjin:
                type="奖金";
                ToastUtils.show(getApplicationContext(), "您选择了奖金");
                break;
            case R.id.bt_touzi:
                type="投资";
                ToastUtils.show(getApplicationContext(), "您选择了投资");
                break;
            case R.id.bt_jiangxuejin:
                type="奖学金";
                ToastUtils.show(getApplicationContext(), "您选择了奖学金");
                break;
        }
    }

    private void doAddress() {
    }

    public void doPhoto() {
        Intent intent=new Intent(getApplicationContext(), AddAccountPhotoActivity.class);
        intent.putExtra("code", String.valueOf(CODE));
        startActivityForResult(intent,CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //相机
        if(requestCode == CODE && resultCode == RESULT_OK){
            path= data.getStringExtra("path");
        }
        //百度地图
        if(requestCode == CODE2 && resultCode == RESULT_OK){
            addr=data.getStringExtra("addr");
            //path= data.getStringExtra("path");
        }
    }

    public void doAdd() {
        /*//金额
        money=Double.parseDouble(mMoney.getText().toString());
        //备注
        remark=mRemark.getText().toString();
        //日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date=sdf.parse(mDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //记账用户
        author = BmobUser.getCurrentUser().getUsername();
        //支出还是收入
        inex=false;
        //消费类型 已定好
        //支付方式 已定好
        //地址
        addr=mAddress.getText().toString();*/

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date=sdf.parse(mDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date=sdf.parse("2018-03-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        //date=new Date(mDate.getText().toString());
        date=mDate.getText().toString();
        //金额
        money=Double.parseDouble(mMoney.getText().toString());
        //money=Double.valueOf(mMoney.getText().toString());
        //备注
        remark=mRemark.getText().toString();
        //地址
        addr=mAddress.getText().toString();
        //ToastUtils.show(getApplicationContext(), "图片路径："+path+"金额："+money+"备注："+remark+"日期："+date+"作者："+author+"支出或收入："+inex+"类型："+type+"支付方式："+mPay+"地址："+addr);
        //ToastUtils.show(getApplicationContext(), path+money+remark+date+author+inex+type+mPay+addr);
        //图片地址 已定好,上传图片
        //mPresenter.updatePhotoAdd(path,money,remark,date,author,inex,type,mPay,addr);
        final BmobFile file = new BmobFile(new File(path));
        //上传文件，
        file.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                   /*//bmobFile.getFileUrl()--返回的上传文件的完整地址*//**//*
                    callback.updatePhotoSuccess(file);*/
                    AddAccount(file);

                }else{
                    //callback.updatePhotoFail(new Error(e));
                }

            }

            private void AddAccount(BmobFile bfile) {
                Account account = new Account();
                account.setAuthor(author);
                account.setDate(date);
                account.setType(type);
                account.setPhoto(bfile);
                account.setInex(inex);
                account.setMoney(money);
                account.setPay(mPay);
                account.setRemark(remark);
                account.setSpaddress(addr);
                account.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            //ToastUtils.show(getApplicationContext(), "添加该账目成功");
                            DialogAdd dialogAdd = new DialogAdd();
                            dialogAdd.show("结果：", "添加该账目成功", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "返回 " + which, Toast.LENGTH_SHORT).show();
                                }
                            }, getFragmentManager());
                        }else{
                            //ToastUtils.show(getApplicationContext(), e.getMessage());
                            DialogAdd dialogAdd = new DialogAdd();
                            dialogAdd.show("结果：", "添加该账目失败"+e.getMessage(), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "返回 " + which, Toast.LENGTH_SHORT).show();
                                }
                            }, getFragmentManager());
                        }
                    }
                });
            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }

    private void doReset() {
        mMoney.setText("");
        mRemark.setText("");
        mDate.setText("选择日期");
        mAddress.setText("");
        type="";
        path="";
        money=999;
        remark="";
        addr="";
        DialogAdd dialogAdd = new DialogAdd();
        dialogAdd.show("结果：", "已清空，继续记录您的时光帐吧~", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "返回 " + which, Toast.LENGTH_SHORT).show();
            }
        }, getFragmentManager());
    }

    @Override
    public void addAccountSuccess() {
        ToastUtils.show(getApplicationContext(), "添加该账目成功");
        //doReset();
    }

    @Override
    public void addAccountFail(Error e) {
        ToastUtils.show(getApplicationContext(), e.getMessage());
    }


}
