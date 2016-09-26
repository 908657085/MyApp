/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gaoxh.myapp.di.components;

import android.content.Context;


import com.gaoxh.data.cache.SharedPreferencesHelper;
import com.gaoxh.data.net.ApiRetrofit;
import com.gaoxh.myapp.di.Application;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.modules.ApplicationModule;
import com.gaoxh.myapp.sys.AndroidApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Application
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ContextType(ContextType.Type.Application)
    Context applicationContext();


    AndroidApplication application();


    SharedPreferencesHelper sharedPreferencesHelper();
}
