package com.m520it.designbook.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.m520it.designbook.R;
import com.m520it.designbook.activity.CollectActivity;
import com.m520it.designbook.activity.LoginActivity;
import com.m520it.designbook.activity.MyHaoYouActivity;
import com.m520it.designbook.activity.MyInFoActivity;
import com.m520it.designbook.activity.MyJingBiActivity;
import com.m520it.designbook.activity.MyWenDaActivity;
import com.m520it.designbook.activity.SearchDesignerActivity;
import com.m520it.designbook.activity.SettingsActivity;
import com.m520it.designbook.activity.UserInfoActivity;
import com.m520it.designbook.activity.WebActivity;
import com.m520it.designbook.base.MyApplication;
import com.m520it.designbook.bean.RLoginResult;
import com.m520it.designbook.config.NetWorkCons;
import com.m520it.designbook.utils.IntentUtils;
import com.m520it.designbook.utils.ToastUtil;

/**
 * Created by dragon on 2016/7/30.
 * 登入界面
 */
public class MineFragment extends Fragment {
    private ImageView mInfo;//最新消息
    private RelativeLayout mqiandao;//签到;
    private TextView mqiandao_text;//签到文字
    private ImageView mqiandao_tupian;//签到图片
    private SimpleDraweeView mtouxiang;//头像
    private TextView mName;//名字
    private TextView mID;//id
    private View mView;//分隔
    private TextView mJinbi;//金币
    private TextView mXiugai;//修改资料
    private RelativeLayout mShejishi;//找设计师
    private RelativeLayout mXuqiu;//提交需求
    private RelativeLayout mShoucheng;//收藏
    private RelativeLayout mWenda;//问答
    private RelativeLayout mHaoyou;//好友
    private RelativeLayout mMyjinbi2;//金币
    private RelativeLayout mShezhi;//设置
    private LinearLayout mzhaoshejishiandxueq;//找设计师 和 提交需求模块

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        return inflater.inflate(R.layout.activity_my, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUi();//每一个界面的按钮点击都要跳入到登入界面
        MyApplication application = (MyApplication) getActivity().getApplication();
        boolean mShiFouDengRu = application.mShiFouDengRu;
        //默认显示的布局
        mID.setVisibility(View.GONE);
        mJinbi.setVisibility(View.GONE);
        mView.setVisibility(View.GONE);
        mzhaoshejishiandxueq.setVisibility(View.GONE);
        mXiugai.setText("快去登录");
        if (mShiFouDengRu) {
            //显示回来
            mID.setVisibility(View.VISIBLE);
            mJinbi.setVisibility(View.VISIBLE);
            mView.setVisibility(View.VISIBLE);
            mzhaoshejishiandxueq.setVisibility(View.VISIBLE);
            mXiugai.setText("修改资料");

            //如果登入过了 就刷新页面
            //设计头像 写死
            Uri uri = Uri.parse(NetWorkCons.TOUXIANG_URL);
            mtouxiang.setImageURI(uri);
            //获取用户信息 设置上去
            RLoginResult mUserinfo = application.mUserinfo;
            mName.setText(mUserinfo.getUserName());
            String id = String.valueOf(mUserinfo.getId());
            mID.setText(id);
            //处理信息
            mInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), MyInFoActivity.class);
                }
            });
            //处理签到
            mqiandao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mqiandao_text.setText("已签到");
                    mqiandao_tupian.setVisibility(View.INVISIBLE);
                    ToastUtil.showToast(getActivity(), "+金币1000000");
                }
            });
            //修改资料
            mXiugai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), UserInfoActivity.class);
                }
            });
            //找设计师  跳到吕朝
            mShejishi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), SearchDesignerActivity.class);
                }
            });
            //提交需求
            mXuqiu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra(WebActivity.URL, NetWorkCons.TIJIAOXUQIU_URL);
                    intent.putExtra(WebActivity.TITLE, "提交需求");
                    startActivity(intent);
                }
            });
            //我的收藏
            mShoucheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), CollectActivity.class);
                }
            });
            //我的问答
            mWenda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), MyWenDaActivity.class);
                }
            });
            //我的好友
            mHaoyou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), MyHaoYouActivity.class);
                }
            });
            //我的金币
            mMyjinbi2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), MyJingBiActivity.class);
                }
            });
            //设置
            mShezhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), SettingsActivity.class);
                }
            });
        }else{//就显示默认的界面 进行的操作
            //处理信息
            mInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), LoginActivity.class);
                }
            });
            //处理签到
            mqiandao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), LoginActivity.class);
                }
            });
            //修改资料
            mXiugai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), LoginActivity.class);
                }
            });

            //我的收藏
            mShoucheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), LoginActivity.class);
                }
            });
            //我的问答
            mWenda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), LoginActivity.class);
                }
            });
            //我的好友
            mHaoyou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), LoginActivity.class);
                }
            });
            //我的金币
            mMyjinbi2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), LoginActivity.class);
                }
            });
            //设置
            mShezhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivity(getActivity(), SettingsActivity.class);
                }
            });
        }
    }

    private void initUi() {
        mInfo = (ImageView) getActivity().findViewById(R.id.my_info);
        mqiandao = (RelativeLayout) getActivity().findViewById(R.id.my_qiandao);
        mqiandao_text = (TextView) getActivity().findViewById(R.id.my_qiandao_tv);
        mqiandao_tupian = (ImageView) getActivity().findViewById(R.id.my_qiandao_iv);
        mtouxiang = (SimpleDraweeView) getActivity().findViewById(R.id.fresco_touxiagn);
        mName = (TextView) getActivity().findViewById(R.id.my_name);
        mID = (TextView) getActivity().findViewById(R.id.my_id);
        mView = (View) getActivity().findViewById(R.id.view1);
        mJinbi = (TextView) getActivity().findViewById(R.id.my_jinbi);
        mXiugai = (TextView) getActivity().findViewById(R.id.my_onmylnog_tv);
        mShejishi = (RelativeLayout) getActivity().findViewById(R.id.my_shejishi_rl);
        mXuqiu = (RelativeLayout) getActivity().findViewById(R.id.my_xuqiu_rl);
        mShoucheng = (RelativeLayout) getActivity().findViewById(R.id.my_shouchang);
        mWenda = (RelativeLayout) getActivity().findViewById(R.id.my_wenda);
        mHaoyou = (RelativeLayout) getActivity().findViewById(R.id.my_haoyou);
        mMyjinbi2 = (RelativeLayout) getActivity().findViewById(R.id.my_jinbi2);
        mShezhi = (RelativeLayout) getActivity().findViewById(R.id.my_shezhi);
        mzhaoshejishiandxueq = (LinearLayout) getActivity().findViewById(R.id.xuqiu);
    }
}
