package com.example.data_recovery_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class PhotoRecovery extends Activity {
    private GridView gridView;
    private Context context;
    List<String> paths = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_recovery);
        context=this;
        getAllImagePath();

        gridView=(GridView) findViewById(R.id.gridView1);

        adapter = new MyGridViewAdapter();
        gridView.setAdapter(adapter);

        //保存图片文件到SD卡中

        for (int i=0;i<paths.size();i++){
            String filename = getFileName(paths.get(i));


            try {
                copyFile(paths.get(i), "sdcard/photoRecovery/"  + filename + "_" + i +".jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public String getFileName(String pathandname){
        int start=pathandname.lastIndexOf("/");
        int end=pathandname.lastIndexOf(".");
        if (start!=-1 && end!=-1) {
            return pathandname.substring(start+1, end);
        }
        else {
            return null;
        }
    }


    public void copyFile(String selectedImagePath, String string) throws IOException {

        InputStream in = new FileInputStream(selectedImagePath);
        OutputStream out = new FileOutputStream(string);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    private void getAllImagePath() {
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        //遍历相册
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            //将图片路径添加到集合
            paths.add(path);
        }
        cursor.close();
    }




    private MyGridViewAdapter adapter;


    class MyGridViewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return paths.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder vh;
            if(convertView==null){
                //这里面的item是一个自定义的View继承线性布局，继承什么布局不重要，
                // 重要的是将item的宽高设置成一样；感觉这个效果项目中很多地方都能用到
                convertView=View.inflate(context, R.layout.image_list, null);
                vh=new ViewHolder();
                vh.imageView=(ImageView) convertView.findViewById(R.id.imgitem);
                convertView.setTag(vh);
            }else{
                vh=(ViewHolder) convertView.getTag();
            }
            //当前item要加载的图片路径
            String path=paths.get(position);

            //使用谷歌官方提供的Glide加载图片
            Glide.with(context).load(new File(path)).diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().dontAnimate().into(vh.imageView);

            return convertView;

        }


        class ViewHolder{
            ImageView imageView;
        }


    }}