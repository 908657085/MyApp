package com.gaoxh.myapp.utils.share.bean;

import com.gaoxh.myapp.utils.share.ShareChannel;

/**
 * @author 高雄辉
 * @Description: 分享实体类
 * @date 16/7/19 下午5:47
 * <p/>
 * Created by apple on 16/7/19.
 */
public interface IShareItem {
    /**
     * 判断数据合法性
     * @return true：合法   false：不合法
     */
    public boolean isLegal(ShareChannel shareChannel);
}
