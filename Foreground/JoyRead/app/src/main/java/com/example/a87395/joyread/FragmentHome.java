package com.example.a87395.joyread;

/**
 * Created by 87395 on 2017/8/8.
 */

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

//import com.lin.coursetwo.R;


public class FragmentHome extends Fragment {

    LinearLayout lo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        //通过id找到需要被监听的layout
        lo= (LinearLayout) view.findViewById(R.id.lo);
        lo.setOnClickListener(new View.OnClickListener(){
            //为找到的layout设置监听
            @Override
            //重写onClick函数
            public void onClick(View v){
                toBookInfo(v);}
        });
        return view;
    }

    private void toBookInfo(View view) {
        Intent intent = new Intent(this.getActivity(),BookInfoActivity.class);
        startActivity(intent);
    }

//    public void toBookInfo(View view) {
//        System.out.println("233333");
////        Intent intent = new Intent(this,BookInfoActivity.class);
////        startActivity(intent);
//    }
}
