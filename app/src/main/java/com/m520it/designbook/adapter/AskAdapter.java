package com.m520it.designbook.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.m520it.designbook.R;
import com.m520it.designbook.bean.AskContextBean;

import java.util.List;


/**
 * @author Wander
 * @time 2016/7/30  14:45
 * @desc ${TODD}
 */
public class AskAdapter extends BaseAdapter {

    private List<AskContextBean.DataEntity> mDatas;//view集合
    private Context mContext;
    private static int mId;

    public AskAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {

        return mDatas != null ? mDatas.size() : 0;

    }

    @Override
    public Object getItem(int position) {
        return mDatas!=null?mDatas.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ask_list_item, null);
            holder.titleTv = (TextView) convertView.findViewById(R.id.askcontent_title_tv);
            holder.timeTv = (TextView) convertView.findViewById(R.id.ask_time_tv);
            holder.commentTv = (TextView) convertView.findViewById(R.id.ask_comment);
            holder.seeTv = (TextView) convertView.findViewById(R.id.ask_see);
            holder.askIv = (SimpleDraweeView) convertView.findViewById(R.id.ask_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AskContextBean.DataEntity contextBean = mDatas.get(position);
        
        if (contextBean != null) {
            mId = contextBean.getId();


            holder.titleTv.setText(contextBean.getTitle());
            holder.timeTv.setText(contextBean.getCreateTime());

            holder.commentTv.setText(contextBean.getComments()+"");
            Log.v("520it",contextBean.getComments()+"有数据吗");
            holder.seeTv.setText(contextBean.getHits()+"");
            //图片
            String imgUrl = contextBean.getImgUrl();
            Uri uri = Uri.parse(imgUrl);
            holder.askIv.setImageURI(uri);
        }
        return convertView;
    }

    public void setData(List<AskContextBean.DataEntity> askBeans) {
        mDatas = askBeans;
    }


    class ViewHolder {

        public TextView titleTv;
        public TextView timeTv;
        public TextView commentTv;
        public TextView seeTv;
        public SimpleDraweeView askIv;


    }
}
