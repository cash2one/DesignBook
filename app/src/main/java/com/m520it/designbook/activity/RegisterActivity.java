package com.m520it.designbook.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.m520it.designbook.R;
import com.m520it.designbook.base.BaseActivity;
import com.m520it.designbook.config.IDMessage;
import com.m520it.designbook.controller.ReginterController;
import com.m520it.designbook.protocol.IModelChangedListener;
import com.m520it.designbook.utils.IntentUtils;
import com.m520it.designbook.utils.ToastUtil;

/**
 * @author 慕泽
 * @time 2016/7/31  16:36
 * @desc 注册 的界面
 */

public class RegisterActivity extends BaseActivity implements IModelChangedListener {

    private ImageView mZhuche_fanhui;//返回键
    private TextView mQuding;//确定键
    private EditText mShoujihao;//手机号
    private EditText mMima1;//密码
    private EditText mMima2;//确认密码
    private TextView mBiaoti;//标题

    private ReginterController mController;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.obj != null) {
                switch (msg.what) {
                    case IDMessage.MUZE_CONTEXT_REGISTER_ER_ACTION:
                        ToastUtil.showToast(RegisterActivity.this, "注册成功");
                        IntentUtils.startActivityAndFinish(RegisterActivity.this, LoginActivity.class);
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhucheandwangjimima);
        initUi();
        //初始化控制器
        mController = new ReginterController(this);
        mController.setListener(this);
        mBiaoti.setText("设计师注册");
        mQuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuche();
            }
        });
        //返回键
        mZhuche_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //注册操作
    private void zhuche() {
        //获取输入框里面的数据
        String Shoujihao = mShoujihao.getText().toString();
        String Mima = mMima1.getText().toString();
        String QuRenMima = mMima2.getText().toString();
        if (TextUtils.isEmpty(Shoujihao) || TextUtils.isEmpty(Mima) || TextUtils.isEmpty(QuRenMima)) {
            ToastUtil.showToast(RegisterActivity.this, "账号密码不能为空");
            return;
        }
        for (int i = 0; i < Shoujihao.length(); i++) {
            if (i == 11) {
                ToastUtil.showToast(RegisterActivity.this, "亲! 你输入的是手机号码么? 认真核对一下");
            }
        }
        if (Mima == QuRenMima) {
            ToastUtil.showToast(RegisterActivity.this, "密码不一致");
            return;
        }
        //发送一个网络请求
        mController.sendAsyncMessage(IDMessage.MUZE_CONTEXT_REGISTER_ACTION, Shoujihao, Mima);
    }

    @Override
    protected void initUi() {
        mZhuche_fanhui = (ImageView) findViewById(R.id.zhuche_fanhui);
        mQuding = (TextView) findViewById(R.id.register_quding);
        mShoujihao = (EditText) findViewById(R.id.register_shoujihao);
        mMima1 = (EditText) findViewById(R.id.register_mima1);
        mMima2 = (EditText) findViewById(R.id.register_mima2);
        mBiaoti = (TextView) findViewById(R.id.register_biaoti);
    }

    @Override
    public void onModeChange(int action, Object... values) {
        if (values == null) {
            ToastUtil.showToast(RegisterActivity.this, "找不到网络");
            return;
        }
        Message mag = new Message();
        mag.what = action;
        mag.obj = values[0];
        mHandler.sendMessage(mag);
    }
}
