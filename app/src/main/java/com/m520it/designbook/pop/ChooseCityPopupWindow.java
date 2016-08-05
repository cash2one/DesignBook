package com.m520it.designbook.pop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.m520it.designbook.R;
import com.m520it.designbook.adapter.PopWinProvinceAdapter;
import com.m520it.designbook.bean.ProvinceBean;
import com.m520it.designbook.db.CityDAO;
import com.m520it.designbook.utils.FixedViewUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * @author JJ
 * @time 2016/8/4 0004  13:29
 * @desc 城市选择
 */
public class ChooseCityPopupWindow {

    //    @BindView(R.id.city_name)
    //    TextView mCityName;//当前定位
    //    @BindView(R.id.loaction_city)
    //    LinearLayout mLoactionCity;//定位到的城市父容器
    //    @BindView(R.id.reload_ll)
    //    LinearLayout mReloadLl;//重新定位
    //    @BindView(R.id.window_fire_city_gv)
    //    GridView mWindowFireCityGv;//热门城市列表
    //    @BindView(R.id.window_all_province_gv)
    //    GridView mWindowAllProvinceGv;//所有省份列表
    //    @BindView(R.id.scroll_city)
    //    ScrollView mScrollCity;
    //    @BindView(R.id.look_all_city_rl)
    //    RelativeLayout mLookAllCityRl;

    public TextView city_name;
    public LinearLayout loaction_city;
    public LinearLayout reload_ll;
    public GridView window_fire_city_gv;
    public GridView window_all_province_gv;
    public ScrollView scroll_city;
    public RelativeLayout look_all_city_rl;

    private PopupWindow mPop;
    private Context mContext;
    private PopupWindow.OnDismissListener mListener;
    private View mRootView;
    private List<ProvinceBean> mAllProvinceList;
    private Myhandler mHandler;
    private PopWinProvinceAdapter mPopWinProvinceAdapter;


    private static final int ALL_PROVINCE = 1;

    public ChooseCityPopupWindow(Context context, PopupWindow.OnDismissListener listener) {
        mContext = context;
        mListener = listener;

    }

    private void initPop() {
        mHandler = new Myhandler(this);

        mRootView = View.inflate(mContext, R.layout.window_city_orientation, null);
        initViews(mRootView);


        mPop = new PopupWindow(mRootView);
        mPop.setFocusable(true);
        mPop.setOutsideTouchable(true);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.BLACK);
        colorDrawable.setAlpha(127);
        mPop.setBackgroundDrawable(colorDrawable);
        mPop.setOnDismissListener(mListener);
    }

    private void initViews(View rootView) {
        this.city_name = (TextView) rootView.findViewById(R.id.city_name);
        this.loaction_city = (LinearLayout) rootView.findViewById(R.id.loaction_city);
        this.reload_ll = (LinearLayout) rootView.findViewById(R.id.reload_ll);
        this.window_fire_city_gv = (GridView) rootView.findViewById(R.id.window_fire_city_gv);
        this.window_all_province_gv = (GridView) rootView.findViewById(R.id.window_all_province_gv);
        this.scroll_city = (ScrollView) rootView.findViewById(R.id.scroll_city);
        this.look_all_city_rl = (RelativeLayout) rootView.findViewById(R.id.look_all_city_rl);

        mPopWinProvinceAdapter = new PopWinProvinceAdapter(mContext,null);
        window_all_province_gv.setAdapter(mPopWinProvinceAdapter);
    }

    public void showPop(View asDrawDown) {
        if (mPop == null) {
            initPop();
        }
        //子线程查询数据返回设置
        new Thread() {
            @Override
            public void run() {
                CityDAO dao = new CityDAO(mContext);
                SparseArray array = dao.queryAllProvince();
                mAllProvinceList = new ArrayList<ProvinceBean>();
                for (int i = 0, len = array.size(); i < len; i++) {
                    mAllProvinceList.add((ProvinceBean) array.get(i));
                }
                Message.obtain(mHandler, ALL_PROVINCE, mAllProvinceList).sendToTarget();
            }
        }.start();

        mPop.showAsDropDown(asDrawDown, -1, -1);
    }

    public void dissmiss() {
        if (mPop == null) {
            return;
        }
        if (mPop.isShowing()) {
            mPop.dismiss();
        }
    }

    /**
     * 所有省份显示
     *
     * @param data
     */
    private void disPlayAllProvince(List<ProvinceBean> data) {
//        mPopWinProvinceAdapter = new PopWinProvinceAdapter(mContext,mData );
//        window_all_province_gv.setAdapter(mPopWinProvinceAdapter);
        mPopWinProvinceAdapter.addData(data);
        mPopWinProvinceAdapter.notifyDataSetChanged();
        FixedViewUtil.setListViewHeightBasedOnChildren(window_all_province_gv, 3);
    }

    @OnClick({R.id.loaction_city, R.id.reload_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loaction_city:
                break;
            case R.id.reload_ll:
                break;
        }
    }

    private static class Myhandler extends Handler {

        WeakReference<ChooseCityPopupWindow> mPopw;

        Myhandler(ChooseCityPopupWindow pop) {
            this.mPopw = new WeakReference<ChooseCityPopupWindow>(pop);
        }

        public void handleMessage(Message msg) {
            ChooseCityPopupWindow act = mPopw.get();
            switch (msg.what) {
                case ALL_PROVINCE://查询所有省份
                    //设置
                    act.disPlayAllProvince((List<ProvinceBean>) msg.obj);
                    break;
            }
        }
    }
}
