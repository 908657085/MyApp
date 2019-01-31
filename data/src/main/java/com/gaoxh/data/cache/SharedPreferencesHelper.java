package com.gaoxh.data.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.gaoxh.data.contstants.Constants_SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by 高雄辉 on 2016/5/25 15:58
 * 文本存储器工具类
 */
@Singleton
public class SharedPreferencesHelper  {
    private SharedPreferences sharedPreferences;
    @Inject
    public SharedPreferencesHelper(Context context) {
        this.sharedPreferences=context.getSharedPreferences(Constants_SharedPreferences.SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }


    public String readToken(){
        String token=this.sharedPreferences.getString(Constants_SharedPreferences.TOKEN_NAME,null);
        return token;
    }

    public void addToken(String token){
        this.sharedPreferences.edit().putString(Constants_SharedPreferences.TOKEN_NAME,token).commit();
    }

    public void removeToken(){
        this.sharedPreferences.edit().remove(Constants_SharedPreferences.TOKEN_NAME);
    }


    public void cacheUserInfo(String accessToken, String userName, String pwd) {
        this.sharedPreferences.edit().putString(Constants_SharedPreferences.USER_NAME,userName).commit();
        this.sharedPreferences.edit().putString(Constants_SharedPreferences.USER_PWD,pwd).commit();
        this.addToken(accessToken);
    }

    public void removeUserInfo() {
        this.sharedPreferences.edit().remove(Constants_SharedPreferences.USER_NAME).commit();
        this.sharedPreferences.edit().remove(Constants_SharedPreferences.USER_PWD).commit();
        this.removeToken();
    }


    public void setString(String key,String value){
        this.sharedPreferences.edit().putString(key,value).commit();
    }

    public String getString(String key){
        return this.sharedPreferences.getString(key,null);
    }
}
