package com.m520it.designbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.m520it.designbook.activity.WebActivity;
import com.m520it.designbook.bean.BannerBean;

import java.util.List;

/**
 * @author JJ
 * @time 2016/7/31 0031  14:58
 * @desc ${TODD}
 */
public class CaseBannerPagerAdapter extends PagerAdapter {
    private List<BannerBean> mBannerList;
    private SparseArray<SimpleDraweeView> mImgList;
    private Context mContext;

    public void setBannerList(List<BannerBean> bannerList) {
        mBannerList = bannerList;
    }

    public CaseBannerPagerAdapter(Context context) {
        mContext = context;
        mImgList = new SparseArray<>();
    }

    public void setUrls(List<BannerBean> bannerList) {
        mBannerList = bannerList;
    }

    @Override
    public int getCount() {
        return mBannerList != null ? mBannerList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //图片
        SimpleDraweeView imageView = new SimpleDraweeView(mContext);
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        imageView.setLayoutParams(params);
        imageView.setImageURI(Uri.parse(mBannerList.get(position).getImgUrl()));
        //设置点击事件进入广告模块
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra(WebActivity.URL, mBannerList.get(position).getUrl());
//                intent.putExtra(WebActivity.TITLE, mContext.getResources().getString());
                mContext.startActivity(intent);
            }
        });
        container.addView(imageView);
        mImgList.put(position, imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImgList.get(position));
    }
}
