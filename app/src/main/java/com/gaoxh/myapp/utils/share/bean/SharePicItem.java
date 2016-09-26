package com.gaoxh.myapp.utils.share.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.gaoxh.myapp.utils.share.ShareChannel;

import java.io.File;

/**
 * 纯图片bean
 * 注意：qq分享需要本地路径，微信、朋友圈、微博需要bitmap
 * 图片需进行压缩处理
 * Created by songlei on 16/4/4.
 */
public class SharePicItem implements Parcelable,IShareItem {
    /**
     * 图片本地路径
     */
    private String nativePicPath;
    /**
     * 图片bitmap对象
     */
    private Bitmap bitmap;

    public SharePicItem() {
    }

    public SharePicItem(String nativePicPath, Bitmap bitmap) {
        this.nativePicPath = nativePicPath;
        this.bitmap = bitmap;
    }

    protected SharePicItem(Parcel in) {
        nativePicPath = in.readString();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<SharePicItem> CREATOR = new Creator<SharePicItem>() {
        @Override
        public SharePicItem createFromParcel(Parcel in) {
            return new SharePicItem(in);
        }

        @Override
        public SharePicItem[] newArray(int size) {
            return new SharePicItem[size];
        }
    };

    public String getNativePicPath() {
        return nativePicPath;
    }

    public void setNativePicPath(String nativePicPath) {
        this.nativePicPath = nativePicPath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * 参数是否合法
     * @return 是否合法
     */
    @Override
    public boolean isLegal(ShareChannel shareChannel) {
        if (TextUtils.isEmpty(nativePicPath)) {
            return false;
        }
        if (bitmap == null) {
            return false;
        }
        return true;
    }

    /**
     * 本地文件是否存在
     * @return 是否存在
     */
    public boolean isPathExist() {
        File file = new File(nativePicPath);
        if (file.exists()) {
            return true;
        }
        return false;
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
        dest.writeString(nativePicPath);
        dest.writeParcelable(bitmap, flags);
    }
}
