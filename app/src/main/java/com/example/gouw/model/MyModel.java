package com.example.gouw.model;

import android.os.Handler;

import com.example.gouw.contrcet.MyContrcet;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyModel implements MyContrcet.Imodle {

    private static final String S = "http://www.zhaoapi.cn/product/getCarts?uid=90";
    private Handler handler = new Handler();

    @Override
    public void GETlist(final HashMap<String, String> hashMap, final MyModelCallBack callBack) {

        /*
        * 添加日志拦截器
        * */
        HttpLoggingInterceptor interceptor  = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new  OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        final Request s = new Request.Builder().url(S).build();

        okHttpClient.newCall(s).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.Fauil("网络错误");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.Success(string);
                    }
                });
            }
        });


    }
}
