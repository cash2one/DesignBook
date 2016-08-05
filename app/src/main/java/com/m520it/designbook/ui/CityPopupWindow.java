package com.m520it.designbook.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.m520it.designbook.R;

/**
 * @author 吕朝
 * @version $Rev$
 * @time 2016/7/31 0:51
 * @desc ${TODO}
 * @updateAuthor $Author$
 * @uodateDate $Date$
 */

/**
 * 所在城市窗口
 */
public class CityPopupWindow implements AdapterView.OnItemClickListener, View.OnClickListener {

    private Context mContext;
    private GridView mWindowCityGv;
    private RelativeLayout mLookAllCityRl;
    private PopupWindow mPopupWindow;
    private ScrollView mScrollCity;
    private LinearLayout mLoactionCity;
    private TextView mCityName;
    private GridView mFireCity;
    private GridView mAllProvince;
    private CityPopupWindowAdapter mCityPopupWindowAdapter;

    /**
     * ------创建数组,并将热门城市的值放入------
     **/
    private String[] mFireCities;

    public CityPopupWindow(Context context) {
        mContext = context;
        initUI();
        //初始化热门城市的值
        initFireCity();
    }

    private void initFireCity() {
        mFireCities = mContext.getResources().getStringArray(R.array.fireCity);
        //取出热门城市的值
    }

    /**
     * 获取界面UI
     */
    private void initUI() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.window_city_orientation, null);

        /**-----获取scrollView-------**/
        mScrollCity = (ScrollView) inflate.findViewById(R.id.scroll_city);

        /**------获取定位城市------**/
        mLoactionCity = (LinearLayout) inflate.findViewById(R.id.loaction_city);
        mCityName = (TextView) inflate.findViewById(R.id.city_name);

        /**------热门城市GridView,全部省份的GridView------**/
        mFireCity = (GridView) inflate.findViewById(R.id.window_fire_city_gv);
        mAllProvince = (GridView) inflate.findViewById(R.id.window_all_province_gv);

        mFireCity.setOnItemClickListener(this);
        mAllProvince.setOnItemClickListener(this);

        /**------查看全部城市------**/
        mLookAllCityRl = (RelativeLayout) inflate.findViewById(R.id.look_all_city_rl);

        mLookAllCityRl.setOnClickListener(this);

        /**-------设置窗口-----**/
        mPopupWindow = new PopupWindow(-1, -1);
        mPopupWindow.setContentView(inflate);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.update();

        mCityPopupWindowAdapter = new CityPopupWindowAdapter();
        mWindowCityGv.setAdapter(mCityPopupWindowAdapter);


    }

    /**
     * 点击girdview中的每个子控件,都会发生=相关的变化
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    /**
     * 获取更多城市信息,跳转界面
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

    }

    //创建Adapter实现填充数据
    class CityPopupWindowAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mFireCities != null ? mFireCities.length : 0;
        }

        @Override
        public Object getItem(int i) {
            return mFireCities != null ? mFireCities[i] : null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.search_designer_tv, null);
                holder = new ViewHolder();
                holder.find_search_city_tv = (TextView) view.findViewById(R.id.find_search_city_tv);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            for (i = 0; i < mFireCities.length; i++) {
                holder.find_search_city_tv.setText(mFireCities[i]);
            }

            return view;
        }

        class ViewHolder {
            TextView find_search_city_tv;
        }
    }


}
