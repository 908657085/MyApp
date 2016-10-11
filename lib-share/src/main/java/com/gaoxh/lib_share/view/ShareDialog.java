package com.gaoxh.lib_share.view;

import android.app.Dialog;
import android.content.Context;
import android.provider.Contacts;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.gaoxh.lib_share.R;
import com.gaoxh.lib_share.ShareUtil;
import com.gaoxh.lib_share.api.ShareApi;
import com.gaoxh.lib_share.api.ShareApiFactory;
import com.gaoxh.lib_share.bean.IShareItem;
import com.gaoxh.lib_share.bean.ShareChannel;
import com.gaoxh.lib_share.view.adapter.ShareAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 高雄辉
 * @Description:分享弹窗
 * @date 16/9/30 上午10:47
 */
public class ShareDialog extends Dialog {
    private static int DEFAULT_COLUMN_COUNT = 3;


    private Context mContext;

    private View view;
    private RecyclerView rvShare;

    private int columnCount = DEFAULT_COLUMN_COUNT;
    private List<ShareChannel> shareChannels;
    private IShareItem shareItem;

    public ShareDialog(Context context,IShareItem shareItem, List<ShareChannel> shareChannels) {
        super(context);
        this.shareItem=shareItem;
        this.shareChannels = shareChannels;
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        if (shareChannels == null || shareChannels.size() == 0) {
            initShareChannels();
        }
        if (columnCount <= 0) {
            columnCount = DEFAULT_COLUMN_COUNT;
        }
        initView();
        initListener();
    }

    /**
     * 初始化view
     */
    private void initView() {
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_share_bottom, null);
        rvShare = (RecyclerView) view.findViewById(R.id.rv_share);
    }

    /**
     * 绑定事件
     */
    private void initListener() {
        rvShare.setLayoutManager(new GridLayoutManager(mContext, columnCount));
        ShareAdapter shareAdapter = new ShareAdapter(mContext, shareChannels, new ShareAdapter.OnShareChannelClickListener() {
            @Override
            public void ShareChannelClick(ShareChannel shareChannel) {
                if(ShareDialog.this.isShowing()){
                    ShareDialog.this.dismiss();
                }
                ShareApi shareApi = ShareApiFactory.createInstance(shareChannel);
                shareApi.share(mContext,shareItem);
            }
        });
        rvShare.setAdapter(shareAdapter);
    }

    @Override
    public void show() {
        super.show();
        setLayout();
    }

    /**
     * 设置弹窗样式
     */
    private void setLayout() {
        Window mWindow = getWindow();
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setContentView(view);
        mWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mWindow.setWindowAnimations(R.style.dialog_bottom_animation);
        mWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.color.transparent));
    }

    private void initShareChannels() {
        shareChannels = new ArrayList<>();
        Collections.addAll(shareChannels, ShareChannel.values());
    }

}
