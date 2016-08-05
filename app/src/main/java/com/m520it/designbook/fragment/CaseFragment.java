package com.m520it.designbook.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m520it.designbook.R;
import com.m520it.designbook.activity.CaseDetailActivity;
import com.m520it.designbook.activity.SubjectActivity;
import com.m520it.designbook.adapter.CaseAdapter;
import com.m520it.designbook.adapter.CaseBannerPagerAdapter;
import com.m520it.designbook.bean.BannerBean;
import com.m520it.designbook.bean.CaseDataBean;
import com.m520it.designbook.bean.CasesBean;
import com.m520it.designbook.bean.JiexiBean;
import com.m520it.designbook.bean.ResultBean;
import com.m520it.designbook.callback.ResultBeanCallback;
import com.m520it.designbook.config.IDMessage;
import com.m520it.designbook.config.NetWorkCons;
import com.m520it.designbook.controller.Controller;
import com.m520it.designbook.protocol.IResultBeanListener;
import com.m520it.designbook.utils.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import okhttp3.Response;

/**
 * @author JJ
 * @time 2016/7/30 0030  10:20
 * @desc 案例fragment
 */
public class CaseFragment extends Fragment {
    private ImageView mIv_search;//搜索按钮
    private PtrClassicFrameLayout mPtr_list;//下拉刷新控件
    private ListView mLv_case;//主列表
    private ViewPager mVp_banner;//广告
    private HorizontalScrollView mHorscr_special;//专题横向scrll
    private LinearLayout mLl_special;//scroll里面装载item的layout

