package com.gaoxh.myapp.di.modules;

import com.gaoxh.lib_share.ShareUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author 高雄辉
 * @Description:
 * @date 16/9/30 上午10:29
 */
@Module
public class ShareModule {

    @Provides
    ShareUtil provideShareUtil(){
        return ShareUtil.getInstance();
    }
}
