package com.gaoxh.myapp.di.modules;

import android.content.Context;

import com.gaoxh.data.net.ApiRetrofit;
import com.gaoxh.data.net.api.MapApi;

import dagger.Module;
import dagger.Provides;

/**
 * @author 高雄辉
 * @createDate 19-1-31
 * @description
 */
@Module
public class ApiModule {

    private Context context;


    public ApiModule(Context context) {
        this.context = context;
    }

    @Provides
    Context context(){
        return context;
    }

    @Provides
    MapApi provideMapApi(ApiRetrofit apiRetrofit){
        return apiRetrofit.createService(MapApi.class);
    }
}
