package com.example.data_recovery_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Message extends AppCompatActivity {

    private ListView lv;
    List<String> contactsList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Cursor c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        lv=(ListView)findViewById(R.id.msgList);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsList);
        lv.setAdapter(adapter);

        getSmsInPhone();




    }

    @SuppressLint("LongLogTag")
    private void getSmsInPhone(){
        final String SMS_URI_ALL   = "content://sms/";

        try{
            ContentResolver cr = getContentResolver();
            String[] projection = new String[]{"_id", "address", "person",
                    "body", "date", "type"};
            Uri uri = Uri.parse(SMS_URI_ALL);
            Cursor cur = cr.query(uri, projection, null, null, "date desc");

            if (cur.moveToFirst()) {
                String name;
                String phoneNumber;
                String smsbody;
                String date;
                String type;

                //    int nameColumn = cur.getColumnIndex("person");
                int phoneNumberColumn = cur.getColumnIndex("address");
                int smsbodyColumn = cur.getColumnIndex("body");
                int dateColumn = cur.getColumnIndex("date");
                int typeColumn = cur.getColumnIndex("type");

                do{
                    phoneNumber = cur.getString(phoneNumberColumn);
                    //    name = cur.getString(nameColumn);    这样获取的联系认为空，所以我改用下面的方法获取
                    smsbody = cur.getString(smsbodyColumn);

                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd hh:mm:ss");
                    Date d = new Date(Long.parseLong(cur.getString(dateColumn)));
                    date = dateFormat.format(d);

                    int typeId = cur.getInt(typeColumn);
                    if(typeId == 1){
                        type = "接收";
                    } else if(typeId == 2){
                        type = "发送";
                    } else {
                        type = "草稿";
                    }

                    contactsList.add(type+"\n"+date+"\n号码: "+phoneNumber+"\n内容: "+smsbody);

                    if(smsbody == null) smsbody = "";
                }   while(cur.moveToNext());
            }
            cur.close();
            cur=null;
        } catch(SQLiteException ex) {
            Log.e("SQLiteException in getSmsInPhone", ex.getMessage());
        }

    }

}