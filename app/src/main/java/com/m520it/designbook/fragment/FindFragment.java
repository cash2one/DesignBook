package com.m520it.designbook.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m520it.designbook.R;
import com.m520it.designbook.activity.ApplyDesignActivity;
import com.m520it.designbook.activity.SearchDesignerActivity;
import com.m520it.designbook.ui.GuiderPopupWindow;
import com.m520it.designbook.utils.IntentUtils;

/**
 * @author 吕朝
 * @version $Rev$
 * @time 2016/7/30 11:14
 * @desc ${TODO}
 * @updateAuthor $Author$
 * @uodateDate $Date$
 */

/**
 * 创建发现页面的fragment
 */
public class FindFragment extends Fragment implements View.OnClickListener {

    /**
     * 获取inflate
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find, null);

    }

    /**
     * 获取UI控件
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        /**------初始化控件------**/
        initUI();

    }

    /**
     * 初始化FindFragment界面的控件
     */
    private void initUI() {
        /**------点击收索框的时候,------**/
        getActivity().findViewById(R.id.search_fl).setOnClickListener(this);
        /**------点击寻找的时候,------**/
        getActivity().findViewById(R.id.find_design_ll).setOnClickListener(this);
        /**------点击申请的时候,------**/
        getActivity().findViewById(R.id.apply_design_ll).setOnClickListener(this);
        /**------点击城市的时候,------**/
        getActivity().findViewById(R.id.find_city_designer_rl).setOnClickListener(this);
        /**------点击热门的时候,------**/
        getActivity().findViewById(R.id.find_fire_designer_rl).setOnClickListener(this);
        /**------点击装修设计顾问的时候,------**/
        getActivity().findViewById(R.id.find_guider_rl).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_fl:

                break;
            case R.id.find_design_ll:
                IntentUtils.startActivity(getActivity(), SearchDesignerActivity.class);
                break;
            case R.id.apply_design_ll:
                IntentUtils.startActivity(getActivity(), ApplyDesignActivity.class);
                break;
            case R.id.find_city_designer_rl:
                IntentUtils.startActivity(getActivity(), SearchDesignerActivity.class);
                break;
            case R.id.find_fire_designer_rl:
                IntentUtils.startActivity(getActivity(), SearchDesignerActivity.class);
                break;
            case R.id.find_guider_rl:
                GuiderPopupWindow guiderPopupWindow = new GuiderPopupWindow(getContext());
                guiderPopupWindow.onShow(view);
                break;

        }
    }
}
