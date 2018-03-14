package com.edu.joyread.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by v2377 on 2017/9/3.
 */

public class MyFavActivity extends AppCompatActivity {
    //此处是用于显示的静态数据
    private String[] bookName = new String[]{"美国众神", "哈利波特", "算法竞赛入门"};
    private String[] bookAuthor = new String[]{"尼尔·盖曼","J.K.罗琳","刘汝佳"};
    private int[] bookImgIds = new int[]{R.drawable.book1,R.drawable.book1,R.drawable.book1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fav);

        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < bookName.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("image", bookImgIds[i]);
            showitem.put("name", bookName[i]);
            showitem.put("author", bookAuthor[i]);
            listitem.add(showitem);
            System.out.println("add in showitem");
        }
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(),
                listitem,
                R.layout.my_fav_list_item_layout,
                new String[] {"image","name","author"},
                new int[] {R.id.myFavBookImg, R.id.myFavBookName, R.id.myFavBookAuthor});
        ListView listView = (ListView) findViewById(R.id.myFavListView);
        listView.setAdapter(myAdapter);
    }
}
