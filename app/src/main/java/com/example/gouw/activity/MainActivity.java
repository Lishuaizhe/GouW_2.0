package com.example.gouw.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gouw.R;
import com.example.gouw.adapter.CardMyRadapter;
import com.example.gouw.callback.CallUIBack;
import com.example.gouw.contrcet.MyContrcet;
import com.example.gouw.entity.LBean;
import com.example.gouw.persenter.MyPesenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyContrcet.Iview,CallUIBack {

    private XRecyclerView mRefreshStatusListView;

    private CheckBox mQuanxuan;

    private TextView mTijiao;
    private MyPesenter pesenter;
    private List<LBean.Cart> carts;
    private CardMyRadapter cardMyRadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();
        initData();
    }

    /*
    * 加载数据
    * */
    private void initData() {
        pesenter = new MyPesenter(this);
        pesenter.GetList(new HashMap<String, String>());
        mQuanxuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO: 2019/1/15
                if (isChecked){
                    for (LBean.Cart c: carts) {
                        c.Yxuan=true;
                        for (LBean.Cart.Godds godds : c.list) {
                            godds.Exuan=true;
                        }
                    }
                }else {
                    for (LBean.Cart c: carts) {
                        c.Yxuan=false;
                        for (LBean.Cart.Godds godds : c.list) {
                            godds.Exuan=false;
                        }
                    }
                }
                cardMyRadapter.notifyDataSetChanged();
                getJiaGe();
            }
        });
    }

    /*
    * 获取总价
    * */
    private void getJiaGe() {
        double zong=0;
        for (LBean.Cart c: carts) {
            for (LBean.Cart.Godds g:c.list){
                if (g.Exuan){
                    zong+=g.price*g.productNum;
                }
            }
        }
        mQuanxuan.setText("￥: "+zong);
    }

    /*
    * 获取资源id
    * */
    private void initView() {
        mRefreshStatusListView =  findViewById(R.id.refresh_status_list_view);
        mRefreshStatusListView.setLayoutManager(new LinearLayoutManager(this));
        mRefreshStatusListView.setPullRefreshEnabled(false);
        mRefreshStatusListView.setLoadingMoreEnabled(false);
        mQuanxuan =  findViewById(R.id.quanxuan);
        mTijiao =  findViewById(R.id.tijiao);
    }

    @Override
    public void Success(List<LBean.Cart> list) {
        if (list!=null){
            carts=list;
            for (LBean.Cart d: carts) {
                for (LBean.Cart.Godds g : d.list){
                    g.productNum=1;
                }
            }
            cardMyRadapter = new CardMyRadapter(this,list);
            mRefreshStatusListView.setAdapter(cardMyRadapter);
            cardMyRadapter.setCallUIBack(this);
        }
    }


    @Override
    public void Fuail(String s) {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void Suaxin() {
        getJiaGe();
    }
}
