package com.gaoxh.myapp.di.components;

import com.gaoxh.myapp.di.PerActivity;
import com.gaoxh.myapp.di.modules.MainModule;
import com.gaoxh.myapp.main.MainActivity;
import com.gaoxh.myapp.utils.ApplicationUtils;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

/**
 * @author 高雄辉
 * @Description:首页注入
 * @date 16/9/20 下午2:42
 */
@Subcomponent(modules = MainModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

    ApplicationUtils applicationUtils();
}
