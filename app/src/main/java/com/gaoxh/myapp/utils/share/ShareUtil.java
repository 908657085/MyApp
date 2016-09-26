package com.gaoxh.myapp.utils.share;

import com.gaoxh.myapp.utils.share.bean.IShareItem;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author 高雄辉
 * @Description:分享工具
 * @date 16/9/26 下午6:45
 */
@Singleton
public class ShareUtil {
    //分享回调
    private OnShareCallBackListener onShareCallBackListener;


    @Inject
    public ShareUtil() {
    }


    /**
     * @param shareItem               分享内容
     * @param shareParams             分享控制参数
     * @param onShareCallBackListener 分享回调
     */
    public void share(IShareItem shareItem, ShareParams shareParams, OnShareCallBackListener onShareCallBackListener) {
        //检测分享内容
        //检测上一次分享是否已经完成,防止重复调用
        //弹出分享平台选择框
    }


    /**
     * 分享统一回调
     */
    public interface OnShareCallBackListener {
        /**
         * 分享成功
         */
        void success();

        /**
         * 分享失败
         */
        void fail();

        /**
         * 分享取消
         */
        void cancel();
    }
}
