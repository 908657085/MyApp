package com.gaoxh.myapp.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gaoxh.lib_share.ShareUtil;
import com.gaoxh.lib_share.bean.ShareItem;
import com.gaoxh.lib_share.bean.ShareParams;
import com.gaoxh.lib_speech.SpeechRecognitionUtils;
import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;
import com.gaoxh.myapp.demo.SpeechCalculatorActivity;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.DaggerMainActivityComponent;
import com.gaoxh.myapp.di.components.MainActivityComponent;
import com.gaoxh.myapp.di.modules.MainModule;
import com.gaoxh.myapp.di.modules.ShareModule;
import com.gaoxh.myapp.main.service.TestService;
import com.gaoxh.widgets.CanvasView;
import com.iflytek.cloud.SpeechUtility;


import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;

/**
 * @author 高雄辉
 * @Description:
 * @date 16/9/19 下午3:31
 */
public class MainActivity extends BaseActivity implements HasComponent<MainActivityComponent> {

    private static final String TAG = MainActivity.class.getName();



    private MainActivityComponent component;

    @Inject
    @ContextType(ContextType.Type.Application)
    public Context applicationContext;
    @Inject
    @ContextType(ContextType.Type.Activity)
    public Context context;

    @Inject
    @Named("A")
    public String a;

    @Inject
    @Named("B")
    public String b;

    @Inject
    @Named("C")
    public Lazy<String> c;

    @Inject
    public Lazy<ShareUtil> shareUtil;


    private TestService.TestBinder testBinder;

    private ServiceConnection testConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            testBinder = (TestService.TestBinder) iBinder;
            Log.d(TAG, "bindItem=" + testBinder.getCurrentItem());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            testBinder = null;
        }
    };


    @Override
    public void setView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initializeInjector() {
        component = DaggerMainActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mainModule(new MainModule(this))
                .shareModule(new ShareModule())
                .build();
        component.inject(this);
    }

    @Override
    public MainActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        bindService(new Intent(context, TestService.class), testConnection, BIND_AUTO_CREATE);
        SpeechUtility speechUtility = SpeechRecognitionUtils.initSpeechSdk(applicationContext);
        System.out.println("speechUtility:" + speechUtility);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }




    @Override
    protected void onDestroy() {
        unbindService(testConnection);
        super.onDestroy();
    }
}
