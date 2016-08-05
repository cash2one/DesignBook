package com.m520it.designbook.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by dragon on 2016/7/29.
 */
public class GuideAdapter extends PagerAdapter {
    private ArrayList<ImageView> mDatas ;//数据容器

    public GuideAdapter(ArrayList<ImageView> datas) {
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas!=null?mDatas.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(mDatas!=null) {
            ImageView iv = mDatas.get(position);
            container.addView(iv);
            return iv ;
        }

        return super.instantiateItem(container, position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(mDatas!=null) {
            ImageView imageView = mDatas.get(position);
            container.removeView(imageView);
        }
    }
}
