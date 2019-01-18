package com.example.gouw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gouw.R;
import com.example.gouw.callback.CallCarBack;
import com.example.gouw.entity.LBean;
import com.example.gouw.view.JIA_Jian;

import java.util.List;

public class GoodsMyRadapter extends RecyclerView.Adapter<GoodsMyRadapter.MyVH> {

    private Context context;
    private List<LBean.Cart.Godds> list;
    private CallCarBack callCarBack;

    public void setCallCarBack(CallCarBack callCarBack) {
        this.callCarBack = callCarBack;
    }

    public GoodsMyRadapter(Context context, List<LBean.Cart.Godds> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_goods_item,viewGroup,false);
        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyVH cardMyRadapter, int i) {
        final LBean.Cart.Godds cart = list.get(i);
        cardMyRadapter.textView.setText(cart.title);
        cardMyRadapter.checkBox.setChecked(cart.Exuan);
        cardMyRadapter.jiage.setText("¥：" + cart.price);
        String[] split = cart.images.split("\\|");
        if (split!=null){
            Glide.with(context).load(split[0]).into(cardMyRadapter.imageView);
        }

        cardMyRadapter.jia_jian.setCallback(new JIA_Jian.AddMinusCallback() {
            @Override
            public void sumCallBack(int SUM) {
                cart.productNum= SUM;
                if (callCarBack!=null){
                    callCarBack.shuanew();
                }
            }
        });

        cardMyRadapter.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!cardMyRadapter.checkBox.isChecked()) {//未选中

                    cart.Exuan = false;

                    if (callCarBack != null) {
                        callCarBack.shuaxinitem(false, cart.pos);
                    }

                } else {
                    cart.Exuan = true;

                    //遍历所有数据
                    for (LBean.Cart.Godds cart : list) {

                        //判断集合内商品的选中状态，如果未选中
                        if (!cart.Exuan) {
                            if (callCarBack != null) {
                                callCarBack.shuaxinitem(false, cart.pos);
                            }
                          break;//跳出循环
                        }

                        //如果都选中，设置一级为true
                        if (callCarBack != null) {
                            callCarBack.shuaxinitem(true, cart.pos);
                        }

                  /*  for (LBean.Cart.Godds g: list) {

                        if (!cardMyRadapter.checkBox.isChecked()){

                            g.Exuan=false;

                            if (callCarBack!=null){
                                callCarBack.shuaxinitem(false,cart.pos);
                            }

                            return;
                        }
                        if (cardMyRadapter.checkBox.isChecked()){

                            cart.Exuan=true;

                            if (callCarBack!=null){

                                callCarBack.shuaxinitem(true,cart.pos);
                            }
                        }
                    }*/
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final CheckBox checkBox;
        private  ImageView imageView;
        private final TextView jiage;
        private final JIA_Jian jia_jian;


        public MyVH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.g_title);
            checkBox = itemView.findViewById(R.id.g_check);
            imageView = itemView.findViewById(R.id.g_image);
            jiage = itemView.findViewById(R.id.g_jiage);
            jia_jian = itemView.findViewById(R.id.g_shuliang);
        }
    }


}
