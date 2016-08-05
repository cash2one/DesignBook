package com.m520it.designbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;
import com.m520it.designbook.activity.GalleryDetailActivity;
import com.m520it.designbook.bean.BitmapCache;
import com.m520it.designbook.bean.GalleryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁家明
 * @time 2016/7/30  19:01
 * @desc 图库
 */
public class GalleryAdapter extends BaseAdapter{
    private List<GalleryBean.DataEntity> list;
    ArrayList<Integer> mListId ;

    public List<GalleryBean.DataEntity> getList() {
        return list;
    }

    public void setList(List<GalleryBean.DataEntity> list) {
        this.list = list;

        //huoqu s获取所有的id
        mListId = new ArrayList<Integer>();
        for (GalleryBean.DataEntity dataEntity:list){
            mListId.add(dataEntity.getId());
        }
    }

    private Context mContext;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public GalleryAdapter(Context context){
        this.mContext=context;


        mRequestQueue= Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue,new BitmapCache());
    }
    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list!=null?list.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {



        SimpleDraweeView img;
        if (convertView==null){
            img = new SimpleDraweeView(mContext);
            img.setMinimumHeight(10);
            img.setMinimumWidth(5);
            img.setAspectRatio(1.03f);
        }else {
            img = (SimpleDraweeView) convertView;
        }
        final String imgUrl = list.get(position).getImgUrl();

        if (imgUrl != null && !imgUrl.equals("")) {

            //img = (SimpleDraweeView) convertView.findViewById(R.id.gallery_img_left);
            img.setImageURI(imgUrl, mImageLoader);
 //           img .setImageUrl(imgUrl, mImageLoader);
//            holder.img_right.setDefaultImageResId(R.mipmap.ico_no_content);
//            holder.img_right.setErrorImageResId(R.mipmap.ico_no_content);
//            holder.img_right.setImageUrl(imgUrl, mImageLoader);

        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GalleryDetailActivity.class);
//                intent.setFlags(Intent.f)
                intent.putExtra(GalleryDetailActivity.GALLERY_POSITION,position);
                intent.putExtra(GalleryDetailActivity.GALLERY_LISTID,mListId);
                mContext.startActivity(intent);
            }
        });

        return img;
    }
}
