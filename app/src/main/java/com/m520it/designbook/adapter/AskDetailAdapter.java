package com.m520it.designbook.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.m520it.designbook.R;
import com.m520it.designbook.bean.AskDetailBean;

import java.util.List;

/**
 * Created by dragon on 2016/7/30.
 */
public class AskDetailAdapter extends BaseAdapter {
    //数据容器
    private List<AskDetailBean.DataBean.CommentListBean> mData ;
    private Context mContext ;

    public AskDetailAdapter(List<AskDetailBean.DataBean.CommentListBean> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData!=null?mData.size():0;
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
        ViewHolder holder ;
        if(convertView==null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_askdetail,null);
               holder.rivFacepic = (SimpleDraweeView)convertView.findViewById(R.id.riv_facepic);
               holder.tvUsername = (TextView) convertView.findViewById(R.id.tv_username);
               holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
               holder.tvResponse = (TextView) convertView.findViewById(R.id.tv_response);
               holder.tvPraisenum = (TextView) convertView.findViewById(R.id.tv_praisenum);
               holder.tvDesc = (TextView) convertView.findViewById(R.id.tv_desc);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //添加数据过去
        AskDetailBean.DataBean.CommentListBean bean = mData.get(position);

        Uri uri = Uri.parse(bean.getFacePic());
        holder.rivFacepic.setImageURI(uri);

        holder.tvUsername.setText(bean.getNick());

        holder.tvPraisenum.setText(bean.getPraiseNum() +"");
        holder.tvTime.setText(bean.getCreateTime());

        holder.tvPraisenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        holder.tvResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        holder.tvDesc.setText(bean.getContent());

        return convertView;
    }

    class ViewHolder{
        SimpleDraweeView rivFacepic;//用户头像
        TextView tvUsername;//用户名
        TextView tvTime;//发表时间
        TextView tvResponse;//点击回复
        TextView tvPraisenum;//点赞
         TextView tvDesc;//内容
    }
}
