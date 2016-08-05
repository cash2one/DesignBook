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
import com.m520it.designbook.controller.ChangePasswordController;
import com.m520it.designbook.protocol.IModelChangedListener;
import com.m520it.designbook.utils.ToastUtil;

/**
 * @author 慕泽
 * @time 2016/8/2  23:32
 * @desc 忘记密码 界面
 */

public class ChangePasswordActivity extends BaseActivity implements IModelChangedListener {
    private ImageView mFanhui;//返回
    private EditText mShoujihaoma;//手机号码
    private TextView mQuding;//确定键
    private ChangePasswordController mController;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.obj != null) {
                switch (msg.what) {
                    case IDMessage.MUZE_CONTEXT_CHANGE_ER_ACTION:
                        ToastUtil.showToast(ChangePasswordActivity.this, "修改成功");
                        finish();
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initUi();
        mController = new ChangePasswordController(this);
        mController.setListener(this);

        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mQuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });

    }

    //修改操作
    private void change() {
        String shoujihao = mShoujihaoma.getText().toString();
        if (TextUtils.isEmpty(shoujihao)) {
            ToastUtil.showToast(ChangePasswordActivity.this, "请输入正确的手机号");
            return;
        }
        if (shoujihao.length() == 11) {
            ToastUtil.showToast(ChangePasswordActivity.this, "请输入正确的手机号");
            return;
        }
        mController.sendAsyncMessage(IDMessage.MUZE_CONTEXT_CHANGE_ACTION, shoujihao);
    }

    @Override
    protected void initUi() {
        mFanhui = (ImageView) findViewById(R.id.change_fanhui);
        mShoujihaoma = (EditText) findViewById(R.id.change_shoujihao);
        mQuding = (TextView) findViewById(R.id.change_quding);
    }

    @Override
    public void onModeChange(int action, Object... values) {
        if (values == null) {
            ToastUtil.showToast(ChangePasswordActivity.this, "请连网,亲");
            return;
        }
        Message mag = new Message();
        mag.what = action;
        mag.obj = values[0];
        mHandler.sendMessage(mag);
    }
}
