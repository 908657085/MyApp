package com.gaoxh.myapp.sys;

import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.gaoxh.data.cache.SharedPreferencesHelper;
import com.gaoxh.data.contstants.Constants_SharedPreferences;
import com.gaoxh.myapp.BuildConfig;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.ApplicationComponent;
import com.gaoxh.myapp.di.components.DaggerApiComponent;
import com.gaoxh.myapp.di.components.DaggerApplicationComponent;
import com.gaoxh.myapp.di.modules.ApiModule;
import com.gaoxh.myapp.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by 高雄辉 on 19/05/2016 16:54
 */
public class AndroidApplication extends MultiDexApplication implements HasComponent<ApplicationComponent>, ReactApplication {


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
    @Inject
    public SharedPreferencesHelper sharedPreferencesHelper;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeLeakDetection();
        initializeBaiduSdk();
        initUserId();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .apiComponent(DaggerApiComponent.builder().apiModule(new ApiModule(this)).build())
                .applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void initializeBaiduSdk() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    private void initUserId() {
        sharedPreferencesHelper.setString(Constants_SharedPreferences.USER_ID, "1");
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
