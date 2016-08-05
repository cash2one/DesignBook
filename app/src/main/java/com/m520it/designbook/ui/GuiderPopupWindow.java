package com.m520it.designbook.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.m520it.designbook.R;
import com.m520it.designbook.protocol.IPopupWindowProtocal;

/**
 * @author 吕朝
 * @version $Rev$
 * @time 2016/7/31 14:15
 * @desc ${TODO}
 * @updateAuthor $Author$
 * @uodateDate $Date$
 */
public class GuiderPopupWindow implements View.OnClickListener, IPopupWindowProtocal {

    private Context mContext;
    private TextView mCancleTv;
    private TextView mTrueTv;
    private PopupWindow mPopupWindow;

    public GuiderPopupWindow(Context context) {
        mContext = context;
        initUI();
    }


    //从布局文件获取控件
    private void initUI() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.window_guider_ask, null);
        //取消
        mCancleTv = (TextView) inflate.findViewById(R.id.guide_cancle);
        //确认
        mTrueTv = (TextView) inflate.findViewById(R.id.guide_true);

        /**--------设置点击事件----**/
        mCancleTv.setOnClickListener(this);
        mTrueTv.setOnClickListener(this);

        mPopupWindow = new PopupWindow(-1, -1);
        mPopupWindow.setContentView(inflate);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.update();
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guide_cancle:
                onDismiss();
                break;
            case R.id.guide_true:
                //Intent intent=new Intent();
                Toast.makeText(mContext, "打电话", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //显示事件
    @Override
    public void onShow(View anchor) {
        if (mPopupWindow != null) {
            mPopupWindow.showAtLocation(anchor, Gravity.CENTER, 0, 0);
        }
    }
    //消失事件
    @Override
    public void onDismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}
