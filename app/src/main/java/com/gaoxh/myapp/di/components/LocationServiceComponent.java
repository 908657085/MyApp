package com.gaoxh.myapp.di.components;

import com.gaoxh.myapp.di.Service;
import com.gaoxh.myapp.main.service.LocationService;

import dagger.Component;

/**
 * @author 高雄辉
 * @createDate 19-1-31
 * @description
 */
@Service
@Component(dependencies = ApplicationComponent.class)
public interface LocationServiceComponent {
   void inject(LocationService locationService);
}
