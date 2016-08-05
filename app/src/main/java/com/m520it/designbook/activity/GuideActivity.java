package com.m520it.designbook.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.m520it.designbook.R;
import com.m520it.designbook.adapter.GuideAdapter;
import com.m520it.designbook.config.SPConstances;
import com.m520it.designbook.utils.IntentUtils;

import java.util.ArrayList;

/**
 * @desc 引导界面 只在用户第一次进入app的时候才出现 以后不会再出现
 * Created by dragon on 2016/7/29.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager vpguide;//显示的guide图片
    private android.widget.LinearLayout llindicate;//图片指示器
    private TextView tv_skip;//跳转按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();
        initData();
        //TODO 记得进入一次后 把sp里面的数据改成false

    }

    /**
     * 加载数据
     */
    private void initData() {
        //为viewpager提供adapter
        Integer[] imagesId = {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3,R.drawable.guide_4};
        ArrayList<ImageView> images =  new ArrayList<>();
        //加载引导图片进去
        for(int i = 0; i < imagesId.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(imagesId[i]);
            images.add(iv);
        }
        GuideAdapter guideAdapter = new GuideAdapter(images);
        vpguide.setAdapter(guideAdapter);


        //还有指示器
        addDots(imagesId.length);
        //设置第一个为默认选中
        enableDots(0);
        vpguide.setOnPageChangeListener(this);
    }

    /**
     * 添加指示器进去
     * @param size 指示器数目
     */
    private void addDots(int size) {
        for (int i = 0; i < size; i++) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(R.drawable.home_dot_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            params.setMargins(10, 0, 0, 0);
            llindicate.addView(iv, params);
        }
    }

    /**
     * 下面的小圆点的变化
     */
    private void enableDots(int position) {

        if (llindicate.getChildCount()>0) {
            //TODO 这里莫名奇怪的要加1
//            position+=1;
            for (int i = 0; i < llindicate.getChildCount(); i++) {
                ImageView view = (ImageView) llindicate.getChildAt(i);
                view.setEnabled(position==i);
            }
        }
    }

    /**
     * 拿到界面的id
     */
    private void initViews() {
        this.llindicate = (LinearLayout) findViewById(R.id.ll_indicate);
        this.vpguide = (ViewPager) findViewById(R.id.vp_guide);
        tv_skip = (TextView) findViewById(R.id.tv_skip);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tv_skip.setVisibility(View.INVISIBLE);
        llindicate.setVisibility(View.VISIBLE);
        //跳转page的时候 改变下面的指示器
        enableDots(position);
        //TODO 这里的3最好不要写死
        if(position==3) {
            tv_skip.setVisibility(View.VISIBLE);
            llindicate.setVisibility(View.INVISIBLE);
            tv_skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //等等用工具类
                    SharedPreferences sp = getSharedPreferences(SPConstances.SP_BASE, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean(SPConstances.SP_ISFIRST,false);
                    edit.commit();

                    IntentUtils.startActivityAndFinish(GuideActivity.this,MainActivity.class);
                }
            });
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
