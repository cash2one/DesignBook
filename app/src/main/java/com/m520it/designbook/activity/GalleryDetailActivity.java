package com.m520it.designbook.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.m520it.designbook.R;
import com.m520it.designbook.adapter.GalleryDetailFragmentAdapter;
import com.m520it.designbook.fragment.GalleryDetailFragment;

import java.util.ArrayList;

/**
 * Created by dragon on 2016/7/31.
 */
public class GalleryDetailActivity extends FragmentActivity {

    public static final String GALLERY_LISTID = "gallerylistid";
    private com.ecloud.pulltozoomview.PullToZoomScrollViewEx zoonscroll;
    private android.support.v4.view.ViewPager vpgallery;

    public static final String GALLERY_POSITION = "gallery_position";
    private int mPosition;
    private GalleryDetailFragmentAdapter mAdapter;
    private ArrayList<Integer> mListId;
    private ArrayList<GalleryDetailFragment> mFragments;
    private long mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);
        //要拿到 整体页面数量  再拿到当前的itemID 循环使用这些fragment就好了
        getPassValue();
        initUi();
        //加载
        initViewPage();
    }

    private void initViewPage() {
        //创建三个fragment再一直复用好了
        mFragments = new ArrayList<GalleryDetailFragment>();
        for(int i = 0; i < 7; i++) {
            GalleryDetailFragment fragment = new GalleryDetailFragment();
            mFragments.add(fragment);

            fragment.setPageId(mListId.get(mPosition++));
        }
        mAdapter = new GalleryDetailFragmentAdapter(getSupportFragmentManager(), mFragments);

        vpgallery.setAdapter(mAdapter);
        vpgallery.setCurrentItem(3);
        vpgallery.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    protected void initUi() {
        this.vpgallery = (ViewPager) findViewById(R.id.vp_gallery);
    }

    public void getPassValue() {
        //要从外界拿到一些数据的 比如当前点击的对象id 可能还需要其他的东西
        //传位置进来好了
        mPosition =getIntent().getIntExtra(GALLERY_POSITION,0);
        mPosition-=3;
        mListId =  getIntent().getIntegerArrayListExtra(GALLERY_LISTID);
//        mId = mListId[mPosition];

    }

}
