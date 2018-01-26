package com.example.a87395.joyread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.a87395.joyread.tools.DateOperator;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by v2377 on 2017/9/4.
 */

public class BorrowHistoryActivity extends AppCompatActivity {
    //此处是用于显示的静态数据
    private String[] bookName = new String[]{"C++程序设计", "霍乱时期的爱情", "数据结构"};
    private String[] bookAuthor = new String[]{"Daniel Liang","加西亚·马尔克斯","殷人昆"};
    private int[] bookImgIds = new int[]{R.drawable.book1,R.drawable.book1,R.drawable.book1};
    private String[] borrowDate = new String[]{"2017-8-6","2017-8-7","2017-8-12"};
    private String[] returnDate = new String[]{"2017-9-8","2017-9-10",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_history);

        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < bookName.length; i++) {
            int tempDays = 0;
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("image", bookImgIds[i]);
            showitem.put("name", bookName[i]);
            showitem.put("author", bookAuthor[i]);
            showitem.put("borrowDate",borrowDate[i]);
            if(returnDate[i] == null || returnDate[i].toString().trim().equals("")){
                showitem.put("returnDate","");
            }else{
                showitem.put("returnDate","归还日期:" + returnDate[i]);
            }
            listitem.add(showitem);
            System.out.println("listitem add succ");
        }
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(),
                listitem,
                R.layout.borrow_history_list_item_layout,
                new String[] {"image","name","author","returnDate","borrowDate"},
                new int[] {R.id.borrowHistoryImg, R.id.borrowHistoryBookName, R.id.borrowHistoryBookAuthor, R.id.borrowHistoryReturnDateValue, R.id.borrowHistoryBorrowDateValue});
        ListView listView = (ListView) findViewById(R.id.borrowHistoryListView);
        listView.setAdapter(myAdapter);
    }
}
