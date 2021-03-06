package com.gaoxh.myapp.di.components;

import android.content.Context;

import com.facebook.react.ReactInstanceManager;
import com.gaoxh.data.cache.SharedPreferencesHelper;
import com.gaoxh.data.net.api.MapApi;
import com.gaoxh.data.net.api.UserApi;
import com.gaoxh.myapp.di.Application;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.modules.ApplicationModule;
import com.gaoxh.myapp.sys.AndroidApplication;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Application
@Component(dependencies = ApiComponent.class, modules = {ApplicationModule.class})
public interface ApplicationComponent {

    @ContextType(ContextType.Type.Application)
    Context applicationContext();


    AndroidApplication application();


    SharedPreferencesHelper sharedPreferencesHelper();

    ReactInstanceManager reactInstanceManager();

    MapApi mapApi();

    UserApi userApi();

    void inject(AndroidApplication application);

}
