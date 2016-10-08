package com.gaoxh.lib_share.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * 图文链接bean
 * 注意：微信、朋友圈、微博分享图需要bitmap，qq和空间分享图需要网络url
 * 所以在bitmap在微信、朋友圈、微博平台用本地资源
 * Created by songlei on 16/1/1.
 */
public class ShareItem implements Parcelable,IShareItem {
    /**
     * 标题
     */
    private String title;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 图片网络地址
     */
    private String image;
    /**
     * 描述内容
     */
    private String content;

    public ShareItem() {
    }

    /**
     * 图文链接bean构造方法
     * @param title 标题
     * @param url 链接
     * @param image 图片url
     * @param content 内容
     */
    public ShareItem(String title, String url, String image, String content) {
        this.title = title;
        this.url = url;
        this.image = image;
        this.content = content;
    }

    protected ShareItem(Parcel in) {
        title = in.readString();
        url = in.readString();
        image = in.readString();
        content = in.readString();
    }

    public static final Creator<ShareItem> CREATOR = new Creator<ShareItem>() {
        @Override
        public ShareItem createFromParcel(Parcel in) {
            return new ShareItem(in);
        }

        @Override
        public ShareItem[] newArray(int size) {
            return new ShareItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 判断数据是否合法
     * @return 是否合法
     */
    @Override
    public boolean isLegal(ShareChannel shareChannel) {
        if (TextUtils.isEmpty(content)) {
            return false;
        }
        if (TextUtils.isEmpty(title)) {
            return false;
        }
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        return true;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(image);
        dest.writeString(content);
    }
}
