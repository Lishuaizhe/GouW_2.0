package com.example.gouw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.gouw.R;
import com.example.gouw.callback.CallCarBack;
import com.example.gouw.callback.CallUIBack;
import com.example.gouw.entity.LBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CardMyRadapter extends RecyclerView.Adapter<CardMyRadapter.MyVH> implements CallCarBack {

    private Context context;
    private List<LBean.Cart> list;
    public CallUIBack callUIBack;

    public void setCallUIBack(CallUIBack callUIBack) {
        this.callUIBack = callUIBack;
    }

    public CardMyRadapter(Context context, List<LBean.Cart> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_card,viewGroup,false);
        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyVH cardMyRadapter, int i) {
        final LBean.Cart cart = list.get(i);
        cardMyRadapter.textView.setText(cart.sellerName);
        cardMyRadapter.checkBox.setChecked(cart.Yxuan);

        for (LBean.Cart.Godds godds : cart.list) {
            godds.pos = i;//
        }

        GoodsMyRadapter goodsMyRadapter = new GoodsMyRadapter(context,cart.list);
        cardMyRadapter.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        cardMyRadapter.recyclerView.setAdapter(goodsMyRadapter);
        goodsMyRadapter.setCallCarBack(this);

        cardMyRadapter.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 cart.Yxuan = cardMyRadapter.checkBox.isChecked();

                 for (LBean.Cart.Godds g: cart.list) {
                    g.Exuan=cart.Yxuan;
                }

                notifyDataSetChanged();

                if (callUIBack!=null){
                    callUIBack.Suaxin();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public void shuaxinitem(boolean s, int p) {
        list.get(p).Yxuan=s;



        notifyDataSetChanged();

        if (callUIBack!=null){
            callUIBack.Suaxin();
        }

    }

    @Override
    public void shuanew() {
        if (callUIBack!=null){
            callUIBack.Suaxin();
        }
    }


    public class MyVH extends RecyclerView.ViewHolder {

        private final TextView textView;
        private CheckBox checkBox;
        private final XRecyclerView recyclerView;


        public MyVH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.shangjiamingc);
            checkBox = itemView.findViewById(R.id.xuanzhoshangjia);
            recyclerView = itemView.findViewById(R.id.x_recycler_view);
        }
    }


}
