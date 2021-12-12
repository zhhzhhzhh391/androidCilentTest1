package com.test.natsuyasumi.testdemo21;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class textListService extends Service {

    String threadName = "textDeliver";//线程名称为递送文本
    private Handler mHandler; //用来接受当前线程传送来的bundle信息，然后存入textList中
    public Integer dialogIndex = 0;//用来定位当前位于根节点的那个dialog子节点中
    ArrayList<dialogText> textList = new ArrayList<>();//用来获取xml的信息并且放入适配器中打印出来
    public final dialogText dialogAtr = new dialogText(); //定义一个dialogText方便存储信息
    public baseThread textDeliver = null; //线程
    String chooseRoad = "0";//对应xml中不同的线路选择
    Integer buttonAId = 2131165219; //为buttonA的id的int型编号
    Integer buttonBId = 2131165220;//为buttonB的id的int型编号



    public textListService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate(){
        System.out.println("service被create");
        final readXML getXML = new readXML();
        final org.dom4j.Document ocument = getXML.readXML(this);
        textDeliver = new baseThread("textDeliver", false) {
            @Override
            public void process() {
                Bundle bundle = new Bundle();
                Element getCorrectRoad = getXML.getCurrentRoad(chooseRoad);//从用户的选择中来获取所要走的路线
                List getDialogList = getCorrectRoad.elements("dialog");//
                Element getDialog = (Element) getDialogList.get(dialogIndex);
                if (getDialog.attributeValue("type").equals("dialog")) {
                    bundle.putString("dialogText", getDialog.element("text").getText());
                    bundle.putString("dialogType", "dialog");
                    textDeliver.SUSPEND_TIME_MILLISECONDS = 1000;//后面设置每句话显示的时间差
                } else if (getDialog.attributeValue("type").equals("button")) {
                    bundle.putString("dialogTextA", getDialog.element("textA").getText());
                    bundle.putString("dialogTextB", getDialog.element("textB").getText());
                    bundle.putString("buttonAId",getDialog.element("idA").getText());
                    bundle.putString("buttonBId",getDialog.element("idB").getText());
                    buttonAId = Integer.parseInt(getDialog.element("idA").getText());
                    buttonBId = Integer.parseInt(getDialog.element("idB").getText());
                    bundle.putString("dialogType", "button");
                    textDeliver.SUSPEND_TIME_MILLISECONDS = 0;
                }else if(getDialog.attributeValue("type").equals("threadStop")){
                    bundle.putString("dialogType","threadStop");
                    textDeliver.stop();
                }
                dialogIndex++;
                Intent intent = new Intent();
                intent.putExtra("text",bundle);
                intent.setAction("com.test.natsuyasumi.testdemo21.textListService");
                sendBroadcast(intent);
            }
        };
        super.onCreate();
    }

    public int OnStartCommand(Intent intent, int flags, int startId){
        System.out.println("service被再次create");

        return START_STICKY;
    }

    public void threadCallUp(String chooseRoad){

    }

}
