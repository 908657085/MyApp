package com.gaoxh.myapp.base;

/**
 * Created by 高雄辉 on 2016/5/24 15:00
 * 通用界面操作
 */
public interface BaseView {

    /**
     * 显示 加载
     */
    public void showLoading();

    /**
     * 隐藏 加载
     */
    public void hideLoading();

    /**
     * 显示提示信息
     */
    public void showMsg(String msg);

}
