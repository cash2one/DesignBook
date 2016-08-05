package com.m520it.designbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author JJ
 * @time 2016/7/29 0029  21:51
 * @desc 城市数据库打开帮助类
 */
public class CityDBHelper extends SQLiteOpenHelper{

    public static final int VERSION=1;
    public static final String DB_NAME="China";//数据库名
    public static final String TABLE_NAME_PROVINCE="S_Province";//省份表名
    public static final String TABLE_NAME_CITY="S_City";//城市表名

    public CityDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE "+TABLE_NAME_PROVINCE+"(" +
//                "ProvinceID bigint NOT NULL PRIMARY KEY ," +
//                "ProvinceName VARCHAR(50));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
