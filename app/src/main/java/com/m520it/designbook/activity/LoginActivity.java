package com.m520it.designbook.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.m520it.designbook.R;
import com.m520it.designbook.base.BaseActivity;
import com.m520it.designbook.base.MyApplication;
import com.m520it.designbook.bean.RLoginResult;
import com.m520it.designbook.bean.RResult;
import com.m520it.designbook.config.IDMessage;
import com.m520it.designbook.controller.LogInController;
import com.m520it.designbook.protocol.IModelChangedListener;
import com.m520it.designbook.utils.IntentUtils;
import com.m520it.designbook.utils.ToastUtil;

/**
 * @author 慕泽
 * @time 2016/7/30  14:34
 * @desc 真的登入界面
 */

public class LoginActivity extends BaseActivity implements IModelChangedListener {
    private TextView mLogin;
    private TextView mRegister;
    private TextView mLogin_yanshe;
    private TextView mRegister_yanshe;
    private FrameLayout mFrameLayout;
    private LinearLayout fl_ll_login;
    private LinearLayout fl_ll_register;
    private LinearLayout mOwner;
    private LinearLayout mStylist;
    private TextView mWangji;
    private TextView mQuedin;
    private EditText mRoot;
    private EditText mPaw;

    private LogInController mLogInController;//初始化控制器
    //初始化一个handler
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg != null) {
                switch (msg.what) {
                    case IDMessage.MUZE_CONTENT_LOGIN_ER_ACTION:
                        setlogin((RResult)msg.obj);
                        break;
                }
            }

        }
    };

    //登入正确的操作方法
    private void setlogin(RResult obj) {
        if(obj == null){
            ToastUtil.showToast(LoginActivity.this,"你开网没有? 亲");
        }
        //保存 用户信息 到全局类里面 其他都是能用到的
        RLoginResult rLoginResult = JSON.parseObject(obj.getResult(), RLoginResult.class);
        ((MyApplication)getApplication()).mUserinfo = rLoginResult;//赋值上去就是可以
        //登入成功后赋值上去  返回的Sting类型
        ((MyApplication)getApplication()).mShiFouDengRu = obj.isSuccess();
        //跳到真正的登入界面
        IntentUtils.startActivityAndFinish(LoginActivity.this,MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longin);
        initUi();
        //初始化控制
        mLogInController = new LogInController(this);
        mLogInController.setListener(this);

        //默认是隐藏
        mLogin.setTextColor(Color.RED);//默认是红色的
        mRegister_yanshe.setVisibility(View.INVISIBLE);//默认是隐藏的
        //默认fragment 的显示


        //登入
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogin.setTextColor(Color.RED);//设置颜色
                mRegister_yanshe.setVisibility(View.INVISIBLE);//隐藏一个textview
                mLogin_yanshe.setVisibility(View.VISIBLE);//显示字本身
                mRegister.setTextColor(Color.BLACK);//设置成黑色

                //fragment 的显示
                fl_ll_login.setVisibility(View.VISIBLE);
                fl_ll_register.setVisibility(View.GONE);


            }
        });
        //注册
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegister.setTextColor(Color.RED);
                mLogin.setTextColor(Color.BLACK);
                mLogin_yanshe.setVisibility(View.INVISIBLE);
                mRegister_yanshe.setVisibility(View.VISIBLE);//显示一个view

                //fragment 的显示
                fl_ll_login.setVisibility(View.GONE);
                fl_ll_register.setVisibility(View.VISIBLE);

                //设置注册里面的点击监听
                setRegister();
            }
        });
        //登入确定 和忘记密码 界面跳转方法
        setLogin();
    }

    //登入确定 和忘记密码 界面跳转方法
    private void setLogin() {
        mQuedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送一个网络请求//TODO
                String root = mRoot.getText().toString();
                String paw = mPaw.getText().toString();
                if (TextUtils.isEmpty(root) || TextUtils.isEmpty(paw)) {
                    ToastUtil.showToast(LoginActivity.this, "账号密码不能为空");
                    return;
                }

                //发送一个异步请求
                mLogInController.sendAsyncMessage(IDMessage.MUZE_CONTENT_LOGIN_ACTION, root, paw);

            }
        });
        mWangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(LoginActivity.this,ChangePasswordActivity.class);
            }
        });
    }

    //设置注册里面的点击监听
    private void setRegister() {
        mOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(LoginActivity.this,RegisterActivity.class);
            }
        });
        mStylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(LoginActivity.this,RegisterActivity.class);
            }
        });
    }

    //获取返回来的数据
    @Override
    public void onModeChange(int action, Object... values) {
        if (values == null) {
            ToastUtil.showToast(LoginActivity.this, "你开网络没有? 亲");
            return;
        }
        //修改ui
        Message mag = new Message();
        mag.what = action;
        mag.obj = values[0];
        mHandler.sendMessage(mag);
    }

    @Override
    protected void initUi() {
        mLogin = (TextView) findViewById(R.id.loginactivity_wenzi1);
        mRegister = (TextView) findViewById(R.id.loginactivity_wenzi2);
        mLogin_yanshe = (TextView) findViewById(R.id.loginactivity_yanshe1);
        mRegister_yanshe = (TextView) findViewById(R.id.loginactivity_yanshe2);
        mFrameLayout = (FrameLayout) findViewById(R.id.loginActivity_fl);
        //真布局里面的连个view
        fl_ll_login = (LinearLayout) findViewById(R.id.fl_ll_login);
        fl_ll_register = (LinearLayout) findViewById(R.id.fl_ll_register);
        //获取业主和设计师
        mOwner = (LinearLayout) findViewById(R.id.owner);
        mStylist = (LinearLayout) findViewById(R.id.stylist);
        //获取输入框里面数据
        mRoot = (EditText) findViewById(R.id.login_root_et);
        mPaw = (EditText) findViewById(R.id.login_paw_et);
        //确定登入
        mQuedin = (TextView) findViewById(R.id.login_qudin_tv);
        //忘记密码
        mWangji = (TextView) findViewById(R.id.login_wangji_tx);
    }

}
