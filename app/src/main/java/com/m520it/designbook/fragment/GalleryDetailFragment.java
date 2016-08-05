package com.m520it.designbook.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m520it.designbook.R;
import com.m520it.designbook.adapter.GalleryDetailAdapter;
import com.m520it.designbook.bean.GalleryDataBean;
import com.m520it.designbook.bean.ResultBean;
import com.m520it.designbook.callback.ResultBeanCallback;
import com.m520it.designbook.config.IDMessage;
import com.m520it.designbook.config.NetWorkCons;
import com.m520it.designbook.controller.Controller;
import com.m520it.designbook.protocol.IResultBeanListener;
import com.m520it.designbook.utils.LogUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by dragon on 2016/7/31.
 */
public class GalleryDetailFragment extends Fragment {
    private android.widget.TextView tvdesc;
    private android.widget.ImageView ivarrow;
    private com.facebook.drawee.view.SimpleDraweeView ivfacepic;
    private android.widget.TextView tvusername;
    private android.widget.TextView tvmoneydesc;
    private android.widget.ImageView ivphone;
    private android.widget.RelativeLayout rlask;
    private android.support.design.widget.TabLayout titletablayout;
    private android.support.v4.view.ViewPager contentvp;
    private PullToZoomScrollViewEx zoonscroll;

    private long mId ; //外界传进来
    private MyHandler myHandler;
    private View mZoomView;

    private List<Fragment> fragList;
    private List<String> titleList;//标题
    private TabLayout mTitle_layout;
    private ViewPager mViewPager;
    private GalleryDetailAdapter mGalleryDetailAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_gallerydetail, null);
        this.zoonscroll = (PullToZoomScrollViewEx) view.findViewById(R.id.zoonscroll);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
        if(myHandler==null) {
            myHandler = new MyHandler(this);
        }
        //加载数据
        loadData();
    }

    private void initUI() {
        loadViewForPullToZoomScrollView(zoonscroll);
        setPullToZoomViewLayoutParams(zoonscroll);
    }

    private void initViewPage(View contentView) {
        mTitle_layout = (TabLayout) contentView.findViewById(R.id.title_tablayout);
        mViewPager = (ViewPager) contentView.findViewById(R.id.content_vp);
        //初始化各个fragment
        GalleryAskFragment askFrag = new GalleryAskFragment();
        GalleryCollectFragment collectFrag = new GalleryCollectFragment();
        //将fragment加入到列表中
        fragList = new ArrayList<>();
        fragList.add(askFrag);
        fragList.add(collectFrag);
        //将title加入到列表中
        titleList = new ArrayList<>();
        titleList.add("问答");
        titleList.add("收藏");
        //设置tablayout的模式
        mTitle_layout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        mTitle_layout.addTab(mTitle_layout.newTab().setText(titleList.get(0)));
        mTitle_layout.addTab(mTitle_layout.newTab().setText(titleList.get(1)));
        //如果是fragment里面的fragment   那adapter就得用getChildFragmentManager()
        mGalleryDetailAdapter = new GalleryDetailAdapter(getChildFragmentManager(),fragList,titleList);

        //viewpager加载adapter
        mViewPager.setAdapter(mGalleryDetailAdapter);
        //TabLayout加载viewpager
        mTitle_layout.setupWithViewPager(mViewPager);
        //2.设置标题来自adapter
        mTitle_layout.setTabsFromPagerAdapter(mGalleryDetailAdapter);
        mTitle_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //TODO 滑动有bug
//        mViewPager.requestDisallowInterceptTouchEvent(true);

        //给viewpager设置监听
        TabLayout.TabLayoutOnPageChangeListener listener = new TabLayout.TabLayoutOnPageChangeListener(mTitle_layout);
        mViewPager.addOnPageChangeListener(listener);

    }
//    http://www.shejiben.com/mobile/index.php?id=2372984&uid=0&action=detail&module=works&askNum=5&colNum=5&
//    http://www.shejiben.com/mobile/index.php?id=2372983&uid=0&action=detail&module=works&askNum=5&colNum=5&
//    http://www.shejiben.com/mobile/index.php?id=2372982&uid=0&action=detail&module=works&askNum=5&colNum=5&

