package com.example.gouw.contrcet;

import com.example.gouw.entity.LBean;
import com.example.gouw.model.MyModelCallBack;

import java.util.HashMap;
import java.util.List;

public interface MyContrcet {

    abstract class Ipersenter{
        public abstract void GetList(HashMap<String, String> hashMap);
    }

    interface Imodle{
        void GETlist(HashMap<String,String> hashMap, MyModelCallBack callBack);
    }

    interface Iview{
        void Success(List<LBean.Cart> list);
        void Fuail(String s);
    }

}
