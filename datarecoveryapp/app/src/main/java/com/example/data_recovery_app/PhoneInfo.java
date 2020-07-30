package com.example.data_recovery_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class PhoneInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_info);

        TextView textView = (TextView) findViewById(R.id.phoneInfoTxt);
        ImageButton phoneInfoBack = (ImageButton) findViewById(R.id.phoneBack);

        phoneInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneInfo.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });

        textView.setText(AndroidOS.loadSystemInfo());
    }
}