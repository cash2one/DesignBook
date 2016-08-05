package com.m520it.designbook.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.m520it.designbook.config.SPConstances;

public class SharePreferenceUtil {

    public static final String FILENAME= SPConstances.SP_BASE;

    /**
     * 放入参数String
     * @param context
     * @param title 配置名
     * @param content 配置的value
     */
    public static void putString(Context context, String title, String content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(title,content);
        edit.apply();
    }

    public static String getString(Context context,String title){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(title,"");
    }


    public static void putInt(Context context,String title,int content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(title, content);
        edit.apply();
    }

    public static int getInt(Context context,String title){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(title, 0);
    }


    public static void putLong(Context context,String title,long content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(title, content);
        edit.apply();
    }

    public static long getLong(Context context,String title){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getLong(title, 0);
    }

    //添加一个boolean的 添加和获取
    public static void putBoolean(Context context,String title,Boolean content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(title, content);
        edit.apply();
    }

    public static Boolean getBoolean(Context context,String title){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean(title, false);
    }

}
