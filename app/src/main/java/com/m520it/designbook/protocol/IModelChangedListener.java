package com.m520it.designbook.protocol;
/**
 * 后台已经从服务器 取出了数据了
 * 已经做了一个网络的请求 并且已经把数据传回来了 此时应该通知activity去修改UI
 * @author dragon
 *
 */
public interface IModelChangedListener {
	//这个aciton 是告诉别人 什么类型的数据加载完成了
	//values  后台返回的数据
	public void  onModeChange(int action, Object... values);
}
