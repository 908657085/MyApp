package com.gaoxh.lib_share.api.qq;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gaoxh.lib_share.ShareConstants;
import com.gaoxh.lib_share.bean.IShareItem;
import com.gaoxh.lib_share.bean.ShareChannel;
import com.gaoxh.lib_share.bean.ShareItem;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * QQ平台统一回调处理
 * Created by 高雄辉 on 16/10/9.
 */
public class QQCallBackActivity extends Activity {

    private Context mContext;
    private IUiListener qqCallbackListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = QQCallBackActivity.this;
        ShareChannel shareChannel= (ShareChannel) getIntent().getExtras().get(ShareConstants.SHARE_CHANNEL);
        IShareItem shareItem = (IShareItem) getIntent().getSerializableExtra(ShareConstants.SHARE_ITEM);
        System.out.println(shareChannel);
        System.out.println(shareItem instanceof ShareItem);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_QQ_SHARE) {
            Tencent.onActivityResultData(requestCode, resultCode, data, getQqCallbackListener());
        }
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initUiCallbackListener() {
        qqCallbackListener = new IUiListener() {

            @Override
            public void onComplete(Object o) {

            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };
    }

    private IUiListener getQqCallbackListener() {
        if (qqCallbackListener == null) {
            initUiCallbackListener();
        }
        return qqCallbackListener;
    }



}
