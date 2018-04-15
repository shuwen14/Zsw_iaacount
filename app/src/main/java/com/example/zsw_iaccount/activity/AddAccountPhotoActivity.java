package com.example.zsw_iaccount.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zsw_iaccount.R;
import com.example.zsw_iaccount.addaccount.AddAccountExFragment_ViewBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by 赵舒文 on 2018-4-12.
 */

public class AddAccountPhotoActivity extends Activity{

    private Integer mCode;
    private static int RESULT_LOAD_IMAGE = 10;
    String SD_CARD_TEMP_DIR;
    Bitmap myBitmap,bm;
    private ImageView imageView;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addaccount_photo_activity);

        Intent intent_date=getIntent();
        String tmp = intent_date.getStringExtra("code");
        mCode=Integer.valueOf(tmp);

        //拍照的

        //照片的命名，目标文件夹下，以当前时间数字串为名称，即可确保每张照片名称不相同。
        String str = null;
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间，进一步转化为字符串
        date = new Date();
        str = format.format(date);
        SD_CARD_TEMP_DIR = Environment.getExternalStorageDirectory()
                + File.separator + str+".jpg";//设定照相后保存的文件名，类似于缓存文件

        imageView = (ImageView)findViewById(R.id.photo_iv_photo);
        Button btn = (Button) findViewById(R.id.photo_bt_photo);
        textView = (TextView) findViewById(R.id.photo_tv_path);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                Intent cameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(SD_CARD_TEMP_DIR)));
                startActivityForResult(cameraIntent, 0);
            }
        });


        //相册的
        Button bt_xiangce = (Button) findViewById(R.id.photo_bt_selectphoto);
        bt_xiangce.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //点击事件，而重定向到图片库
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //这里要传一个整形的常量RESULT_LOAD_IMAGE到startActivityForResult()方法。
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });


        //确认传回图片地址给add界面jj
        Button bt_positive = (Button) findViewById(R.id.photo_bt_positive);
        bt_positive.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(mCode==0x13A)
                {
                    Intent intent = new Intent(AddAccountPhotoActivity.this,AddAccountExFragment_ViewBinding.class);
                    intent.putExtra("path", textView.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //拍照的
// TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            Log.d("requestCode", "Need 0");
            if(resultCode == RESULT_OK){
                Log.d("resultCode", "OK!!!" + SD_CARD_TEMP_DIR);
                myBitmap = BitmapFactory.decodeFile(SD_CARD_TEMP_DIR);
                imageView.setImageBitmap(myBitmap);
                textView.setText(SD_CARD_TEMP_DIR);
            }else{
                Log.d("resultCode", "" + resultCode);
            }
        }else{
            Log.d("requestCode", "Not Need");
        }


        //相册的
        //我们需要判断requestCode是否是我们之前传给startActivityForResult()方法的RESULT_LOAD_IMAGE，并且返回的数据不能为空
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            //查询我们需要的数据
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            textView.setText(picturePath);
        }
    }
}
