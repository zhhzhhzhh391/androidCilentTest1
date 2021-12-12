package com.test.natsuyasumi.testdemo21;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class httptest {

    private final OkHttpClient client = new OkHttpClient();
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    private String ip;
    private String url;

    public httptest(String url){
        //初始化配置ip需要配置ip和端口
        this.ip = "http://"+djangoabout.localComputerIp;
        this.url = url;
    }

    //同步get请求  不能在Android主线程中执行
    public Response get() throws Exception {
        Request request = new Request.Builder()
                .url(ip+url)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
        System.out.println(response.code());
        return response;
    }

    //同步post请求
    public Response post(RequestBody body) throws Exception{
        Request request = new Request.Builder()
                .url(ip+url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return response;
    }
}
