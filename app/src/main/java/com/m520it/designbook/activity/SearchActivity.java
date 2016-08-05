package com.m520it.designbook.activity;

/**
 * @author 吕朝
 * @version $Rev$
 * @time 2016/7/31 14:53
 * @desc ${TODO}
 * @updateAuthor $Author$
 * @uodateDate $Date$
 */

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.m520it.designbook.R;
import com.m520it.designbook.base.BaseActivity;

/**
 * 点击搜索栏目时,跳转界面
 */
public class SearchActivity extends BaseActivity {

    private TextView mSearchContent;
    private ImageView mDeleteAll;
    private TextView mSearchCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initUi();
    }

    @Override
    protected void initUi() {
        mSearchContent = (TextView) findViewById(R.id.search_content);
        mDeleteAll = (ImageView) findViewById(R.id.delete_all);
        mSearchCancle = (TextView) findViewById(R.id.search_cancle);


    }
}
