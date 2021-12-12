package com.test.natsuyasumi.testdemo21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class GameBeginActivity extends AppCompatActivity {
    private static Bundle receiveBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_begin);
        final Intent beginGame = new Intent(GameBeginActivity.this,MainActivity.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(beginGame);
                finish();
            }
        };
        timer.schedule(task,3000);
    }
}
