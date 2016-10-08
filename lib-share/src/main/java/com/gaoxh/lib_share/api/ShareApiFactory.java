package com.gaoxh.lib_share.api;

import com.gaoxh.lib_share.bean.ShareChannel;

/**
 * @author 高雄辉
 * @Description:创建分享API静态工厂
 * @date 16/10/8 上午11:28
 */
public class ShareApiFactory {

    public static ShareApi getShareApi(ShareChannel shareChannel){
        switch (shareChannel){
            case WECHAT:
                return new WeChatShareApi();
            case FRIENDCIRCLE:
                return new FriendCircleShareApi();
            case QQ:
                return new QQShareApi();
            case QQZONE:
                return new QQZoneShareApi();
            case WEIBO:
                return new WeiBoShareApi();
            default:
                return new WeChatShareApi();
        }
    }

}
