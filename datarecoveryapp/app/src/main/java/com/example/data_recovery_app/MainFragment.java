package com.example.data_recovery_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

public class MainFragment extends Fragment {


    public MainFragment() {
    }

    public static MainFragment newInstance(String param1) {
        MainFragment fragment = new MainFragment();
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

        //获取手机开机的时间
        long time = SystemClock.elapsedRealtime();

        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        String sTime =  hours > 0 ? String.format("%02dh%02dm%02ds", hours, minutes, seconds) : String.format("%02dm%02ds", minutes, seconds);

        Log.v("开机时间",sTime);





        View view = inflater.inflate(R.layout.mainpage_fragment, container, false);

        ImageButton wechat = view.findViewById(R.id.button8);
        ImageButton phoneInfo = view.findViewById(R.id.phoneInfo);
        ImageButton contact = view.findViewById(R.id.contact);
        ImageButton callLog = view.findViewById(R.id.callLog);
        ImageButton msgBtn =  view.findViewById(R.id.msgBtn);
        ImageButton photoBtn = view.findViewById(R.id.photoBtn);


        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WechatRecovery.class);
                startActivity(intent);

            }
        });

        phoneInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhoneInfo.class);
                startActivity(intent);

            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactInfo.class);
                startActivity(intent);

            }
        });

        callLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhoneLog.class);
                startActivity(intent);

            }
        });

        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Message.class);
                startActivity(intent);

            }
        });

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhotoRecovery.class);
                startActivity(intent);

            }
        });


        //创建image数组
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.a);
        images.add(R.drawable.b);
        images.add(R.drawable.c);

        Banner banner = view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置轮播时间
        banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(position==0){
                    Log.i("tag", "你点了第0张轮播图");
                }
                if(position==1){
                    Log.i("tag", "你点了第1张轮播图");
                }
                if(position==2){
                    Log.i("tag", "你点了第2张轮播图");
                }


            }
        });


        return view;


    }


}
