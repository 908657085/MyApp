package com.gaoxh.data.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.gaoxh.data.cache.SharedPreferencesHelper;
import com.gaoxh.data.contstants.Constants_Api;
import com.gaoxh.data.contstants.Constants_SharedPreferences;
import com.gaoxh.data.contstants.Constants_Sys;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 高雄辉 on 20/05/2016 11:21
 * API请求发起以及Token管理
 */
@Singleton
public class ApiRetrofit {

    private  Retrofit retrofit = null;


    @Inject
   public ApiRetrofit(ApiTokenIntercepter apiTokenIntercepter) {
        createRetrofit(createOkHttpClient(apiTokenIntercepter));
    }


    private void createRetrofit(OkHttpClient okHttpClient){
        retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants_Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private OkHttpClient createOkHttpClient(ApiTokenIntercepter apiTokenIntercepter) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                        .writeTimeout(5000, TimeUnit.MILLISECONDS)
                                        .readTimeout(5000,TimeUnit.MILLISECONDS)
                                        .addInterceptor(createLogIntercepter())
                                        .addInterceptor(apiTokenIntercepter)
                                        .build();
        return okHttpClient;
    }

    private Interceptor createLogIntercepter(){
        Interceptor logInterceptor= new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger(){

            @Override
            public void log(String message) {
                System.out.println(message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }





    public  <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
