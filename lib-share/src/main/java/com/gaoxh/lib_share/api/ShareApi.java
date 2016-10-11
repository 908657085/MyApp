package com.gaoxh.lib_share.api;

import android.content.Context;

import com.gaoxh.lib_share.bean.IShareItem;

/**
 * @author 高雄辉
 * @Description:分享API
 * @date 16/10/8 上午11:25
 */
public interface ShareApi {
    /**
     * 分享API
     */
    void share(Context context, IShareItem shareItem);
}
