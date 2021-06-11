package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.intentdemo.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        String user = "账户："+intent.getStringExtra("user");
        String pwd = "密码："+intent.getStringExtra("pwd");
        String isAdmin = "是否为管理员："+intent.getStringExtra("isAdmin");

        mBinding.userText.setText(user);
        mBinding.userText.setText(pwd);
        mBinding.userText.setText(isAdmin);
    }
}