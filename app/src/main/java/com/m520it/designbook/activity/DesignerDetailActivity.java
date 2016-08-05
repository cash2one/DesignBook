package com.m520it.designbook.activity;

/**
 * @author 吕朝
 * @version $Rev$
 * @time 2016/7/31 19:09
 * @desc ${TODO}
 * @updateAuthor $Author$
 * @uodateDate $Date$
 */

import android.os.Bundle;

import com.m520it.designbook.R;
import com.m520it.designbook.base.BaseActivity;

/**
 * 设计师详情页面
 */
public class DesignerDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_detail);
        initUi();
    }

    @Override
    protected void initUi() {

    }
}
