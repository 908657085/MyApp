package com.gaoxh.myapp.di.components;

import com.gaoxh.data.net.api.MapApi;
import com.gaoxh.myapp.di.modules.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author 高雄辉
 * @createDate 19-1-31
 * @description
 */
@Singleton
@Component (modules = ApiModule.class)
public interface ApiComponent {
    MapApi mapApi();
}
