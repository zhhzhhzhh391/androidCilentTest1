package com.test.natsuyasumi.testdemo21;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.dom4j.Element;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GameActivity extends AppCompatActivity {
    String threadName = "textDeliver";//线程名称为递送文本
    private Handler mHandler; //用来接受当前线程传送来的bundle信息，然后存入textList中
    public Integer dialogIndex = 0;//用来定位当前位于根节点的那个dialog子节点中
    ArrayList<dialogText> textList = new ArrayList<>();//用来获取xml的信息并且放入适配器中打印出来
    public final dialogText dialogAtr = new dialogText(); //定义一个dialogText方便存储信息
//    public baseThread textDeliver = null; //线程
    String chooseRoad = "0";//对应xml中不同的线路选择
    Integer buttonAId = 2131165219; //为buttonA的id的int型编号
    Integer buttonBId = 2131165220;//为buttonB的id的int型编号
    Intent gameBeging;
    public textShowAdapter adapter;
    ListView listView;

    public SQLiteDatabase db;
    /**使用服务**/
    private MyReceiver receiver = null; //广播接受器

    public GameActivity() {
    }

    /**/
    @Override
    @SuppressLint("HandlerLeak")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setTitle("20190812");
        gameBeging = new Intent(GameActivity.this,textListService.class);
        startService(gameBeging);
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.test.natsuyasumi.testdemo21.textListService");
        GameActivity.this.registerReceiver(receiver,filter);
        //从service接受textList
        adapter = new textShowAdapter(this,textList);
        listView = (ListView) findViewById(R.id.textTest);
        listView.setAdapter(adapter);
        final readXML getXML = new readXML();
        final org.dom4j.Document ocument = getXML.readXML(this);
    }
    public class MyReceiver extends BroadcastReceiver {
        textListService buttonControl = new textListService();

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle2 = intent.getBundleExtra("text");
            if(bundle2.getString("dialogType").equals("dialog")){
                dialogAtr.setType(bundle2.getString("dialogType"));
                dialogAtr.setText(bundle2.getString("dialogText"));
                textList.add(dialogAtr);
            }else if(bundle2.getString("dialogType").equals("button")){
                dialogAtr.setTextA(bundle2.getString("dialogTextA"));
                dialogAtr.setTextB(bundle2.getString("dialogTextB"));
                dialogAtr.setType(bundle2.getString("dialogType"));
                dialogAtr.setButtonAId(bundle2.getString("buttonAId"));
                dialogAtr.setButtonBId(bundle2.getString("buttonBId"));
                buttonAId = Integer.parseInt(bundle2.getString("buttonAId"));
                buttonBId = Integer.parseInt(bundle2.getString("buttonBId"));
                textList.add(dialogAtr);
            }else if(bundle2.getString("dialogType").equals("threadStop")){

                buttonShow();
                dialogAtr.setType(bundle2.getString("dialogType"));
            }

            adapter.notifyDataSetChanged();
        }
    }
    protected void onSaveInstanceState(Bundle saveGameWhenHome) {//Home键存档
        super.onSaveInstanceState(saveGameWhenHome);
    }
    protected void onPause(){
        super.onPause();
    }

    public void onBackPressed(){
        moveTaskToBack(true);
    }//返回键时存档，流氓方式

    public void buttonShow() {
        Intent intent = new Intent();
        gameBeging = new Intent(GameActivity.this,textListService.class);
        final Button buttonA;
        final Button buttonB;
        buttonA = (Button) findViewById(buttonAId);
        buttonB = (Button) findViewById(buttonBId);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRoad = buttonA.getText().toString();
                gameBeging.putExtra("chooseRoad",chooseRoad);
                gameBeging.putExtra("threadBegin",true);
                startService(gameBeging);
                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRoad = buttonB.getText().toString();
                gameBeging.putExtra("chooseRoad",chooseRoad);
                gameBeging.putExtra("threadBegin",true);
                startService(gameBeging);
                buttonA.setEnabled(false);
                buttonB.setEnabled(false);
            }
        });
    }

    public void onStart(){
        startService(new Intent(GameActivity.this,textListService.class));
        super.onStart();
    }
}
