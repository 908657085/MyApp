package com.gaoxh.myapp.sys;

import android.app.Application;


import com.gaoxh.myapp.BuildConfig;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.ApplicationComponent;
import com.gaoxh.myapp.di.components.DaggerApplicationComponent;
import com.gaoxh.myapp.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;


/**
 * Created by 高雄辉 on 19/05/2016 16:54
 */
public class AndroidApplication extends Application implements HasComponent<ApplicationComponent>, ReactApplication {


    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeLeakDetection();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }


    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        protected boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
                    );
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public ApplicationComponent getComponent() {
        return this.applicationComponent;
    }
}
