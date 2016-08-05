package com.m520it.designbook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomBase;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m520it.designbook.R;
import com.m520it.designbook.bean.CaseDetailDataBean;
import com.m520it.designbook.bean.ImgBean;
import com.m520it.designbook.bean.ResultBean;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import okhttp3.Response;
import sharesdk.onekeyshare.OnekeyShare;

/**
 * @author JJ
 * @time 2016/8/3 0003  0:23
 * @desc 案例详细
 */
public class CaseDetailActivity extends AppCompatActivity {
    //网络请求参数key
    public static final String ID = "id";
    public static final String UID = "uid";
    public static final String ACTION = "action";
    public static final String MODELE = "module";
    //网络请求参数value
    private final int mUid = 0;
    private final String mAction = "detail";
    private final String mModele = "cases";
    private int mId;

    private Myhandler mHandler;

    @BindView(R.id.iv_background)
    SimpleDraweeView mIvBackground;//背景图片
    @BindView(R.id.scroll_EX)
    MyPullToZoomScrollViewEx mScrollEX;//滑动
    @BindView(R.id.goback)
    ImageView mGoback;//返回
    @BindView(R.id.iv_share)
    ImageView mIvShare;//分享
    @BindView(R.id.iv_comment)
    ImageView mIvComment;//评论
    @BindView(R.id.iv_favorite)
    ImageView mIvFavorite;//收藏
    //    @BindView(R.id.tv_title)
    //    TextView mTvTitle;//大标题
    //    @BindView(R.id.tv_designerName)
    //    TextView mTvDesignerName;//设计师名字
    //    @BindView(R.id.tv_cost)
    //    TextView mTvCost;//设计费用
    //    @BindView(R.id.tv_comment)
    //    TextView mTvComment;//内容
    //    @BindView(R.id.ll_imgs)
    //    LinearLayout mLlImgs;//图片装载
    //    @BindView(R.id.iv_icon)
    //    SimpleDraweeView mIvIcon;//设计师头像
    //    @BindView(R.id.ll_content)
    //    RelativeLayout mLlContent;//内容父容器


    private TextView tv_title;
    private TextView tv_designerName;
    private TextView tv_comment;
    private LinearLayout ll_imgs;
    private SimpleDraweeView iv_icon;
    private RelativeLayout ll_content;
    private TextView mTv_cost;
    private TextView tv_title2;
    private CaseDetailDataBean mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_detail);
        ButterKnife.bind(this);
        mHandler = new Myhandler(this);
        mId = getIntent().getIntExtra(ID, 0);
        initViews();
        loadData();
    }

    private void initViews() {
        View background = View.inflate(this, R.layout.background_scroll_activity, null);//空控件,不设置会出现问题
        View content = View.inflate(this, R.layout.content_casedetail_activity, null);
        mScrollEX.setZoomView(background);
        mScrollEX.setScrollContentView(content);

        tv_title = (TextView) content.findViewById(R.id.tv_title);
        tv_title2 = (TextView) content.findViewById(R.id.tv_title2);
        tv_designerName = (TextView) content.findViewById(R.id.tv_designerName);
        tv_comment = (TextView) content.findViewById(R.id.tv_comment);
        ll_imgs = (LinearLayout) content.findViewById(R.id.ll_imgs);
        iv_icon = (SimpleDraweeView) content.findViewById(R.id.iv_icon);
        ll_content = (RelativeLayout) content.findViewById(R.id.ll_content);
        mTv_cost = (TextView) content.findViewById(R.id.tv_costt);

        setZoomListener();
    }

    /***
     * 滑动监听
     */
    private void setZoomListener() {
        //下拉监听
        mScrollEX.setOnPullZoomListener(new PullToZoomBase.OnPullZoomListener() {
            @Override
            public void onPullZooming(int newScrollValue) {
                LogUtils.v("newScrollValue" + newScrollValue);
            }

            @Override
            public void onPullZoomEnd() {
                LogUtils.v("END");

            }
        });
        //滑动监听
        mScrollEX.setIOnScrollChangedListener(new IOnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldX, int oldY) {
                LogUtils.v("滑动" + x + ":" + y + ":" + oldX + ":" + oldY + ":");
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goback:
                finish();
                break;
            case R.id.iv_share:
                showShare();
                break;
            case R.id.iv_comment:
                //// TODO: 2016/8/3 0003 打开评论页面
                Intent intent = new Intent(this,CaseCommentActivity.class);

                startActivity(intent);
                break;
            case R.id.iv_favorite:
                break;
            case R.id.iv_icon:
                //// TODO: 2016/8/3 0003 跳转到设计师页面
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

        ResultBeanCallback<CaseDetailDataBean> callback = new ResultBeanCallback<CaseDetailDataBean>() {

            @Override
            public ResultBean<CaseDetailDataBean> parseNetworkResponse(Response response, int id) throws Exception {
                ResultBean<CaseDetailDataBean> bean = new Gson().fromJson(response.body().string(), new TypeToken<ResultBean<CaseDetailDataBean>>() {}.getType());
                return bean;
            }
        };

        callback.setResultListener(new IResultBeanListener<CaseDetailDataBean>() {
            @Override
            public void onError(String errorMsg, int id) {
                if ("".equals(errorMsg)) {
                    errorMsg = "请求错误";
                }
                ToastUtil.showToast(CaseDetailActivity.this, errorMsg);
            }

            @Override
            public void onSucceed(ResultBean<CaseDetailDataBean> response, int id) {
                //获取数据成功之后使用hanlder对所有数据填充
                Message.obtain(mHandler, id, response.getData()).sendToTarget();
            }
        });

        Controller.doGetAsync(NetWorkCons.URL_BASE, params, 0, callback);
    }

    /**
     * 显示页面数据
     *
     * @param data
     */
    private void displayPage(CaseDetailDataBean data) {
        mData=data;
        mIvBackground.setImageURI(Uri.parse(data.getImgUrl()));
        tv_title.setText(data.getName());
        tv_title2.setText(data.getAreaName()+" "+data.getSpaceName());
        tv_comment.setText(data.getComment());
        iv_icon.setImageURI(Uri.parse(data.getFacePic()));
        tv_designerName.setText("设计师: "+ data.getNick());
        mTv_cost.setText("设计费用: "+data.getFeeRand());


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


    private static class Myhandler extends Handler {

        WeakReference<CaseDetailActivity> mActivity;

        Myhandler(CaseDetailActivity activity) {
            this.mActivity = new WeakReference<CaseDetailActivity>(activity);
        }

        public void handleMessage(Message msg) {
            mActivity.get().displayPage((CaseDetailDataBean) msg.obj);
        }

    }
}
