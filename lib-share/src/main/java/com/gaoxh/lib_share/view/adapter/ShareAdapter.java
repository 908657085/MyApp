package com.gaoxh.lib_share.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaoxh.lib_share.R;
import com.gaoxh.lib_share.bean.ShareChannel;

import java.util.List;

/**
 * @author 高雄辉
 * @Description:分享列表数据适配器
 * @date 16/10/8 上午10:22
 */
public class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.ShareViewHolder> {
    private Context mContext;
    private List<ShareChannel> shareChannels;
    private OnShareChannelClickListener shareChannelClickListener;


    public ShareAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ShareAdapter(Context mContext, List<ShareChannel> shareChannels) {
        this.mContext = mContext;
        this.shareChannels = shareChannels;
    }

    public ShareAdapter(Context mContext, List<ShareChannel> shareChannels, OnShareChannelClickListener shareChannelClickListener) {
        this.mContext = mContext;
        this.shareChannels = shareChannels;
        this.shareChannelClickListener = shareChannelClickListener;
    }

    @Override
    public ShareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_share_channel, parent, false);
        return new ShareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShareViewHolder holder, int position) {
        final ShareChannel shareChannel = shareChannels.get(position);
        holder.ivShareChannel.setImageResource(shareChannel.getDefaultChannelIcon());
        holder.tvShareChannel.setText(shareChannel.getChannelName());
        holder.llShareChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shareChannelClickListener != null) {
                    shareChannelClickListener.ShareChannelClick(shareChannel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shareChannels != null ? shareChannels.size() : 0;
    }


    public void setShareChannelClickListener(OnShareChannelClickListener shareChannelClickListener) {
        this.shareChannelClickListener = shareChannelClickListener;
    }

    public interface OnShareChannelClickListener {
        void ShareChannelClick(ShareChannel shareChannel);
    }

    public static class ShareViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout llShareChannel;
        public ImageView ivShareChannel;
        public TextView tvShareChannel;

        public ShareViewHolder(View itemView) {
            super(itemView);
            llShareChannel = (LinearLayout) itemView.findViewById(R.id.item_share_channel);
            ivShareChannel = (ImageView) itemView.findViewById(R.id.iv_item_share_channel);
            tvShareChannel = (TextView) itemView.findViewById(R.id.tv_item_share_channel);
        }
    }
}
