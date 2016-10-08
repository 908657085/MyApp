package com.gaoxh.lib_share.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 高雄辉
 * @Description:分享控制参数
 * @date 16/9/26 下午7:16
 */
public class ShareParams {
    public ShareTheme shareTheme;
    public List<ShareChannel> shareChannelList;


    public ShareParams(Builder builder) {
        this.shareTheme=builder.shareTheme;
        shareChannelList=new ArrayList<>();
        if(builder.qqShareAvailable){
            shareChannelList.add(ShareChannel.QQ);
        }
        if(builder.qqZoneShareAvailable){
            shareChannelList.add(ShareChannel.QQZONE);
        }
        if(builder.wechatShareAvailable){
            shareChannelList.add(ShareChannel.WECHAT);
        }
        if(builder.friendCircleShareAvailable){
            shareChannelList.add(ShareChannel.FRIENDCIRCLE);
        }
        if(builder.weiboShareAvailable){
            shareChannelList.add(ShareChannel.WEIBO);
        }
    }

    public static class Builder {

        private ShareTheme shareTheme;

        private boolean qqShareAvailable;
        private boolean qqZoneShareAvailable;
        private boolean wechatShareAvailable;
        private boolean friendCircleShareAvailable;
        private boolean weiboShareAvailable;

        public Builder() {
            shareTheme = ShareTheme.Center;
            qqShareAvailable = true;
            qqZoneShareAvailable = true;
            wechatShareAvailable = true;
            friendCircleShareAvailable = true;
            weiboShareAvailable = true;
        }

        public Builder setShareTheme(ShareTheme shareTheme) {
            this.shareTheme = shareTheme;
            return this;
        }

        public Builder setQqShareAvailable(boolean qqShareAvailable) {
            this.qqShareAvailable = qqShareAvailable;
            return this;
        }


        public Builder setQqZoneShareAvailable(boolean qqZoneShareAvailable) {
            this.qqZoneShareAvailable = qqZoneShareAvailable;
            return this;
        }


        public Builder setWechatShareAvailable(boolean wechatShareAvailable) {
            this.wechatShareAvailable = wechatShareAvailable;
            return this;
        }


        public Builder setFriendCircleShareAvailable(boolean friendCircleShareAvailable) {
            this.friendCircleShareAvailable = friendCircleShareAvailable;
            return this;
        }

        public Builder setWeiboShareAvailable(boolean weiboShareAvailable) {
            this.weiboShareAvailable = weiboShareAvailable;
            return this;
        }

        public ShareParams build(){
            return new ShareParams(this);
        }
    }
}
