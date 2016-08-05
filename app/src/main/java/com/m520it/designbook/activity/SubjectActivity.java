package com.m520it.designbook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomBase;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m520it.designbook.R;
import com.m520it.designbook.bean.ImgBean;
import com.m520it.designbook.bean.ResultBean;
import com.m520it.designbook.bean.SubjectDataBean;
import com.m520it.designbook.callback.ResultBeanCallback;
import com.m520it.designbook.config.NetWorkCons;
import com.m520it.designbook.controller.Controller;
import com.m520it.designbook.protocol.IOnScrollChangedListener;
import com.m520it.designbook.protocol.IResultBeanListener;
import com.m520it.designbook.ui.MyPullToZoomScrollViewEx;
import com.m520it.designbook.utils.HttpHelper;
import com.m520it.designbook.utils.LogUtils;
import com.m520it.designbook.utils.ToastUtil;
import com.m520it.designbook.utils.UIUtils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import okhttp3.Response;
import sharesdk.onekeyshare.OnekeyShare;

/**
 * 专题页--JJ
 */
public class SubjectActivity extends AppCompatActivity implements View.OnClickListener, IOnScrollChangedListener {
    //网络请求参数key
    public static final String ID = "id";
    public static final String UID = "uid";
    public static final String ACTION = "action";
    public static final String MODELE = "module";
    //网络请求参数value
    private final int mUid = 0;
    private final String mAction = "jiexiDetail";
    private final String mModele = "cases";
    private int mId;


    private SimpleDraweeView iv_background;//背景图片
    private ImageView goback;//返回
    private ImageView iv_share;//分享
    private TextView tv_title;//主标题
    private SimpleDraweeView iv_icon;//设计师图片
    private TextView tv_designerName;//设计师名字
    private TextView tv_comment;//描述
    private LinearLayout ll_imgs;//装载图片
//    private ReboundScrollView scroll_content;//设置监听
    private Button btn_apply;//申请定制设计

    private MyPullToZoomScrollViewEx scroll_EX;

