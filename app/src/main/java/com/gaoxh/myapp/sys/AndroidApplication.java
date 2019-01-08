package com.gaoxh.myapp.sys;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.gaoxh.myapp.BuildConfig;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.ApplicationComponent;
import com.gaoxh.myapp.di.components.DaggerApplicationComponent;
import com.gaoxh.myapp.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import java.util.Arrays;
import java.util.List;


/**
 * Created by 高雄辉 on 19/05/2016 16:54
 */
public class AndroidApplication extends Application implements HasComponent<ApplicationComponent>, ReactApplication {


    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }
    };
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeLeakDetection();
        initializeBaiduSdk();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void initializeBaiduSdk(){
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public ApplicationComponent getComponent() {
        return this.applicationComponent;
    }
}
