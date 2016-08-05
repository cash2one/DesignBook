package com.m520it.designbook.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import com.m520it.designbook.bean.CityBean;
import com.m520it.designbook.bean.ProvinceBean;
import com.m520it.designbook.config.CityDBCons;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @time 2016/7/29 0029  21:44
 * @desc 城市数据库DAO
 */
public class CityDAO {
    private final Context mContent;
    private final SQLdm mSql;

    //    private CityDBHelper mHelper;

    public CityDAO(Context context) {
        mContent = context;
//        mHelper = new CityDBHelper(context);
        mSql = new SQLdm();
    }

    /**
     * 查询所有省份,按省份ID排序
     */
    public SparseArray queryAllProvince() {
        SQLiteDatabase readableDatabase= mSql.openDatabase(mContent);
//        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        Cursor query = readableDatabase.query(CityDBCons.TABLE_NAME_PROVINCE,
                null, null, null, null, null, CityDBCons.COLUMN_PROVINCE_ID);

        SparseArray<ProvinceBean> array = new SparseArray<>();
        while (query.moveToNext()) {
            ProvinceBean province = new ProvinceBean();
            //获取省份名
            province.setProvinceName(query.getString(
                    query.getColumnIndex(CityDBCons.COLUMN_PROVINCE_NAME)));
            //获取省份id
            long proID = query.getLong(
                    query.getColumnIndex(CityDBCons.COLUMN_PROVINCE_ID));
            province.setProvinceID(proID);

            array.put((int) proID, province);
        }
        query.close();
        readableDatabase.close();
        return array;
    }

    /**
     * 查询指定省份的城市列表
     *
     * @param provinceID 省份ID
     */
    public List<CityBean> queryCitys(long provinceID) {
//        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        SQLiteDatabase readableDatabase= mSql.openDatabase(mContent);
        Cursor query = readableDatabase.query(CityDBCons.TABLE_NAME_CITY,
                null, CityDBCons.COLUMN_CITY_ID+"=?",
                new String[]{String.valueOf(provinceID)}, null, null, CityDBCons.COLUMN_CITY_ID);

        List<CityBean> cityList = new ArrayList<>();
        while (query.moveToNext()) {
            //获取城市名
          CityBean city = new CityBean();
            city.setCityName(query.getString(
                    query.getColumnIndex(CityDBCons.COLUMN_PROVINCE_NAME)));
            //获取城市id
            city.setCityID(query.getLong(
                    query.getColumnIndex(CityDBCons.COLUMN_PROVINCE_ID)));
            city.setProvinceID(provinceID);
        }
        query.close();
        readableDatabase.close();
        return cityList;
    }

//    public void close(){
//        mHelper.close();
//    }
}