    private Myhandler mHandler;
    private SubjectDataBean mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Myhandler(this);
        mId = getIntent().getIntExtra(ID, 0);
        setContentView(R.layout.activity_subject);
        initUi();
        loadData();
    }

    protected void initUi() {

        scroll_EX = (MyPullToZoomScrollViewEx) findViewById(R.id.scroll_EX);
        //        View head = View.inflate(this, R.layout.head_case_activity, null);
        View background = View.inflate(this, R.layout.background_scroll_activity, null);//空控件,不设置会出现问题
        View content = View.inflate(this, R.layout.content_case_activity, null);
        View head = findViewById(R.id.head);

        iv_background = (SimpleDraweeView) findViewById(R.id.iv_background);
        goback = (ImageView) head.findViewById(R.id.goback);
        iv_share = (ImageView) head.findViewById(R.id.iv_share);

        tv_title = (TextView) content.findViewById(R.id.tv_title);
        iv_icon = (SimpleDraweeView) content.findViewById(R.id.iv_icon);
        tv_designerName = (TextView) content.findViewById(R.id.tv_designerName);
        tv_comment = (TextView) content.findViewById(R.id.tv_comment);
        ll_imgs = (LinearLayout) content.findViewById(R.id.ll_imgs);
//        scroll_content = (ReboundScrollView) scroll_EX.getPullRootView().findViewById(R.id.scroll_content);
        btn_apply = (Button) findViewById(R.id.btn_apply);

        btn_apply.setOnClickListener(this);
        goback.setOnClickListener(this);
        iv_share.setOnClickListener(this);
//        scroll_content.setIOnScrollChangedListener(this);

        scroll_EX.setZoomView(background);
        scroll_EX.setScrollContentView(content);

        setZoomListener();

    }

    private void setZoomListener() {
        /***
         * 滑动监听
         */
        scroll_EX.setOnPullZoomListener(new PullToZoomBase.OnPullZoomListener() {
            @Override
            public void onPullZooming(int newScrollValue) {
                LogUtils.v("newScrollValue"+newScrollValue);
            }

            @Override
            public void onPullZoomEnd() {
                LogUtils.v("END");
            }
        });

        scroll_EX.setIOnScrollChangedListener(new IOnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldX, int oldY) {
                LogUtils.v("滑动"+x+":"+y+":"+oldX+":"+oldY+":");
            }
        });
    }

    /**
     * 加载数据
     */
    private void loadData() {
        if (mId == 0) {
            finish();
            return;
        }
        //请求参数
        HashMap<String, Object> params = HttpHelper.getEmptyMap();
        params.put(ID, mId);
        params.put(UID, mUid);
        params.put(ACTION, mAction);
        params.put(MODELE, mModele);

        ResultBeanCallback<SubjectDataBean> callback = new ResultBeanCallback<SubjectDataBean>() {

            @Override
            public ResultBean<SubjectDataBean> parseNetworkResponse(Response response, int id) throws Exception {
                ResultBean<SubjectDataBean> bean = new Gson().fromJson(response.body().string(), new TypeToken<ResultBean<SubjectDataBean>>() {}.getType());
                return bean;
            }
        };

        callback.setResultListener(new IResultBeanListener<SubjectDataBean>() {
            @Override
            public void onError(String errorMsg, int id) {
                if ("".equals(errorMsg)) {
                    errorMsg = "请求错误";
                }
                ToastUtil.showToast(SubjectActivity.this, errorMsg);
            }

            @Override
            public void onSucceed(ResultBean<SubjectDataBean> response, int id) {
                //获取数据成功之后使用hanlder对所有数据填充
                Message.obtain(mHandler, id, response.getData()).sendToTarget();
            }
        });

        Controller.doGetAsync(NetWorkCons.URL_SUBJECT, params, 0, callback);
    }

    /**
     * 显示页面数据
     *
     * @param data
     */
    private void displayPage(SubjectDataBean data) {
        this.mData=data;
        iv_background.setImageURI(Uri.parse(data.getImgUrl()));
        tv_title.setText(data.getName());
        tv_comment.setText(data.getComment());
        iv_icon.setImageURI(Uri.parse(data.getMemberInfo().getFacePic()));
        tv_designerName.setText(data.getMemberInfo().getNick());

        loadImgList(data.getImgList());
    }

    /**
     * 加载图片列表
     *
     * @param imgList
     */
    private void loadImgList(List<ImgBean> imgList) {
        int width = ll_imgs.getWidth();//获取父控件布局宽度
        for (int i = 0, len = imgList.size(); i < len; i++) {
            /**插入图片**/
            ImgBean imgBean = imgList.get(i);
            SimpleDraweeView img = new SimpleDraweeView(this);
            boolean hasDes = !"".equals(imgBean.getComment());//有没有描述
            //必须设置最小宽高
            //宽度确定,是控件宽度,根据比例确定高度 h:bean里的高度 w:bean里的高度
            // w/h= width/height --> height=h*width/w
            img.setMinimumHeight((imgBean.getHeight() * width) / imgBean.getWidth());
            img.setMinimumWidth(width);
            img.setImageURI(Uri.parse(imgBean.getImgUrl()));
            img.setTag(imgBean.getId());

            //如果有描述就先插入图片再插入文字,如果没有描述就直接插入图片

            /**插入图片描述**/
            if (hasDes) {
                ll_imgs.addView(img);
                TextView tv = new TextView(this);
                tv.setText(imgBean.getComment());
                tv.setTextSize(17);
                tv.setTextColor(Color.BLACK);
                tv.setLineSpacing(UIUtils.dip2Px(5), 1f);
                tv.setPadding(0, 5, 0, 5);
                ll_imgs.addView(tv);
            } else {
                //没有描述的时候的图片分隔
                img.setPadding(0, 10, 0, 0);
                ll_imgs.addView(img);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_apply://申请设计
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra(WebActivity.TITLE, "申请定制设计");
                intent.putExtra(WebActivity.URL, "http://m.shejiben.com/appZb");
                startActivity(intent);
                break;
            case R.id.goback://返回
                finish();
                break;
            case R.id.iv_share://分享
                showShare();
                break;
        }
    }


    private void showShare(){
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        String shareUrl = mData.getShareUrl();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(shareUrl);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(shareUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(shareUrl);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(shareUrl);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareUrl);

        // 启动分享GUI
        oks.show(this);
    }

    /**
     * 滑动监听
     */
    @Override
    public void onScrollChanged(int x, int y, int oldX, int oldY) {
        //scroll_content  iv_background
        LogUtils.v("滑动x=" + x + " y=" + y + " oldX" + oldX + " oldY" + oldY);
    }

    private static class Myhandler extends Handler {

        WeakReference<SubjectActivity> mActivity;

        Myhandler(SubjectActivity activity) {
            this.mActivity = new WeakReference<SubjectActivity>(activity);
        }

        public void handleMessage(Message msg) {
            mActivity.get().displayPage((SubjectDataBean) msg.obj);
        }

    }
}
