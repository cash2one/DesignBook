package com.m520it.designbook.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * @desc 问题详情里面的图片viewpager的适配器
 * Created by dragon on 2016/7/31.
 */
public class PicturePageAdapter extends PagerAdapter {
    private ArrayList<SimpleDraweeView> mData ;
    public PicturePageAdapter(ArrayList<SimpleDraweeView> pics) {
        mData =  pics;
    }

    @Override
    public int getCount() {
        return mData!=null?mData.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mData.get(position));
        return mData.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView(mData.get(position));
    }
}
