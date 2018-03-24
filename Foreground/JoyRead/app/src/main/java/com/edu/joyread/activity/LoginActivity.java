package com.edu.joyread.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    public static final int SHOW_RESPONSE=0;
    EditText idTxt;
    EditText pswTxt;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    //Log.i("LoginActivity", response);
            }
//            if (result != null) {
//                if (result.equals("ok")) {
//                    SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
//                    editor.putBoolean("state", true);
//                    editor.putString("sID", idTxt.getText().toString());
//                    editor.commit();
//                    Intent intentLogin = new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intentLogin);
//                }
//            } else {
//                Log.e("LoginActivity", "result is null");
//            }
        }
    };
    private String result = null;



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

//            String sID= idTxt.getText().toString();
//            String password = pswTxt.getText().toString();
//            final LoginBean lb = new LoginBean();
//            lb.setsID(sID);
//            lb.setPassword(password);
//            Log.d("LoginActivityDebug", "clicked2");
            /*class LoginHandler extends Handler {
                @Override
                public void handleMessage(Message msg) {
                    //super.handleMessage(msg);
                    if (result != null) {
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
            }
            handler = new LoginHandler();*/
//            class LoginRunnable implements Runnable {
//                @Override
//                public void run() {
//                    doLogin(lb);
//                    Message m = handler.obtainMessage();
//                    handler.sendMessage(m);
//                }
//            }
//            new Thread(new LoginRunnable()).start();

            doLogin();
        }
    }

    public void doLogin() {
        Log.i("LoginActivity", "doLogin");
        final String sID = idTxt.getText().toString();
        final String password = pswTxt.getText().toString();
//        new Thread() {
//            @Override
//            public void run() {

        //String result = "";

        new Thread(new Runnable() {
            @Override
            public void run() {
                String target = "http://10.0.2.2:8080/JoyRead/stuLogin.action";
                URL url;
                HttpURLConnection conn = null;
                try {
                    JSONObject loginJSON = new JSONObject();
                    loginJSON.put("sID", sID);
                    loginJSON.put("password", password);
                    String content = String.valueOf(loginJSON);

                    url = new URL(target);
                    //String target = /*getResources().getString(R.string.serverURL)*/ "http://10.0.2.2:8080/XmlParser/" + "stuLogin.action";
                    conn = (HttpURLConnection) url.openConnection();

//                    conn.setRequestMethod("POST");
//                    conn.setDoOutput(true);
//                    conn.setUseCaches(false);
//                    conn.setInstanceFollowRedirects(true);
//                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
//                    BufferedReader buffer = new BufferedReader(in);
//                    String line = null;
//                    while ((line = buffer.readLine()) != null) {
//                        result += line;
//                    }
//                    in.close();
//                    conn.disconnect();

                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("POST");
                    conn.setInstanceFollowRedirects(true);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("User-Agent", "Fiddler");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Charset", "UTF-8");

                    //DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    OutputStream out = conn.getOutputStream();
                    out.write(content.getBytes());
                    out.flush();
                    out.close();
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        Log.d("LoginActivity", "connected");
                        InputStreamReader in = new InputStreamReader(conn.getInputStream());
                        BufferedReader br = new BufferedReader(in);
                        //String result = null;
                        //result = br.readLine();
                        if ((result = br.readLine()) != null) {
                            if (result.equals("ok")) {
                                SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
                                editor.putBoolean("state", true);
                                editor.putString("sID", sID);
                                editor.commit();
                                Intent intentLogin = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intentLogin);
                            } else {
                                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("LoginActivity", "result is null");
                        }
                    }
                    Message msg = new Message();
                    msg.what = SHOW_RESPONSE;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("LoginActivity", "JSON Exception");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("LoginActivity", "URL Exception");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("LoginActivity", "IO Exception");
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }


    class AddUserListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intentAddUser = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intentAddUser);
        }
    }

}


