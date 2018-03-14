package com.edu.joyread.activity;

/**
 * Created by 87395 on 2017/8/8.
 */

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;


//import com.lin.coursetwo.R;


public class FragmentPerson extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_person,container,false);

        ItemListener itemListener = new ItemListener();
        Button personalInfoBtn = (Button) view.findViewById(R.id.personalInfoBtn);
        Button myFavBtn = (Button) view.findViewById(R.id.myFavBtn);
        Button currentBorrowBtn = (Button) view.findViewById(R.id.currentBorrowBtn);
        Button borrowHistoryBtn = (Button) view.findViewById(R.id.borrowHistoryBtn);
        Button logoutBtn = (Button) view.findViewById(R.id.logoutBtn);
        personalInfoBtn.setOnClickListener(itemListener);
        myFavBtn.setOnClickListener(itemListener);
        currentBorrowBtn.setOnClickListener(itemListener);
        borrowHistoryBtn.setOnClickListener(itemListener);
        logoutBtn.setOnClickListener(itemListener);


        return view;
    }





    class ItemListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id){
                case R.id.personalInfoBtn :
                    System.out.println("in personalInfoBtn");
                    Intent intentPersonalInfo = new Intent(FragmentPerson.this.getActivity(),PersonalInfoActivity.class);
                    System.out.println("new intent");
                    startActivity(intentPersonalInfo);
                    System.out.println("start activity");
                    break;

                case R.id.myFavBtn :
                    System.out.println("in personalInfoBtn");
                    Intent intentMyFav = new Intent(FragmentPerson.this.getActivity(),MyFavActivity.class);
                    System.out.println("new intent");
                    startActivity(intentMyFav);
                    System.out.println("start activity");
                    break;
                case R.id.currentBorrowBtn :
                    System.out.println("in personalInfoBtn");
                    Intent intentCurrentBorrow = new Intent(FragmentPerson.this.getActivity(),CurrentBorrowActivity.class);
                    System.out.println("new intent");
                    startActivity(intentCurrentBorrow);
                    System.out.println("start activity");
                    break;
                case R.id.borrowHistoryBtn :
                    System.out.println("in personalInfoBtn");
                    Intent intentBorrowHistory = new Intent(FragmentPerson.this.getActivity(),BorrowHistoryActivity.class);
                    System.out.println("new intent");
                    startActivity(intentBorrowHistory);
                    System.out.println("start activity");
                    break;
                case R.id.logoutBtn :
                    System.out.println("in personalInfoBtn");
                    AlertDialog.Builder builder = new Builder(FragmentPerson.this.getActivity());
                    builder.setTitle("注销");//设置对话框的标题
                    builder.setMessage("是否确认退出该账号？");//设置对话框的内容
                    builder.setPositiveButton("确认", new OnClickListener() {  //这个是设置确定按钮

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intentLogin = new Intent(FragmentPerson.this.getActivity(),LoginActivity.class);
                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit();
                            editor.putBoolean("state", false);
                            editor.commit();
                            System.out.println("new intent");
                            startActivity(intentLogin);
                            System.out.println("start activity");
                        }
                    });
                    builder.setNegativeButton("取消", new OnClickListener() {  //取消按钮

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    AlertDialog b=builder.create();
                    b.show();  //必须show一下才能看到对话框，跟Toast一样的道理
                    break;
            }
        }
    }
}
