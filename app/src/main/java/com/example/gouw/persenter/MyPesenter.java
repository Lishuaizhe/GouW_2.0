package com.example.gouw.persenter;

import com.example.gouw.contrcet.MyContrcet;
import com.example.gouw.model.MyModelCallBack;
import com.example.gouw.entity.LBean;
import com.example.gouw.model.MyModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class MyPesenter extends MyContrcet.Ipersenter {

    private MyModel myModel;
    private MyContrcet.Iview iview;


    public MyPesenter(MyContrcet.Iview iview) {
        this.iview = iview;
        myModel = new MyModel();
    }

    @Override
    public void GetList(HashMap<String, String> hashMap) {
        myModel.GETlist(hashMap, new MyModelCallBack() {
            @Override
            public void Success(String s) {
                LBean lBean = new Gson().fromJson(s, LBean.class);
                iview.Success(lBean.data);
            }

            @Override
            public void Fauil(String s) {
                iview.Fuail(s);
            }
        });

    }
}
