package com.edu.joyread.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.edu.joyread.activity.tools.DateOperator;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by v2377 on 2017/9/3.
 */

public class CurrentBorrowActivity extends AppCompatActivity {
    //此处是用于显示的静态数据
    private String[] bookName = new String[]{"C++程序设计", "霍乱时期的爱情", "数据结构"};
    private String[] bookAuthor = new String[]{"Daniel Liang","加西亚·马尔克斯","殷人昆"};
    private int[] bookImgIds = new int[]{R.drawable.book1,R.drawable.book1,R.drawable.book1};
    private String[] returnDate = new String[]{"2017-9-8","2017-9-10","2017-9-15"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_borrow);

        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < bookName.length; i++) {
            int tempDays = 0;
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("image", bookImgIds[i]);
            showitem.put("name", bookName[i]);
            showitem.put("author", bookAuthor[i]);
            showitem.put("returnDate",returnDate[i]);
            try {
                tempDays = DateOperator.daysBetween(new Date(System.currentTimeMillis()).toString(), returnDate[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(tempDays < 5){
                showitem.put("daysRemaining","剩余" + tempDays + "天");
            }else{
                showitem.put("daysRemaining","");
            }
            listitem.add(showitem);
        }
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(),
                listitem,
                R.layout.current_borrow_list_item_layout,
                new String[] {"image","name","author","returnDate","daysRemaining"},
                new int[] {R.id.currentBorrowImg, R.id.currentBorrowBookName, R.id.currentBorrowBookAuthor, R.id.returnDateValue, R.id.daysRemaining});
        ListView listView = (ListView) findViewById(R.id.currentBorrowListView);
        listView.setAdapter(myAdapter);

    }
}
