package com.test.natsuyasumi.testdemo21;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;


public class ConnectActivity extends AppCompatActivity {

    private Button start;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        start = (Button) findViewById(R.id.start);
        text = (TextView) findViewById(R.id.text);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();
            }
        });
    }

    private void connect(){
        WebSocketDemo listener = WebSocketDemo.getDefault();
        OkHttpClient client = new OkHttpClient();
        client.dispatcher().executorService().shutdown();
    }

    public void output(final String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(text.getText().toString() + content + "\n");
            }
        });
    }

}
