package com.gaoxh.myapp.di.modules;

import com.gaoxh.myapp.main.activity.MainActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author 高雄辉
 * @Description:
 * @date 16/9/20 下午2:43
 */
@Module
public class MainModule {

    public MainModule(MainActivity activity) {
    }

    @Provides
    @Named("A")
    String provideA(){
        return "A";
    }

    @Provides
    @Named("B")
    String provideB(){
        return "B";
    }

    @Provides
    @Named("C")
    String provideC(){
        System.out.println("生成C");
        return "C";
    }

}
