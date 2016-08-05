package com.m520it.designbook.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.m520it.designbook.R;
import com.m520it.designbook.base.MyApplication;
import com.m520it.designbook.fragment.AskFragment;
import com.m520it.designbook.fragment.CaseFragment;
import com.m520it.designbook.fragment.FindFragment;
import com.m520it.designbook.fragment.GalleryFragment;
import com.m520it.designbook.fragment.MineFragment;

/**
 * @desc 主界面   fragment+fragmenttabhost  相当于跳转界面
 */
public class MainActivity extends FragmentActivity {
    private FragmentTabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //这行代码是 关闭整个引用的  慕泽添加
        MyApplication.addActivity(this);

        initViews();
        //最后一个参数 是容器的id
        tabhost.setup(this,getSupportFragmentManager(),R.id.content);
        //图片的显示
        int[] images  ={R.drawable.selector_case_btn,R.drawable.selector_gallery_btn,
                        R.drawable.selector_questions_btn,R.drawable.selector_find_btn,
                        R.drawable.selector_mine_btn};
        String[] titles = {"案例","图库","问答","发现","我的"};
        //fragment的类名
        Class[] frags = {CaseFragment.class, GalleryFragment.class, AskFragment.class, FindFragment.class, MineFragment.class};
        for(int i = 0; i < titles.length; i++) {
            View view = getView(titles[i], images[i]);
            //第一个参数是 编号 和对应的view  第二个是每个的主体内容   扔个fragment进去 而且不是new 而是字节码文件对象
            //应该每个不一样的 要扔进五个不一样的fragment
            tabhost.addTab(tabhost.newTabSpec(String.valueOf(i)).setIndicator(view),frags[i] ,null);
        }
    }

    //每个item的显示
    private View getView(String title ,int imageResource){
        View view = View.inflate(this,R.layout.tab_item_view,null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_picture);
        TextView tv = (TextView) view.findViewById(R.id.tv_title);
        iv.setImageResource(imageResource);
        tv.setText(title);
        return view ;
    }


    private void initViews() {
        tabhost = (FragmentTabHost)findViewById(R.id.tabhost);
    }

//    /**
//     * 打开一个webview
//     * @param event
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void openWebActivity(WebClickEvent event){
//        Intent intent = new Intent(this, WebActivity.class);
//        intent.putExtra("url",event.getUrl());
//        startActivity(intent);
//    }

}
