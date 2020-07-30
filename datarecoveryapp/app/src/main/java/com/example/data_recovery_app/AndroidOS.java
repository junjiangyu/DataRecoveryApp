package com.example.data_recovery_app;

import android.os.Build;

public class AndroidOS {

    public static String loadSystemInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("主板：" + Build.BOARD + "\n\n");
        sb.append("系统启动程序版本号：" + Build.BOOTLOADER + "\n\n");
        sb.append("系统定制商：" + Build.BRAND + "\n\n");
        sb.append("cpu指令集：" + Build.CPU_ABI + "\n\n");
        sb.append("cpu指令集2: " + Build.CPU_ABI2 + "\n\n");
        sb.append("设置参数： " + Build.DEVICE + "\n\n");
        sb.append("显示屏参数：" + Build.DISPLAY + "\n\n");
        sb.append("无线电固件版本：" + Build.getRadioVersion() + "\n\n");
        sb.append("硬件识别码：" + Build.FINGERPRINT + "\n\n");
        sb.append(" 硬件名称： " + Build.HARDWARE + "\n\n");
        sb.append(" HOST: " + Build.HOST + "\n\n");
        sb.append("  修订版本列表：" + Build.ID + "\n\n");
        sb.append("  硬件制造商：" + Build.MANUFACTURER + "\n\n");
        sb.append(" 版本：" + Build.MODEL + "\n\n");
        sb.append("  硬件序列号：" + Build.SERIAL + "\n\n");
        sb.append(" 手机制造商：" + Build.PRODUCT + "\n\n");
        sb.append(" 描述Build的标签：" + Build.TAGS + "\n\n");
        sb.append("  TIME:" + Build.TIME + "\n\n");
        sb.append("  builder类型：" + Build.TYPE + "\n\n");
        sb.append("  USER:" + Build.USER + "\n\n");
        return sb.toString();
    }
}
