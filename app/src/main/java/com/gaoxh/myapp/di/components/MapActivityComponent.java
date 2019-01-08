package com.gaoxh.myapp.di.components;

import com.gaoxh.myapp.di.PerActivity;
import com.gaoxh.myapp.di.modules.ActivityModule;
import com.gaoxh.myapp.di.modules.MainModule;
import com.gaoxh.myapp.di.modules.ShareModule;
import com.gaoxh.myapp.main.MainActivity;
import com.gaoxh.myapp.main.MapActivity;

import dagger.Component;

/**
 * @author 高雄辉
 * @Description:首页注入
 * @date 16/9/20 下午2:42
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface MapActivityComponent {
    void inject(MapActivity mainActivity);
}