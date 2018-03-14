package com.edu.joyread.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;
    private FragmentHome fragmentHome;
    private FragmentSearch fragmentSearch;
    private FragmentPerson fragmentPerson;
    private String sID;
    private String password;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener  //创建事件监听器
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    showNav(R.id.navigation_home);
                    return true;
                case R.id.navigation_search:
                    //mTextMessage.setText(R.string.title_search);
                    showNav(R.id.navigation_search);
                    return true;
                case R.id.navigation_person:
                    //mTextMessage.setText(R.string.title_person);
                    showNav(R.id.navigation_person);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);   //注册监听器
        //navigation.setBackgroundColor(R.color.colorAccent);


        //Button loginBtn = (Button) findViewById(R.id.loginBtn);
        //EditText idTxt = (EditText) findViewById(R.id.idTxt);
        //EditText pswTxt = (EditText) findViewById(R.id.pswTxt);
        final SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        if(userInfo.getBoolean("state", false) == false) {
            Intent intentLogin = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intentLogin);
        }

    }

    //init（）用来初始化组件
    private void init(){
        fragmentHome =new FragmentHome();
        fragmentSearch =new FragmentSearch();
        fragmentPerson =new FragmentPerson();
        FragmentTransaction beginTransaction=getFragmentManager().beginTransaction();
        beginTransaction.add(R.id.content, fragmentHome).add(R.id.content, fragmentSearch).add(R.id.content, fragmentPerson);//开启一个事务将fragment动态加载到组件
        beginTransaction.hide(fragmentHome).hide(fragmentSearch).hide(fragmentPerson);//隐藏fragment
        //beginTransaction.addToBackStack(null);//返回到上一个显示的fragment
        beginTransaction.commit();//每一个事务最后操作必须是commit（），否则看不见效果
        showNav(R.id.navigation_home);
    }

    private void showNav(int navid){
        FragmentTransaction beginTransaction=getFragmentManager().beginTransaction();
        switch (navid){
            case R.id.navigation_home:
                beginTransaction.hide(fragmentSearch).hide(fragmentPerson);
                beginTransaction.show(fragmentHome);
                //beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            case R.id.navigation_search:
                beginTransaction.hide(fragmentHome).hide(fragmentPerson);
                beginTransaction.show(fragmentSearch);
                //beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            case R.id.navigation_person:
                beginTransaction.hide(fragmentSearch).hide(fragmentHome);
                beginTransaction.show(fragmentPerson);
                //beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
        }
    }



}
