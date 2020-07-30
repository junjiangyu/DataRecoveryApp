package com.example.data_recovery_app;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {
    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.my_fragment, container, false);

        View aboutUsView = view.findViewById(R.id.aboutUs);
        View webView = view.findViewById(R.id.website);
        View feedbackView = view.findViewById(R.id.feedback);
        View phoneView = view.findViewById(R.id.phone);
        View logOutView = view.findViewById(R.id.logOut);

        ImageButton imgBtn = view.findViewById(R.id.orderbtn);

        TextView textView = view.findViewById(R.id.textView9);

        int loginStatus = LoginStatusData.getA();

        if (loginStatus==0){
            textView.setText("请登录");
            textView.setTextColor(Color.rgb(152, 245, 255));
            //加粗字体
            TextPaint tp = textView.getPaint();
            tp.setFakeBoldText(true);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });


        }


        //打开关于我们页面
        aboutUsView.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(),AboutUs.class);
               startActivity(intent);
            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });





        //打开官网
        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://www.xlysoft.net/");
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        //打开官网
        phoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "15244885897");
                intent.setData(data);
                startActivity(intent);
            }
        });




        //打开feedback页面
        feedbackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FeedbackActivity.class);
                startActivity(intent);

            }
        });


        //打开官网
        logOutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("id",4);
                LoginStatusData.setA(0);
                startActivity(intent);
            }
        });

        return view;

    }

}
