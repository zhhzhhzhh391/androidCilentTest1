package com.test.natsuyasumi.testdemo21;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button getAllUser;
    private httptest mgrHttp;
    private loginAsyncTask logintask ;
    private userTask getAllUserTask;
    private Intent toChatConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toChatConnect = new Intent(MainActivity.this,ConnectActivity.class);
        logintask = new loginAsyncTask();
        getAllUserTask = new userTask();

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        getAllUser = (Button)findViewById(R.id.getAllUser);
        //按钮响应事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logintask.execute();
            }
        });
        getAllUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllUserTask.execute();
            }
        });
    }

    @Override
    //在Activity 或 Fragment中使用 AsyncTask时，最好在Activity 或 Fragment的onDestory（）调用 cancel(boolean)；
    protected void onDestroy() {
        super.onDestroy();
        logintask.cancel(true);
        getAllUserTask.cancel(true);
        this.getDelegate().onDestroy();
    }


    private class loginAsyncTask extends AsyncTask<String,Integer,String> {
        Response response;
        String result;
        @Override
        protected String doInBackground(String... strings) {
            try{
                mgrHttp = new httptest(djangoabout.loginAddress);
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                Map<String,String> loginAbout = new HashMap<>();
                loginAbout.put("username",usernameText);
                loginAbout.put("password",passwordText);
                FormBody.Builder formBody = new FormBody.Builder();
                for(String key:loginAbout.keySet()){
                    formBody.add(key,loginAbout.get(key));
                }
                response = mgrHttp.post(formBody.build());
                System.out.println(response.toString());
            }catch(Exception e){
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println(this.result);
            // 执行完毕后，则更新UI
            if(this.result.equals("200")){
                startActivity(toChatConnect);
                login.setText("登录成功");
            }
            else if(this.result.equals("1001")){
                login.setText("账号密码错误");
            }
            else{
                login.setText("未知错误");
            }
            logintask.cancel(true);
        }
    }

    private class userTask extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... strings) {
            try {
                mgrHttp = new httptest(djangoabout.getAllUser);
                mgrHttp.get();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "success";
        }
        @Override
        protected void onPostExecute(String result) {
            // 执行完毕后，则更新UI
            getAllUser.setText("已执行");
            getAllUserTask.cancel(true);

        }
    }
}
