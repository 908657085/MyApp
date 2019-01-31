package com.gaoxh.myapp.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 高雄辉
 * @createDate 19-1-31
 * @description
 */
@Scope
@Retention(RUNTIME)
public @interface Service {
}
