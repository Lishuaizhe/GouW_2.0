package com.example.gouw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gouw.R;
import com.example.gouw.activity.MainActivity;

public class JIA_Jian extends LinearLayout {

    private TextView jian;
    private TextView jia;
    private EditText editText;
    private int sum =1;

    public JIA_Jian(Context context) {
        this(context,null);
    }

    public JIA_Jian(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public JIA_Jian(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.zidingyi,this,true);

        jia = view.findViewById(R.id.jia);
        jian = view.findViewById(R.id.jian);
        editText = view.findViewById(R.id.g_edit_quera);
        editText.setText("1");

        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sum++;
                editText.setText(sum+"");
                if (callback!=null){
                    callback.sumCallBack(sum);
                }
            }
        });

        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sum--;
                if (sum==0){
                    sum=1;
                    Toast.makeText(getContext(),"你还买不买了！",Toast.LENGTH_SHORT).show();
                }
                editText.setText(sum+"");
                if (callback!=null){
                    callback.sumCallBack(sum);
                }
            }
        });
    }


    private AddMinusCallback callback;

    public void setCallback(AddMinusCallback callback) {
        this.callback = callback;
    }

    public interface AddMinusCallback{
        void sumCallBack(int SUM);
    }



}
