package com.edu.joyread.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class BookInfoActivity extends AppCompatActivity {

    private boolean isFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
    }

    public void addFav(View view) {
        ImageView fav = (ImageView) findViewById(R.id.favButton);
//        if(fav.getResources().getDrawable().equals(R.drawable.ic_favorite_gray_24dp)) {
//            fav.setImageResource(R.drawable.ic_favorite_pink_30dp);
//        } else {
//            fav.setImageResource(R.drawable.ic_favorite_gray_24dp);
//        }

        if (isFav == false){
            fav.setImageResource(R.drawable.ic_favorite_pink_30dp);
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
            isFav = true;
        }else {
            fav.setImageResource(R.drawable.ic_favorite_gray_24dp);
            Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
            isFav = false;
        }

    }
}
