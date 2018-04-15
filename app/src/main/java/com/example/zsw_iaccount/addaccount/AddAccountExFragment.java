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
 * Created by 赵舒文 on 2018-3-10.
 */

public class AddAccountExFragment extends Fragment implements AddAccountContract.View{

    private AddAccountContract.Presenter mPresenter;

    @BindView(R.id.ex_sp_select_pay)
    Spinner mSpinner;
    @BindView(R.id.ex_et_remark)
    EditText mRemark;
    @BindView(R.id.ex_selectDateShow)
    TextView mDate;
    @BindView(R.id.ex_et_money)
    EditText mMoney;
    @BindView(R.id.ex_et_address)
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
        View root = inflater.inflate(R.layout.addaccount_ex_fragment,container,false);
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
        inex=false;
        //消费类型 已定好
        //支付方式 已定好

    }

    /*private void initDate() {
        //获取当前日期
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }*/

    //回收
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(AddAccountContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick({R.id.ex_bt_photo, R.id.ex_bt_reset,R.id.ex_bt_add,R.id.ex_bt_address,R.id.bt_yiban, R.id.bt_fushi,R.id.bt_yongcan,
            R.id.bt_jiaotong,R.id.bt_tongxun,R.id.bt_renqing,R.id.bt_yiliao,
            R.id.bt_xuexi,R.id.bt_riyongpin,R.id.bt_lingshi,R.id.bt_yule,R.id.bt_meizhuang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ex_bt_photo:
                doPhoto();//上传照片
                break;
            case R.id.ex_bt_reset:
                doReset();//全部清零
                break;
            case R.id.ex_bt_add:
                doAdd();//添加账目
                break;
            case R.id.ex_bt_address:
                doAddress();
                break;
            case R.id.bt_yiban:
                type="一般";
                ToastUtils.show(getApplicationContext(), "您选择了一般");
                break;
            case R.id.bt_fushi:
                type="服饰";
                ToastUtils.show(getApplicationContext(), "您选择了服饰");
                break;
            case R.id.bt_yongcan:
                type="用餐";
                ToastUtils.show(getApplicationContext(), "您选择了用餐");
                break;
            case R.id.bt_jiaotong:
                type="交通";
                ToastUtils.show(getApplicationContext(), "您选择了交通");
                break;
            case R.id.bt_tongxun:
                type="通讯";
                ToastUtils.show(getApplicationContext(), "您选择了通讯");
                break;
            case R.id.bt_renqing:
                type="人情";
                ToastUtils.show(getApplicationContext(), "您选择了人情");
                break;
            case R.id.bt_yiliao:
                type="医疗";
                ToastUtils.show(getApplicationContext(), "您选择了医疗");
                break;
            case R.id.bt_xuexi:
                type="学习";
                ToastUtils.show(getApplicationContext(), "您选择了学习");
                break;
            case R.id.bt_riyongpin:
                type="日用品";
                ToastUtils.show(getApplicationContext(), "您选择了日用品");
                break;
            case R.id.bt_lingshi:
                type="零食";
                ToastUtils.show(getApplicationContext(), "您选择了零食");
                break;
            case R.id.bt_yule:
                type="娱乐";
                ToastUtils.show(getApplicationContext(), "您选择了娱乐");
                break;
            case R.id.bt_meizhuang:
                type="美妆";
                ToastUtils.show(getApplicationContext(), "您选择了美妆");
                break;
        }
    }


    /*public void doDate() {
        DialogDatePicker dialogDatePicker=DialogDatePicker.newInstance();
        dialogDatePicker.setOnDataSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mDate.setText(year+"-"+(++month)+"-"+day);
            }
        });
        dialogDatePicker.show(getFragmentManager(),"");



        *//*DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                mDate.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
            }
        };
        DatePickerDialog dialog=new DatePickerDialog(getApplicationContext(), 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
        dialog.show();
        *//*final Calendar c = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                mDate.setText(DateFormat.format("yyyy-MM-dd", c));
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();*//*
    }*/

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

    public void doReset() {
        //ToastUtils.show(getApplicationContext(), mRemark.getText().toString());
        //ToastUtils.show(getApplicationContext(), "图片路径："+path+"金额："+money+"备注："+remark+"日期："+date+"作者："+author+"支出或收入："+inex+"类型："+type+"支付方式："+mPay+"地址："+mAddress.getText().toString());
       // ToastUtils.show(getApplicationContext(), "图片路径："+path+"金额："+Double.parseDouble(mMoney.getText().toString())+"备注："+mRemark.getText().toString()+"日期："+date+"作者："+BmobUser.getCurrentUser(User.class)+"支出或收入："+false+"类型："+type+"支付方式："+mPay+"地址："+mAddress.getText().toString());

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
        dialogAdd.show("结果：", "已清空，继续记录您的时光帐吧！", new DialogInterface.OnClickListener() {
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

    /*@Override
    public void updatePhotoSuccess(BmobFile file) {
        bmobfile =file;
        ToastUtils.show(getApplicationContext(), "上传成功");
    }

    @Override
    public void updatePhotoFail(Error e) {
        ToastUtils.show(getApplicationContext(), e.getMessage());
    }*/



    /*private String[] doSelectAddress() {
        final String[] address=new String[10];
        BmobQuery<Account> bmobQuery = new BmobQuery<Account>();
        bmobQuery.addWhereNear("gpsAdd", new BmobGeoPoint(112.934755, 24.52065));
        bmobQuery.setLimit(10);    //获取最接近用户地点的10条数据
        bmobQuery.findObjects(new FindListener<Account>() {
            @Override
            public void done(List<Account> object, BmobException e) {
                int i=0;
                if (e == null) {
                    for (Account account : object) {
                        //获得地址信息
                        address[i++]=account.getSpaddress();
                    }
                } else {
                    ToastUtils.show(getApplicationContext(),"查询失败：" + e.getMessage() );
                }
            }
        });
        return address;
    }*/

    public void doAddress() {
        /*DialogAddress dialogAddress = new DialogAddress();
        final String[] items=doSelectAddress();
        dialogAddress.show("请选择地点:", items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAddress.setText(items[which]);
            }
        }, getFragmentManager());*/

        /*// 百度地图
        Intent intent = new Intent();
        intent.setAction("com.baidu.location.f");
        intent.setPackage(getApplicationContext().getPackageName());
        getApplicationContext().startService(intent);  // 开启服务

        *//*Intent intent=new Intent("com.baidu.location.f");
        getApplicationContext().startService(intent);  // 开启服务*//*
        Log.i("1", "1");
        mMyLocationListener=new MyLocationListener();
        location(mMyLocationListener);
        addr=mMyLocationListener.getAddr();
        mAddress.setText(addr);*/

        //最后
        /*Intent intent=new Intent(getApplicationContext(), MapActivity.class);
        intent.putExtra("code", String.valueOf(CODE2));
        startActivityForResult(intent,CODE2);*/
    }


    /*public void location(BDLocationListener listener) {
        mLocationClient = new LocationClient(getApplicationContext());

        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);// 中文地址
        option.setCoorType("bd09ll");// gcj02 国测局经纬度坐标系 ；bd09 百度墨卡托坐标系；bd09ll
        // 百度经纬度坐标系
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);// 设置定位模式
        option.setScanSpan(5*60000);//检查周期 小于1秒的按1秒
        mLocationClient.setLocOption(option);
        Log.i("2", "2");
        mLocationClient.start();
    }*/
}
