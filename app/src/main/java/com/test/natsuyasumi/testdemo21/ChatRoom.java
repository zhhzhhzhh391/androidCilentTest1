package com.test.natsuyasumi.testdemo21;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.sf.json.JSONObject;

import java.util.List;

import bean.chatRoomBean;
import okhttp3.OkHttpClient;

public class ChatRoom extends AppCompatActivity {
    private List<String> userList;

    private JSONObject userJson;
    private Button setConnect;
    chatRoomBean enterChatCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        enterChatCode = new chatRoomBean();

        setConnect = (Button)findViewById(R.id.setChatConnect);
        setConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    connect();
            }
        });
    }

    public void connect() {
        userJson = new JSONObject();

        WebSocketDemo listener = WebSocketDemo.getDefault();
        OkHttpClient client = new OkHttpClient();
        client.dispatcher().executorService().shutdown();
        enterChatCode.setCode("100");
        enterChatCode.setToken("1fisnfsnfsiofno");
        JSONObject json = JSONObject.fromObject(enterChatCode);
        System.out.println("第一次修改提交");
        listener.sendMessage(json.toString());
    }


}


