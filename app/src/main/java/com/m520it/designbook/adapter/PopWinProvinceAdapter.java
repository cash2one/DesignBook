package com.m520it.designbook.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.m520it.designbook.R;
import com.m520it.designbook.bean.ProvinceBean;
import com.m520it.designbook.utils.LogUtils;

import java.util.List;

/**
 * @author JJ
 * @time 2016/8/4 0004  13:58
 * @desc 所有省份的适配器
 */
public class PopWinProvinceAdapter extends BaseAdapter {
    private List<ProvinceBean> mData;
    private Context mContext;

    public PopWinProvinceAdapter(Context context, List<ProvinceBean> data) {
        mData = data;
        mContext = context;
    }

    public void addData(List<ProvinceBean> data) {
        if (mData == null) {
            mData = data;
        } else {
            mData.addAll(data);
        }
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView city;
        if (convertView == null) {
            city = (TextView) View.inflate(mContext, R.layout.search_designer_tv, null);
        } else {
            city = (TextView) convertView;
        }
        ProvinceBean provinceBean = mData.get(position);
        LogUtils.vAdv(provinceBean.toString());

        city.setText(mData.get(position).getProvinceName());
        return city;
    }
}
