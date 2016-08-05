package com.m520it.designbook.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m520it.designbook.R;
import com.m520it.designbook.adapter.SearchDesignerAdapter;
import com.m520it.designbook.bean.DesignerBean;
import com.m520it.designbook.bean.ResultBean;
import com.m520it.designbook.callback.ResultBeanCallback;
import com.m520it.designbook.config.IDMessage;
import com.m520it.designbook.config.NetWorkCons;
import com.m520it.designbook.controller.Controller;
import com.m520it.designbook.pop.ChooseCityPopupWindow;
import com.m520it.designbook.protocol.IResultBeanListener;
import com.m520it.designbook.utils.HttpHelper;
import com.m520it.designbook.utils.LogUtils;
import com.m520it.designbook.utils.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;


/**
 * @author 吕朝
 * @version $Rev$
 * @time 2016/7/30 14:45
 * @desc ${TODO}
 * @updateAuthor $Author$
 * @uodateDate $Date$
 */

/**
 * 寻找设计师界面
 */
public class SearchDesignerActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请求参数
     */
    private final String PAGE_SIZE = "pageSize";
    private final int PAGE_SIZE_VALUE = 30;

    private final String ACTION = "action";
    private final String ACTION_VALUE = "list";

    private final String MODULE = "module";
    private String MODULE_VALUE = "sjs";

    private final String SPACE = "space";
    private String mSpaceValue = "";

    private final String PAGE = "page";
    private int mPage = 1;//当前页数

    private final String CITY = "city";
    private String mCity = "0";//当前城市


    private int visibleLastIndex = 0;   //最后的可视项索引
    private int itemCount;       // 当前窗口可见项总数

    private ImageView mGoBack;
    private TextView mSearchDesignerCityTv;
    private ImageView mSearchDesignerCityIv;
    private TextView mSearchDesignerSkllTv;
    private ImageView mSearchDesignerSkillIv;
    private TextView mSearchDesignerCompositeTv;
    private ImageView mSearchDesignerCompositeIv;
    //    private int i = 0;//默认城市被点击数初始为0
    //    private int j = 0;//默认特长被点击数初始为0
    //    private int k = 0;//默认综合被点击数初始为0
    private ListView mSearchDesignerLv;//列表
    private SearchDesignerAdapter mSearchDesignerAdapter;//分配器
    private MyHandler mHandler;
    private List<DesignerBean> datas = new ArrayList<>();
    private int StartIndex = 0;
    private ResultBeanCallback<List<DesignerBean>> mCallback;

    private boolean mIsLoading = false;//是否在loading
    private ChooseCityPopupWindow mCityPop;
    private LinearLayout ll_indicate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_designer);
        mHandler = new MyHandler(this);
        initUI();
        loadData();
    }


    //在load中解析数
    private void loadData() {
        LogUtils.v("loadData");
        mCallback = new ResultBeanCallback<List<DesignerBean>>() {
            @Override
            public ResultBean<List<DesignerBean>> parseNetworkResponse(Response response, int id) throws Exception {
                ResultBean<List<DesignerBean>> resultBean = new Gson().fromJson(response.body().string(), new TypeToken<ResultBean<List<DesignerBean>>>() {
                }.getType());
                return resultBean;
            }
        };
        /**------监听成功还是失败------**/
        mCallback.setResultListener(new IResultBeanListener<List<DesignerBean>>() {
            @Override
            public void onError(String errorMsg, int id) {
                if ("".equals(errorMsg)) {
                    errorMsg = "请求错误";
                }
                ToastUtil.showToast(SearchDesignerActivity.this, errorMsg);
            }

            @Override
            public void onSucceed(ResultBean<List<DesignerBean>> response, int id) {
                Log.v("520it", "jiexi cheng " + response.getData());
                Message.obtain(mHandler, id, response.getData()).sendToTarget();
            }
        });
        Controller.doPostAsync(NetWorkCons.SEARCH_DESIGNER_URL, getParamsMap(), IDMessage.LOOK_FOR_DESIGNER_ACTION, mCallback);
    }


    /**
     * 初始化控件
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void initUI() {
        /**------返回------**/
        mGoBack = (ImageView) findViewById(R.id.designer_back);
        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /**-----三个控件点击-------**/
        findViewById(R.id.search_designer_city).setOnClickListener(this);
        findViewById(R.id.search_designer_skill).setOnClickListener(this);
        findViewById(R.id.search_designer_composite).setOnClickListener(this);

        /**------获取LinearLayout里面的小的控件------**/
        ll_indicate = (LinearLayout)findViewById(R.id.ll_indicate);
        mSearchDesignerCityTv = (TextView) findViewById(R.id.search_designer_city_tv);
        mSearchDesignerCityIv = (ImageView) findViewById(R.id.search_designer_city_iv);

        mSearchDesignerSkllTv = (TextView) findViewById(R.id.search_designer_skill_tv);
        mSearchDesignerSkillIv = (ImageView) findViewById(R.id.search_designer_skill_iv);

        mSearchDesignerCompositeTv = (TextView) findViewById(R.id.search_designer_composite_tv);
        mSearchDesignerCompositeIv = (ImageView) findViewById(R.id.search_designer_composite_iv);

        /**------添加adapter------**/
        mSearchDesignerLv = (ListView) findViewById(R.id.search_designer_lv);
        mSearchDesignerAdapter = new SearchDesignerAdapter(this);
        mSearchDesignerAdapter.addData(new ArrayList<DesignerBean>());
        mSearchDesignerLv.setAdapter(mSearchDesignerAdapter);
        mSearchDesignerLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchDesignerActivity.this, DesignerDetailActivity.class);
                startActivity(intent);
            }
        });

        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {}
        /**
         * 滑动加载
         */

        mSearchDesignerLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                return;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastVisiblePosition = mSearchDesignerLv.getLastVisiblePosition() + 1;
                LogUtils.v("最后:" + lastVisiblePosition + " 总量:" + mSearchDesignerAdapter.getCount());
                //足以触发,而且没在加载的时候加载
                LogUtils.v((mSearchDesignerAdapter.getCount() - 5 < lastVisiblePosition && !mIsLoading) + "");
                if (mSearchDesignerAdapter.getCount() - 5 < lastVisiblePosition && !mIsLoading) {

                    LogUtils.v("滑动加载");
                    HashMap<String, Object> map = HttpHelper.getEmptyMap();
                    map.put("city", mCity);
                    map.put("page", mPage);

                    //如果是自动加载,可以在这里放置异步加载数据的代码
                    Controller.doPostAsync(NetWorkCons.SEARCH_DESIGNER_URL, getParamsMap(), IDMessage.LOOK_FOR_DESIGNER_ACTION, mCallback);
                    mIsLoading = true;
                    Log.i("LOADMORE", "loading...");
                }
            }

        });
    }

    private Map<String, Object> getParamsMap() {
        HashMap<String, Object> map = HttpHelper.getEmptyMap();
        map.put(PAGE_SIZE, PAGE_SIZE_VALUE);
        map.put(ACTION, ACTION_VALUE);
        map.put(MODULE, MODULE_VALUE);
        map.put(PAGE, mPage);
        map.put(CITY, mCity);

        if (!"".equals(mSpaceValue)) {
            map.put(SPACE, mSpaceValue);
        }
        return map;
    }

    private void displayData(List<DesignerBean> obj) {
        if (obj != null) {
            mIsLoading = false;
            mPage++;
            mSearchDesignerAdapter.addData(obj);
            mSearchDesignerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 点击三个控件后触发事件
     * <p/>
     * 字体颜色改变
     * 箭头的方向改变
     * <p/>
     * 首先默认的颜色为黑色,箭头向下,
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

        int curItem = 0;
        switch (view.getId()) {
            case R.id.search_designer_city:
                curItem = 1;
                //显示选择框
                if (mCityPop == null) {
                    mCityPop = new ChooseCityPopupWindow(this, new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            setButtonSelected(0);
                        }
                    });
                }
                mCityPop.showPop(ll_indicate);
                break;
            case R.id.search_designer_skill:
                curItem = 2;
                break;
            case R.id.search_designer_composite:
                curItem = 3;
                break;
            default:
                break;
        }
        setButtonSelected(curItem);
    }

    /**
     * 根据index改变按键状态
     */
    private void setButtonSelected(int index) {
        mSearchDesignerCityTv.setSelected(index == 1);
        mSearchDesignerCityIv.setSelected(index == 1);

        mSearchDesignerSkllTv.setSelected(index == 2);
        mSearchDesignerSkillIv.setSelected(index == 2);

        mSearchDesignerCompositeTv.setSelected(index == 3);
        mSearchDesignerCompositeIv.setSelected(index == 3);
    }


    /**
     * 默认为未点击时
     */
    private void defaultStatus(int i, int j, int k) {
        mSearchDesignerCityTv.setTextColor(i % 2 == 0 ? Color.BLACK : Color.RED);
        mSearchDesignerCityIv.setSelected(i % 2 == 0 ? false : true);

        mSearchDesignerSkllTv.setTextColor(j % 2 == 0 ? Color.BLACK : Color.RED);
        mSearchDesignerSkillIv.setSelected(j % 2 == 0 ? false : true);

        mSearchDesignerCompositeTv.setTextColor(k % 2 == 0 ? Color.BLACK : Color.RED);
        mSearchDesignerCompositeIv.setSelected(k % 2 == 0 ? false : true);
    }

    /**
     * ------创建Handler------
     **/
    private static class MyHandler extends Handler {
        //创建弱引用
        private WeakReference<SearchDesignerActivity> mWeakReference;

        public MyHandler(SearchDesignerActivity searchDesignerActivity) {
            mWeakReference = new WeakReference<SearchDesignerActivity>(searchDesignerActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mWeakReference.get() == null) {
                return;
            }
            switch (msg.what) {
                case IDMessage.LOOK_FOR_DESIGNER_ACTION:
                    mWeakReference.get().displayData((List<DesignerBean>) msg.obj);
                    break;
            }
        }
    }
}
