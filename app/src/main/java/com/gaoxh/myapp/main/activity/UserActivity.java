package com.gaoxh.myapp.main.activity;

import com.gaoxh.data.net.api.UserApi;
import com.gaoxh.myapp.base.BaseActivity;
import com.gaoxh.myapp.di.components.ActivityComponent;
import com.gaoxh.myapp.di.components.DaggerActivityComponent;
import com.gaoxh.myapp.di.modules.ActivityModule;

import javax.inject.Inject;

/**
 * @author 高雄辉
 * @createDate 19-2-21
 * @description
 */
public class UserActivity extends BaseActivity {


    @Inject
    public UserApi userApi;
    ActivityComponent activityComponent;

    @Override
    public void setView() {

    }

    @Override
    public void initializeInjector() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }
}
