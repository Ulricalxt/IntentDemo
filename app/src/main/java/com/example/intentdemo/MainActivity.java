package com.example.intentdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.intentdemo.databinding.ActivityMainBinding;

/**
 * @author 龚鸿飞
 */
public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding mBinding;
    private static final int CALL_CODE= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.ButtonToMain.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HelloActivity.class);
            startActivity(intent);
        });
        mBinding.ButtonToBaidu.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.baidu.com/"));
            startActivity(intent);
        });
        mBinding.ButtonToBing.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String s = mBinding.editKey.getText().toString();
            intent.setData(Uri.parse("https://cn.bing.com/search?q=" + s));
            startActivity(intent);
        });
        mBinding.ButtonToPhone.setOnClickListener(v -> {
            String callPermyssion = Manifest.permission.CALL_PHONE;
            if (ActivityCompat.checkSelfPermission(this, callPermyssion) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{callPermyssion},1);
            } else {
                call(Intent.ACTION_CALL);
            }
        });

        /**
         * 发送短信
         * */
        mBinding.ButtonToSMS.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:17671049231"));
            intent.putExtra("sms_body","年轻人发送第一条短信");
            startActivity(intent);
        });

        mBinding.ButtonToCamera.setOnClickListener(v -> {
            //启动系统相机
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        });
    }
/**
 * 打电话
 * */
    private void call(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.setData(Uri.parse("tel:17671049231"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        if (requestCode==CALL_CODE){
            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                call(Intent.ACTION_DIAL);
            }else{
                Toast.makeText(this,"未授权拨打电话的权限",Toast.LENGTH_SHORT).show();

                call(Intent.ACTION_CALL);
            }
        }
    }
}