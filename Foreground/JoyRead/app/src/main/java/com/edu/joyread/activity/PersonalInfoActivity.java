package com.edu.joyread.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by v2377 on 2017/8/28.
 */

public class PersonalInfoActivity extends AppCompatActivity {
    private AlertDialog alertDialog = null;
    private AlertDialog.Builder builder = null;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        PersonalInfoItemListener personalInfoItemListener = new PersonalInfoItemListener();
        View pinfoTelLayout = findViewById(R.id.pinfoTel);
        View pinfoEmailLayout = findViewById(R.id.pinfoEmail);
        pinfoTelLayout.setOnClickListener(personalInfoItemListener);
        pinfoEmailLayout.setOnClickListener(personalInfoItemListener);

    }

    class PersonalInfoItemListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id){
                case R.id.pinfoTel :
                    LayoutInflater factoryTel = LayoutInflater.from(PersonalInfoActivity.this);
                    final View dialogViewTel = factoryTel.inflate(R.layout.personal_info_alert_dialog_layout,null);
                    new AlertDialog.Builder(PersonalInfoActivity.this)
                            .setTitle("请输入手机号")
                            .setView(dialogViewTel)
                            .setPositiveButton("确定",null).setNegativeButton("取消",null).create().show();
                    break;

                case R.id.pinfoEmail:
                    LayoutInflater factoryEmail = LayoutInflater.from(PersonalInfoActivity.this);
                    final View dialogViewEmail = factoryEmail.inflate(R.layout.personal_info_alert_dialog_layout,null);
                    new AlertDialog.Builder(PersonalInfoActivity.this)
                            .setTitle("请输入邮箱")
                            .setView(dialogViewEmail)
                            .setPositiveButton("确定",null).setNegativeButton("取消",null).create().show();
                    break;
            }
        }
    }
}
