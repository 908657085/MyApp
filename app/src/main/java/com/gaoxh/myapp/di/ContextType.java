package com.gaoxh.myapp.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 高雄辉
 * @Description:
 * @date 16/9/26 下午2:15
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ContextType {
    enum Type {
        Application, Activity;
    }

    Type value() default Type.Activity;
}