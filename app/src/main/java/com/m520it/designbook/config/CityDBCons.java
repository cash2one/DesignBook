package com.m520it.designbook.config;

/**
 * @author JJ
 * @time 2016/7/29 0029  21:56
 * @desc 城市数据库常量
 */
public class CityDBCons {
    public static final int VERSION=1;
    public static final String DB_NAME="China";//数据库名
    public static final String TABLE_NAME_PROVINCE="S_Province";//省份表名
    public static final String TABLE_NAME_CITY="S_City";//城市表名

    public static final String COLUMN_PROVINCE_ID="ProvinceID";//省份ID
    public static final String COLUMN_PROVINCE_NAME="ProvinceName";//省份名

    public static final String COLUMN_CITY_ID="CityID";//城市ID
    public static final String COLUMN_CITY_NAME="CityName";//城市名
    public static final String COLUMN_CITY_PROID="ProvinceID";//省份ID

}
