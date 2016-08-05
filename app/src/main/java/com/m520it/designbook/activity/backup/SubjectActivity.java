package com.m520it.designbook.activity.backup;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m520it.designbook.R;
import com.m520it.designbook.activity.WebActivity;
import com.m520it.designbook.bean.ImgBean;
import com.m520it.designbook.bean.ResultBean;
import com.m520it.designbook.bean.SubjectDataBean;
import com.m520it.designbook.callback.ResultBeanCallback;
import com.m520it.designbook.config.NetWorkCons;
import com.m520it.designbook.controller.Controller;
import com.m520it.designbook.protocol.IOnScrollChangedListener;
import com.m520it.designbook.protocol.IResultBeanListener;
import com.m520it.designbook.ui.ReboundScrollView;
import com.m520it.designbook.utils.HttpHelper;
import com.m520it.designbook.utils.LogUtils;
import com.m520it.designbook.utils.ToastUtil;
import com.m520it.designbook.utils.UIUtils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

import okhttp3.Response;

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
    private RelativeLayout ll_content;//暂时没用
    private ReboundScrollView scroll_content;//设置监听
    private Button btn_apply;//申请定制设计

    private Myhandler mHandler;

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
        iv_background = (SimpleDraweeView) findViewById(R.id.iv_background);
        goback = (ImageView) findViewById(R.id.goback);
        iv_share = (ImageView) findViewById(R.id.iv_share);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_icon = (SimpleDraweeView) findViewById(R.id.iv_icon);
        tv_designerName = (TextView) findViewById(R.id.tv_designerName);
        tv_comment = (TextView) findViewById(R.id.tv_comment);
        ll_imgs = (LinearLayout) findViewById(R.id.ll_imgs);
        ll_content = (RelativeLayout) findViewById(R.id.ll_content);
        scroll_content = (ReboundScrollView) findViewById(R.id.scroll_content);
        btn_apply = (Button) findViewById(R.id.btn_apply);

        btn_apply.setOnClickListener(this);
        goback.setOnClickListener(this);
        scroll_content.setIOnScrollChangedListener(this);
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

            //必须设置最小宽高
            //宽度确定,是控件宽度,根据比例确定高度 h:bean里的高度 w:bean里的高度
            // w/h= width/height --> height=h*width/w
            img.setMinimumHeight((imgBean.getHeight() * width) / imgBean.getWidth());
            img.setMinimumWidth(width);
            img.setImageURI(Uri.parse(imgBean.getImgUrl()));
            img.setTag(imgBean.getId());

            /**插入图片描述**/
            if (!"".equals(imgBean.getComment())) {
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
            }
            ll_imgs.addView(img);
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
                // TODO: 2016/8/2 0002 弹出分享
                break;
        }
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
