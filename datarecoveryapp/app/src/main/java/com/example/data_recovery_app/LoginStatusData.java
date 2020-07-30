package com.example.data_recovery_app;

public class LoginStatusData{
    private static int loginStatus = 0;

    public static int getA() {
        return loginStatus;
    }

    public static void setA(int a) {
        loginStatus = a;
    }

}