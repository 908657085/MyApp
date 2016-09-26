package com.gaoxh.myapp.di.components;

import com.gaoxh.myapp.di.PerActivity;
import com.gaoxh.myapp.di.modules.ActivityModule;
import com.gaoxh.myapp.di.modules.MainModule;
import com.gaoxh.myapp.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

/**
 * @author 高雄辉
 * @Description:首页注入
 * @date 16/9/20 下午2:42
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class,MainModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
