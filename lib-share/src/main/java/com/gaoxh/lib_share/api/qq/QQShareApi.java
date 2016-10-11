package com.gaoxh.lib_share.api.qq;

import android.content.Context;
import android.content.Intent;

import com.gaoxh.lib_share.ShareConstants;
import com.gaoxh.lib_share.api.ShareApi;
import com.gaoxh.lib_share.bean.IShareItem;
import com.gaoxh.lib_share.bean.ShareChannel;

/**
 * @author 高雄辉
 * @Description: QQ分享
 * @date 16/10/8 上午11:26
 */
public class QQShareApi implements ShareApi {
    /**
     * 分享API
     *@param context
     * @param shareItem
     */
    @Override
    public void share(Context context, IShareItem shareItem) {
        Intent intent=new Intent(context,QQCallBackActivity.class);
        intent.putExtra(ShareConstants.SHARE_ITEM,shareItem);
        intent.putExtra(ShareConstants.SHARE_CHANNEL, ShareChannel.QQ);
        context.startActivity(intent);
    }
}
