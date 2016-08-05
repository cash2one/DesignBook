package com.m520it.designbook.protocol;

import android.view.View;

/**
 * @author 吕朝
 * @version $Rev$
 * @time 2016/8/1 16:19
 * @desc ${TODO}
 * @updateAuthor $Author$
 * @uodateDate $Date$
 */
public interface IPopupWindowProtocal {
    public void onShow(View anchor);

    public void onDismiss();
}
