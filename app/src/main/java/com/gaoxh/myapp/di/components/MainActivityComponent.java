package com.gaoxh.myapp.di.components;

import com.gaoxh.myapp.di.PerActivity;
import com.gaoxh.myapp.di.modules.ActivityModule;
import com.gaoxh.myapp.di.modules.MainModule;
import com.gaoxh.myapp.di.modules.ShareModule;
import com.gaoxh.myapp.main.activity.MainActivity;
import com.gaoxh.myapp.main.fragment.MainFragment;
import com.gaoxh.myapp.main.fragment.MainReactFragment;

import dagger.Component;

/**
 * @author 高雄辉
 * @Description:首页注入
 * @date 16/9/20 下午2:42
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class,MainModule.class, ShareModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);

    void inject(MainReactFragment mainReactFragment);
}
