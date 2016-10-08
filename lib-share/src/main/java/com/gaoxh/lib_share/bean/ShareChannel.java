package com.gaoxh.lib_share.bean;

import com.gaoxh.lib_share.R;

/**
 * @author 高雄辉
 * @Description:分享支持渠道
 * @date 16/9/26 下午7:02
 */
public enum ShareChannel {
    QQ("QQ", "QQ", "qq", R.mipmap.icon_qq),
    QQZONE("QQ空间", "QQZONE", "qqZone",R.mipmap.icon_qqzone),
    WECHAT("微信", "WECHAT", "wechat",R.mipmap.icon_wechat),
    FRIENDCIRCLE("微信朋友圈", "FRIENDCIRCLE", "friendCircle",R.mipmap.icon_friendcircle),
    WEIBO("微博", "WEIBO", "weibo",R.mipmap.icon_weibo),
    MORE("更多", "more", "more",R.drawable.ic_more);

    private String channelName;
    private String channelIcon;
    private String channelType;
    private int defaultChannelIcon;

    ShareChannel(String channelName, String channelIcon, String channelType) {
        this.channelName = channelName;
        this.channelIcon = channelIcon;
        this.channelType = channelType;
    }


    ShareChannel(String channelName, String channelIcon, String channelType, int defaultChannelIcon) {
        this.channelName = channelName;
        this.channelIcon = channelIcon;
        this.channelType = channelType;
        this.defaultChannelIcon = defaultChannelIcon;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelIcon() {
        return channelIcon;
    }

    public void setChannelIcon(String channelIcon) {
        this.channelIcon = channelIcon;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public int getDefaultChannelIcon() {
        return defaultChannelIcon;
    }

    public void setDefaultChannelIcon(int defaultChannelIcon) {
        this.defaultChannelIcon = defaultChannelIcon;
    }
}
