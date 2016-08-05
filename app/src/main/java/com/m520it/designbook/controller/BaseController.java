package com.m520it.designbook.controller;

import android.app.Activity;
import android.content.Context;

import com.m520it.designbook.base.MyApplication;
import com.m520it.designbook.protocol.IModelChangedListener;

public abstract class BaseController {

	protected IModelChangedListener mListener;
	protected Context mContext;
	protected long mUserId;
	
	public BaseController(Context mContext) {
		this.mContext = mContext;

	}

	public void setListener(IModelChangedListener listener) {
		this.mListener = listener;
	}

	/**
	 *
	 */
	public void sendAsyncMessage(final int action,final Object... values){
		new Thread(){
			public void run() {
				handlerMessage(action, values);
			}
		}.start();
	}
	
	/**
	 *
	 */
	protected abstract void handlerMessage(int action,Object... values);
	
}
