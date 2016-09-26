package com.gaoxh.myapp.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author 高雄辉
 * @Description: Application SCOPE
 * @date 16/9/22 下午2:45
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Application {}
