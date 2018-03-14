package com.edu.joyread.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.joyread.model.LoginBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    EditText idTxt;
    EditText pswTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        idTxt = (EditText) findViewById(R.id.idTxt);
        pswTxt = (EditText) findViewById(R.id.pswTxt);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        TextView addUser = (TextView) findViewById(R.id.createUser);
        loginBtn.setOnClickListener(new LoginListener());
        addUser.setOnClickListener(new AddUserListener());
    }


    class LoginListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d("LoginActivityDebug", "clicked");
            /*if( (idTxt.getText().toString().equals("2015214461")) && (pswTxt.getText().toString().equals("123456")) ) {
                SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
                editor.putBoolean("state", true);
                editor.commit();
                Intent intentLogin = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intentLogin);
            } else {
                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }*/

            String sID= idTxt.getText().toString();
            String password = pswTxt.getText().toString();
            LoginBean lb = new LoginBean();
            lb.setsID(sID);
            lb.setPassword(password);
            Log.d("LoginActivityDebug", "clicked2");
            doLogin(lb);
        }
    }

    public void doLogin(final LoginBean lb) {
        Log.i("LoginActivity", "doLogin");
        new Thread() {
            @Override
            public void run() {
                try {
                    JSONObject loginJSON = new JSONObject();
                    loginJSON.put("sID", lb.getsID());
                    loginJSON.put("password", lb.getPassword());
                    String content = String.valueOf(loginJSON);

                    String target = R.string.serverURL + "user/login";
                    HttpURLConnection conn = (HttpURLConnection) new URL(target).openConnection();
                    conn.setConnectTimeout(4000);
                    conn.setRequestMethod("POST");
                    conn.setInstanceFollowRedirects(true);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Charset", "UTF-8");

                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(content);
                    out.flush();
                    out.close();
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        Log.i("LoginActivity", "connected");
                        InputStreamReader in = new InputStreamReader(conn.getInputStream());
                        BufferedReader br = new BufferedReader(in);
                        String result = null;
                        if ((result = br.readLine()) != null) {
                            if (result.equals("ok")) {
                                SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
                                editor.putBoolean("state", true);
                                editor.putString("sID", lb.getsID());
                                editor.commit();
                                Intent intentLogin = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intentLogin);
                            }

                        } else {
                            Log.e("LoginActivity", "result is null");
                        }
                    }
                } catch (JSONException e) {
                    Log.e("LoginActivity", "JSON Exception");
                } catch (MalformedURLException e) {
                    Log.e("LoginActivity", "URL Exception");
                } catch (IOException e) {
                    Log.e("LoginActivity", "IO Exception");
                }
            }
        }.start();
    }

    class AddUserListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intentAddUser = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intentAddUser);
        }
    }

}


