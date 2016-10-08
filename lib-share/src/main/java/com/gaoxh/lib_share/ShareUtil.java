package com.gaoxh.lib_share;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.gaoxh.lib_share.bean.IShareItem;
import com.gaoxh.lib_share.bean.ShareParams;
import com.gaoxh.lib_share.view.ShareDialog;

/**
 * @author 高雄辉
 * @Description:分享工具
 * @date 16/9/26 下午6:45
 */
public class ShareUtil {
    private static Object object = new Object();
    private static ShareUtil instance;

    //分享回调
    private OnShareCallBackListener shareCallBackListener;


    private ShareUtil() {
    }

    public static ShareUtil getInstance() {
        synchronized (object) {
            if (instance == null) {
                instance = new ShareUtil();
            }
        }
        return instance;
    }


    /**
     * @param context                 上下文
     * @param shareItem               分享内容
     * @param shareParams             分享控制参数
     * @param onShareCallBackListener 分享回调
     */
    public void share(Context context, IShareItem shareItem, ShareParams shareParams, OnShareCallBackListener onShareCallBackListener) {
        //检测上一次分享是否已经完成,防止重复调用
        if (this.shareCallBackListener != null) {
            Toast.makeText(context, ShareConstants.MSG_LASTSHARE_UNFINISH, Toast.LENGTH_SHORT).show();
            return;
        } else {
            this.shareCallBackListener = onShareCallBackListener;
        }
        //弹出分享平台选择框
        ShareDialog shareDialog = new ShareDialog(context, shareItem, null);
        shareDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (shareCallBackListener != null) {
                    shareCallBackListener.cancel();
                    shareCallBackListener = null;
                }
            }
        });
        shareDialog.show();
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
