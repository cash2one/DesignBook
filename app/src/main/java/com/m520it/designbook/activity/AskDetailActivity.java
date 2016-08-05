package com.m520it.designbook.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m520it.designbook.R;
import com.m520it.designbook.adapter.AskDetailAdapter;
import com.m520it.designbook.adapter.PicturePageAdapter;
import com.m520it.designbook.bean.AskDetailBean;
import com.m520it.designbook.bean.ResultBean;
import com.m520it.designbook.callback.ResultBeanCallback;
import com.m520it.designbook.config.IDMessage;
import com.m520it.designbook.config.NetWorkCons;
import com.m520it.designbook.controller.Controller;
import com.m520it.designbook.protocol.IResultBeanListener;
import com.m520it.designbook.utils.IntentUtils;
import com.m520it.designbook.utils.LogUtils;
import com.m520it.designbook.utils.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import okhttp3.Response;
import sharesdk.onekeyshare.OnekeyShare;

/**
 * @desc 问答界面的 点击详情
 * Created by dragon on 2016/7/30.
 */
public class AskDetailActivity extends Activity implements View.OnClickListener {
    private android.widget.ImageView ivback; //回退按钮
    private android.widget.ImageView ivshare;//分享按钮
    private android.widget.TextView tvquestiontitle;//问题标题
    private android.support.v4.view.ViewPager vppictures;//问题图片详情
    private android.widget.LinearLayout llindicate;//问题图片的 指示器
    private android.widget.TextView tvquestuionusername;//提问题的用户名
    private android.widget.TextView tvquestiontime;//提问题的时间
    private android.widget.TextView tvquestionhits;//阅读数
    private android.widget.TextView tvcommentsnum;//评论数
    private android.widget.ListView lvcomments;//评论内容
    private android.widget.LinearLayout llaskquestion;//我要提问


    public static final String QUESTION_ID = "id";
    private int mId;//当前问题详情的id 用它来获取数据的
    private MyHandle mHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_detail);

        mHandle = new MyHandle(this);
        getPassValues();
        initViews();

        loadData();
    }

    private void loadData() {


        ResultBeanCallback<AskDetailBean.DataBean> callback = new ResultBeanCallback<AskDetailBean.DataBean>() {
            @Override
            public ResultBean<AskDetailBean.DataBean> parseNetworkResponse(Response response, int id) throws Exception {
                ResultBean<AskDetailBean.DataBean> resultBean = new Gson().fromJson(response.body().string(), new TypeToken<ResultBean<AskDetailBean.DataBean>>() {
                }.getType());
                return resultBean;
            }
        };

        callback.setResultListener(new IResultBeanListener<AskDetailBean.DataBean>() {
            @Override
            public void onError(String errorMsg, int id) {
                LogUtils.v("dargon", "数据获取失败了  askDetail");
            }

            @Override
            public void onSucceed(ResultBean<AskDetailBean.DataBean> response, int id) {
                //如果获取数据 就在这里搞了
                LogUtils.v("dargon", "数据加载成功  askDetail");

                mHandle.obtainMessage(id, response).sendToTarget();
            }
        });
        //获取数据
        Controller.doGetAsync(NetWorkCons.getAskDetailUrl(mId + ""), null, IDMessage.ASK_QUESTION_DETAIL, callback);

    }

    public void getPassValues() {
        //拿到传进来的id
        mId = getIntent().getIntExtra(QUESTION_ID, 0);
        LogUtils.v("dargon","mid === "+mId+"");
    }


    private void initViews() {

        this.lvcomments = (ListView) findViewById(R.id.lv_comments);
        this.tvcommentsnum = (TextView) findViewById(R.id.tv_comments_num);
        this.tvquestionhits = (TextView) findViewById(R.id.tv_question_hits);
        this.tvquestiontime = (TextView) findViewById(R.id.tv_question_time);
        this.tvquestuionusername = (TextView) findViewById(R.id.tv_questuion_username);
        this.llindicate = (LinearLayout) findViewById(R.id.ll_indicate);
        this.vppictures = (ViewPager) findViewById(R.id.vp_pictures);
        this.tvquestiontitle = (TextView) findViewById(R.id.tv_question_title);
        this.llaskquestion = (LinearLayout) findViewById(R.id.ask_detail_ll);
        this.ivshare = (ImageView) findViewById(R.id.iv_share);
        this.ivback = (ImageView) findViewById(R.id.iv_back);
        this.ivback.setOnClickListener(this);
        this.ivshare.setOnClickListener(this);

        this.llaskquestion.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ask_detail_ll:
                IntentUtils.startActivityAndFinish(AskDetailActivity.this, LoginActivity.class);
                break;
            case R.id.iv_share:
                showShare();
                break;

        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }


    private static class MyHandle extends Handler {
        private WeakReference<AskDetailActivity> reference;


        public MyHandle(AskDetailActivity activity) {
            reference = new WeakReference<AskDetailActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            AskDetailActivity activity = reference.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case IDMessage.ASK_QUESTION_DETAIL:
                    activity.handleQuestionDetail((ResultBean) msg.obj);
                    break;
            }
        }
    }

    private void handleQuestionDetail(ResultBean bean) {
        //数据有了 填充进去
        if (bean != null) {
            //强转一下
            AskDetailBean.DataBean data = (AskDetailBean.DataBean) bean.getData();
            AskDetailBean.DataBean.AskInfoBean askInfo = data.getAskInfo();
            tvquestiontitle.setText(askInfo.getTitle());
            tvquestuionusername.setText(askInfo.getNick());
            tvquestiontime.setText(askInfo.getCreateTime() + "");
            tvquestionhits.setText(askInfo.getHits() + "");
            tvcommentsnum.setText(askInfo.getComments() + "条评论");
            //创建一个数据 扔到 viewpager的adapter里面去
            ArrayList<SimpleDraweeView> picts = new ArrayList<>();
            for (int i = 0; i < askInfo.getPics().size(); i++) {
                SimpleDraweeView iv = new SimpleDraweeView(this);
                Uri uri = Uri.parse(askInfo.getPics().get(i));
                iv.setImageURI(uri);
                picts.add(iv);
            }

            //Indicator
            vppictures.setAdapter(new PicturePageAdapter(picts));

            lvcomments.setAdapter(new AskDetailAdapter(data.getCommentList(), this));

        } else {
            ToastUtil.showToast(this, "没有获取到数据 瞬间爆炸");
        }
    }

}
