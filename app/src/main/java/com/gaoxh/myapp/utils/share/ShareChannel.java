package com.gaoxh.myapp.utils.share;

/**
 * @author 高雄辉
 * @Description:分享支持渠道
 * @date 16/9/26 下午7:02
 */
public enum ShareChannel {
    QQ("QQ", "QQ", "qq"),
    QQZONE("QQ空间", "QQZONE", "qqZone"),
    WECHAT("微信", "WECHAT", "wechat"),
    FRIENDCIRCLE("微信朋友圈", "FRIENDCIRCLE", "friendCircle"),
    WEIBO("微博", "WEIBO", "weibo");

    private String channelName;
    private String channelIcon;
    private String channelType;

    ShareChannel(String channelName, String channelIcon, String channelType) {
        this.channelName = channelName;
        this.channelIcon = channelIcon;
        this.channelType = channelType;
    }
}