//    http://www.shejiben.com/mobile/index.php?id=2366986&uid=0&action=detail&module=works&askNum=5&colNum=5&
    private void loadData() {

        ResultBeanCallback<GalleryDataBean> callback = new ResultBeanCallback<GalleryDataBean>() {
            @Override
            public ResultBean<GalleryDataBean> parseNetworkResponse(Response response, int id) throws Exception {
                ResultBean<GalleryDataBean> resultBean  = new Gson().fromJson(response.body().string(),new TypeToken<ResultBean<GalleryDataBean>>(){}.getType());
                return resultBean;
            }
        };

        callback.setResultListener(new IResultBeanListener<GalleryDataBean>() {
            @Override
            public void onError(String errorMsg, int id) {
                LogUtils.v("dargon","galleryDetailFragment数据获取失败"+errorMsg);
            }

            @Override
            public void onSucceed(ResultBean<GalleryDataBean> response, int id) {
                //获取到的数据 全部存起来
                myHandler.obtainMessage(IDMessage.GALLERY_DETAIL,response.getData()).sendToTarget();
            }
        });

            Controller.doGetAsync(NetWorkCons.getGalleryDetailUrl(mId+""),null, IDMessage.GALLERY_DETAIL,callback);




    }

    private void loadViewForPullToZoomScrollView(PullToZoomScrollViewEx scrollView) {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.head_view, null);
        mZoomView = LayoutInflater.from(getActivity()).inflate(R.layout.head_zoom_view, null);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.content_view, null);

        //内部的其他信息
        initContentView(contentView);
        initViewPage(contentView);

        scrollView.setHeaderView(headView);
        scrollView.setZoomView(mZoomView);
        scrollView.setScrollContentView(contentView);



    }

    private void initContentView(View contentView) {

        this.contentvp = (ViewPager) contentView.findViewById(R.id.content_vp);
        this.titletablayout = (TabLayout) contentView.findViewById(R.id.title_tablayout);
        this.rlask = (RelativeLayout) contentView.findViewById(R.id.rl_ask);
        this.ivphone = (ImageView) contentView.findViewById(R.id.iv_phone);
        this.tvmoneydesc = (TextView) contentView.findViewById(R.id.tv_money_desc);
        this.tvusername = (TextView) contentView.findViewById(R.id.tv_username);
        this.ivfacepic = (SimpleDraweeView) contentView.findViewById(R.id.iv_facepic);
        this.ivarrow = (ImageView) contentView.findViewById(R.id.iv_arrow);
        this.tvdesc = (TextView) contentView.findViewById(R.id.tv_desc);

    }

    // 设置头部的View的宽高。
    private void setPullToZoomViewLayoutParams(PullToZoomScrollViewEx scrollView) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth,
                (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }

    public void setPageId(long id) {
        this.mId= id ;
    }



    private static class  MyHandler extends Handler{
        private WeakReference<GalleryDetailFragment> reference ;

        public MyHandler(GalleryDetailFragment fragment) {
            reference = new WeakReference<GalleryDetailFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if(reference.get()==null) {
                return ;
            }
            switch (msg.what){
                case IDMessage.GALLERY_DETAIL:
                    reference.get().handlerGalleryDetail((GalleryDataBean)msg.obj);
                    break;
            }
        }
    }

    private void handlerGalleryDetail(GalleryDataBean bean) {


        //获取到数据了 设置起来
        tvmoneydesc.setText(bean.getMemberInfo().getFeeRand());//费用

        this.rlask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        this.ivphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
       tvusername.setText(bean.getMemberInfo().getNick());//TODO不确定是不是这个
       ivfacepic.setImageURI(Uri.parse(bean.getMemberInfo().getFacePic()));
        LogUtils.v("dargon",bean.getMemberInfo().getFacePic());
        ((SimpleDraweeView)mZoomView).setImageURI(Uri.parse(bean.getImgUrl()));

        //TODO
        //下面两个要默认隐藏的 如果存在 才显示出来的
        if(TextUtils.isEmpty(bean.getComment())) {
            this.tvdesc.setVisibility(View.GONE);
        }else{
             this.tvdesc.setText(bean.getComment());
        }
//        this.ivarrow


    }

}
