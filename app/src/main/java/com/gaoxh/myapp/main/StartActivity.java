package com.gaoxh.myapp.main;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;
import com.gaoxh.myapp.demo.ViewTestActivity;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.StartActivityComponent;
import com.gaoxh.myapp.di.components.DaggerStartActivityComponent;
import com.gaoxh.myapp.main.service.TestService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 高雄辉 on 2016/5/3.
 */
public class StartActivity extends BaseActivity implements HasComponent<StartActivityComponent> {
    private static final String TAG = StartActivity.class.getName();

    private StartActivityComponent startActivityComponent;

    @Inject
    @ContextType(ContextType.Type.Activity)
    public Context context;

    @Inject
    @ContextType(ContextType.Type.Application)
    public Context applicationContext;

    @Inject
    public Activity activity;


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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void navigateToMain() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void navigateToMainReact() {
        Intent intent = new Intent(context, MainReactActivity.class);
        context.startActivity(intent);
    }

    public void navigateToViewTest() {
        Intent intent = new Intent(context, ViewTestActivity.class);
        context.startActivity(intent);
    }


}
