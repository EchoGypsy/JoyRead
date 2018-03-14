package com.edu.joyread.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by v2377 on 2018/1/27.
 */

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button regButton = (Button) findViewById(R.id.regBtn);
        regButton.setOnClickListener(new AddUserListener());

    }

    class AddUserListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            finish();
        }
    }
}