    private Myhandler mHandler;
    private CaseAdapter mAdapter;
    private CaseBannerPagerAdapter mBannerAdapter;//广告适配器
    private android.widget.LinearLayout llindicate;//图片指示器
    private View mHead;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_case, null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHandler = new Myhandler(this);
        initView();
        loadData();
    }

    private void initView() {
        this.mIv_search = (ImageView) getActivity().findViewById(R.id.iv_search);
        this.mLv_case = (ListView) getActivity().findViewById(R.id.lv_case);
        this.mPtr_list = (PtrClassicFrameLayout) getActivity().findViewById(R.id.ptr_list);
        //头布局,包括广告和专题,
        mHead = View.inflate(getActivity(), R.layout.include_case, null);
        mVp_banner = (ViewPager) mHead.findViewById(R.id.vp_banner);
        mLl_special = (LinearLayout) mHead.findViewById(R.id.ll_special);
        this.llindicate = (LinearLayout) mHead.findViewById(R.id.ll_indicate);//指示器
        //设置广告适配器
        mBannerAdapter = new CaseBannerPagerAdapter(getActivity());
        mVp_banner.setAdapter(mBannerAdapter);

        mHorscr_special = (HorizontalScrollView) mHead.findViewById(R.id.horscr_special);
        setupViews();
    }


    protected void setupViews() {
        final StoreHouseHeader header = new StoreHouseHeader(getActivity());
        header.setPadding(0, LocalDisplay.dp2px(15), 0, 0);
        //        header.setPadding(0, LocalDisplay.dp2px(20), 0, LocalDisplay.dp2px(20));
        header.initWithString("CUBE APP");
        header.initWithStringArray(R.array.storehouse);
        //关头时间
        mPtr_list.setDurationToCloseHeader(1500);
        //设置头
        mPtr_list.setHeaderView(header);
        //设置头回调
        mPtr_list.addPtrUIHandler(header);

        //设置下拉监听
        mPtr_list.setPtrHandler(new PtrHandler() {
            //是否可以刷新
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                //return true;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                // TODO: 2016/7/30 0030 刷新页面
                mPtr_list.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //回调完成
                        mPtr_list.refreshComplete();
                    }
                }, 1500);
            }
        });

        /***
         * 列表adapter
         */
        mAdapter = new CaseAdapter(getActivity()) {
            @Override
            protected void openDesignerIndex(CasesBean cases) {
                //todo 打开设计师页面
            }

            @Override
            public void openAllCaseActivity() {
                // TODO: 2016/8/2 0002 打开全部案例页面

            }
        };

        /**
         * 打开案例详细页面
         */
        mLv_case.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //adapter.getItem(position).onClick(view);
                CasesBean item = (CasesBean) mAdapter.getItem(position-1);
                Intent intent = new Intent(getActivity(), CaseDetailActivity.class);
                intent.putExtra(CaseDetailActivity.ID,item.getId());
                startActivity(intent);
            }
        });
        mLv_case.setAdapter(mAdapter);

        //添加头广告
        mLv_case.addHeaderView(mHead);

    }

    private void loadData() {
        ResultBeanCallback<CaseDataBean> callback = new ResultBeanCallback<CaseDataBean>() {
            @Override
            public ResultBean<CaseDataBean> parseNetworkResponse(Response response, int id) throws Exception {
                //                new JsonObject()
                ResultBean<CaseDataBean> resultBean = new Gson().fromJson(response.body().string(),
                        new TypeToken<ResultBean<CaseDataBean>>() {}.getType());
                return resultBean;
            }
        };

        callback.setResultListener(new IResultBeanListener<CaseDataBean>() {
            @Override
            public void onError(String errorMsg, int id) {
                if ("".equals(errorMsg)) {
                    errorMsg = "请求错误";
                }
                ToastUtil.showToast(getActivity(), errorMsg);
            }

            @Override
            public void onSucceed(ResultBean<CaseDataBean> response, int id) {
                Message.obtain(mHandler, id, response.getData()).sendToTarget();
            }
        });

        Controller.doGetAsync(NetWorkCons.URL_CASE, null, IDMessage.ACTION_LOAD_CASE, callback);
    }

    /**
     * 设置广告
     *
     * @param banner
     */
    private void setBanner(List<BannerBean> banner) {
        mBannerAdapter.setBannerList(banner);
        mBannerAdapter.notifyDataSetChanged();
        //广告指示器
        addDots(banner.size());
        enableDots(0);
        mVp_banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                enableDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 添加指示器进去
     *
     * @param size 指示器数目
     */
    private void addDots(int size) {
        for (int i = 0; i < size; i++) {
            ImageView iv = new ImageView(getActivity());
            iv.setBackgroundResource(R.drawable.selector_case_indicator);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(7, 7);
            params.setMargins(10, 0, 0, 0);
            llindicate.addView(iv, params);
        }
    }

    /**
     * 下面的小圆点的变化
     */
    private void enableDots(int position) {
        if (llindicate.getChildCount() > 0) {
            for (int i = 0; i < llindicate.getChildCount(); i++) {
                ImageView view = (ImageView) llindicate.getChildAt(i);
                view.setEnabled(position == i);
            }
        }
    }


    /**
     * 设置专题
     *
     * @param jiexi
     */
    private void setSpecial(List<JiexiBean> jiexi) {
        //        mHorscr_special
        for (int i = 0, len = jiexi.size(); i < len; i++) {

            final View inflate = View.inflate(getActivity(), R.layout.item_special, null);
            inflate.setTag(jiexi.get(i).getId());
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //打开专题页面
                    int id = (int) v.getTag();
                    Intent intent = new Intent(getActivity(), SubjectActivity.class);
                    intent.putExtra(SubjectActivity.ID, id);
                    //                    Intent intent = new Intent(getActivity(), TestActivity.class);
                    ////                    intent.putExtra(SubjectActivity.ID, id);
                    getActivity().startActivity(intent);
                }
            });

            //图像
            SimpleDraweeView iv_special = (SimpleDraweeView) inflate.findViewById(R.id.iv_special);
            iv_special.setImageURI(Uri.parse(jiexi.get(i).getImgUrl()));
            //标题1
            TextView tv_title1 = (TextView) inflate.findViewById(R.id.tv_title1);
            tv_title1.setText(jiexi.get(i).getImgTitle1());
            //            tv_title1.setShadowLayer(20F, 11F,5F, Color.BLACK);//阴影
            //标题2
            TextView tv_title2 = (TextView) inflate.findViewById(R.id.tv_title2);
            tv_title2.setText(jiexi.get(i).getImgTitle2());
            //            tv_title2.setShadowLayer(20F, 0F,5F, Color.BLACK);

            mLl_special.addView(inflate);
        }
    }

    /**
     * 案例list
     *
     * @param cases
     */
    private void setCaseList(List<CasesBean> cases) {
        mAdapter.setData(cases);
        mAdapter.notifyDataSetChanged();
    }


    private static class Myhandler extends Handler {

        WeakReference<CaseFragment> mFrag;

        Myhandler(CaseFragment frag) {
            this.mFrag = new WeakReference<CaseFragment>(frag);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IDMessage.ACTION_LOAD_CASE:
                    //1.给广告分配
                    mFrag.get().setBanner(((CaseDataBean) msg.obj).getBanner());
                    //2.给专题分配
                    mFrag.get().setSpecial(((CaseDataBean) msg.obj).getJiexi());
                    //3.给list分配
                    mFrag.get().setCaseList(((CaseDataBean) msg.obj).getCases());
                    break;
            }
        }
    }
}
