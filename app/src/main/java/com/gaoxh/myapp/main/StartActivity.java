package com.gaoxh.myapp.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.DaggerStartActivityComponent;
import com.gaoxh.myapp.di.components.StartActivityComponent;
import com.gaoxh.myapp.main.service.LocationService;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * Created by 高雄辉 on 2016/5/3.
 */
public class StartActivity extends BaseActivity implements HasComponent<StartActivityComponent> {
    private static final String TAG = StartActivity.class.getName();
    @Inject
    @ContextType(ContextType.Type.Activity)
    public Context context;
    @Inject
    @ContextType(ContextType.Type.Application)
    public Context applicationContext;
    @Inject
    public Activity activity;
    private StartActivityComponent startActivityComponent;

    @Override
    public void setView() {
        setContentView(R.layout.activity_start);
    }


    @Override
    public void initializeInjector() {
        startActivityComponent = DaggerStartActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        startActivityComponent.inject(this);
    }

    @Override
    public StartActivityComponent getComponent() {
        return startActivityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToMain();
                StartActivity.this.finish();
            }
        }, 3000);
        startBackService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void startBackService(){
        Intent intent = new Intent(context,LocationService.class);
        startService(intent);
    }

    public void navigateToMain() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    public void navigateToMainReact() {
        Intent intent = new Intent(context, MainReactActivity.class);
        startActivity(intent);
    }

}
