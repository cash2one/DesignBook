package com.m520it.designbook.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m520it.designbook.R;
import com.m520it.designbook.activity.AskDetailActivity;
import com.m520it.designbook.adapter.AskAdapter;
import com.m520it.designbook.bean.AskContextBean;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * @author Wander
 * @time 2016/7/30  11:31
 * @desc ${TODD}
 */
public class AskFragment extends Fragment {

    @BindView(R.id.title_tablayout)
    TabLayout mTitleTablayout;
    @BindView(R.id.content_vp)
    ViewPager mContentVp;
    @BindView(R.id.pulltozoom)
    PullToZoomScrollViewEx mPulltozoom;


    private ArrayList<String> mTitleList;//标题集合
    private ArrayList<View> mViewList;//view集合
    private long mId;

    private LayoutInflater mInflater;
    private View view1, view2, view3;

    private AskAdapter mListAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_ask, null);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
        loadData();

    }

    private void loadData() {
        ResultBeanCallback<List<AskContextBean.DataEntity>> callback = new ResultBeanCallback<List<AskContextBean.DataEntity>>() {
            @Override
            public ResultBean<List<AskContextBean.DataEntity>> parseNetworkResponse(Response response, int id) throws Exception {

                ResultBean<List<AskContextBean.DataEntity>> resultBean = new Gson().fromJson(response.body().string(), new TypeToken<ResultBean<List<AskContextBean.DataEntity>>>() {
                }.getType());

                return resultBean;
            }
        };


        callback.setResultListener(new IResultBeanListener<List<AskContextBean.DataEntity>>() {
            @Override
            public void onError(String errorMsg, int id) {
                Log.v("520it", "请求失败");
            }

            @Override
            public void onSucceed(ResultBean<List<AskContextBean.DataEntity>> response, int id) {
                Log.v("520it", "jiexi chengg " + response.getData());

                Message.obtain(mHanlder, id, response.getData()).sendToTarget();
            }
        });
        Controller.doGetAsync(NetWorkCons.URL_ASK_ACTIVE, null, IDMessage.ASK_CONTENT_ACTION, callback);
        Controller.doGetAsync(NetWorkCons.URL_ASK_NEW, null, IDMessage.ASK_CONTENT_ACTION, callback);
        Controller.doGetAsync(NetWorkCons.URL_ASK_WAIT, null, IDMessage.ASK_CONTENT_ACTION, callback);

    }

    //给adapter设置数据
    private void setList(List<AskContextBean.DataEntity> askBeans) {
        mListAdapter.setData(askBeans);
        mListAdapter.notifyDataSetChanged();

    }


    private void initView() {
        mViewList = new ArrayList<>();
        mTitleList = new ArrayList<>();

    }

    private void initData() {

        loadViewForPullToZoomScrollView(mPulltozoom);
        setPullToZoomViewLayoutParams(mPulltozoom);



        //这是listview的adapter
        mListAdapter = new AskAdapter(getContext());

        mInflater = LayoutInflater.from(getContext());
        //动态的向viewPager添加listview
        View view1 = mInflater.inflate(R.layout.ask_list_layout, null);
        View view2 = mInflater.inflate(R.layout.ask_list_layout, null);
        View view3 = mInflater.inflate(R.layout.ask_list_layout, null);
        ListView listView1 = (ListView) view1.findViewById(R.id.ask_lv);
        ListView listView2 = (ListView) view2.findViewById(R.id.ask_lv);
        ListView listView3 = (ListView) view3.findViewById(R.id.ask_lv);

        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);

        listViewSetInAdapter(listView1);
        listViewSetInAdapter(listView2);
        listViewSetInAdapter(listView3);

        mTitleList.add("最近活跃");
        mTitleList.add("最新提问");
        mTitleList.add("等待回答");

        setTabLayout();


    }
    private void loadViewForPullToZoomScrollView(PullToZoomScrollViewEx scrollView) {

        View headView = LayoutInflater.from(getContext()).inflate(R.layout.ask_head_view, null);
        View zoomView = LayoutInflater.from(getContext()).inflate(R.layout.ask_head_zoom_view, null);
        // View contentView = LayoutInflater.from(this).inflate(R.layout.content_view, null);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        // scrollView.setScrollContentView(contentView);
    }

    // 设置头部的View的宽高。
    private void setPullToZoomViewLayoutParams(PullToZoomScrollViewEx scrollView) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
       getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth,
                (int) (9.0*(mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }


    private void listViewSetInAdapter(ListView listView) {

        listView.setAdapter(mListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), AskDetailActivity.class);
                AskContextBean.DataEntity item = (AskContextBean.DataEntity) mListAdapter.getItem(position);
                if(item!=null) {
                    intent.putExtra(AskDetailActivity.QUESTION_ID, item.getId());
                    LogUtils.v("dargon","item id = "+item.getId());
                    startActivity(intent);
                }

                //TODO
            }
        });
    }


    private void setTabLayout() {
        mTitleTablayout.setTabMode(TabLayout.MODE_FIXED);
        /**
         * 设置tablayout的监听
         */
        mTitleTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mContentVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTitleTablayout.addTab(mTitleTablayout.newTab().setText(mTitleList.get(0)));
        mTitleTablayout.addTab(mTitleTablayout.newTab().setText(mTitleList.get(1)));
        mTitleTablayout.addTab(mTitleTablayout.newTab().setText(mTitleList.get(2)));
        Adapter adapter = new Adapter(getContext(), mTitleList, mViewList);
        mContentVp.setAdapter(adapter);
        mTitleTablayout.setupWithViewPager(mContentVp);
        //2.设置标题来自adapter
        mTitleTablayout.setTabsFromPagerAdapter(adapter);
        //给viewpager设置监听
        TabLayout.TabLayoutOnPageChangeListener listener = new TabLayout.TabLayoutOnPageChangeListener(mTitleTablayout);
        mContentVp.addOnPageChangeListener(listener);
    }


    private class Adapter extends PagerAdapter {
        private Context mContext;

        public Adapter(Context context, ArrayList<String> titleList, ArrayList<View> viewList) {
            mContext = context;
            mTitleList = titleList;
            mViewList = viewList;
        }


        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(mViewList.get(position));
            return mViewList.get(position);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(mViewList.get(position));

        }

        /**
         * 获取标题
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }

    private MyHanlder mHanlder = new MyHanlder(this);

    private class MyHanlder extends Handler {
        WeakReference<AskFragment> mAskFragment;

        public MyHanlder(AskFragment askFragment) {
            mAskFragment = new WeakReference<AskFragment>(askFragment);
        }

        @Override
        public void handleMessage(Message msg) {

            if (mAskFragment.get() == null) {
                return;
            }

            switch (msg.what) {
                case IDMessage.ASK_CONTENT_ACTION:
                    mAskFragment.get().setList((List<AskContextBean.DataEntity>) msg.obj);
                    break;

            }


        }
    }
}
