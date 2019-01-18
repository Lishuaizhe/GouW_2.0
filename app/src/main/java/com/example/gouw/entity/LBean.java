package com.example.gouw.entity;

import java.util.List;

public class LBean {

    public String msg;
    public String code;
    public List<Cart> data;

    public class Cart{
        /*
        * 一级
        * */
        public boolean Yxuan;

        public String sellerName;
        public String sellerid;
        public List<Godds> list;

        public class Godds{
            /*
             * 二级
             * */
            public boolean Exuan;

            public String title;
            public String images;
            public double price;
            public String pid;
            public int pos;
            public int productNum =1;
        }
    }



}
