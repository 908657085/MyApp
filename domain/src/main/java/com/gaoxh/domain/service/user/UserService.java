package com.gaoxh.domain.service.user;

import com.gaoxh.data.cache.SharedPreferencesHelper;
import com.gaoxh.data.net.ApiResult;
import com.gaoxh.data.net.api.UserApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by 高雄辉 on 2016/5/24 16:51
 */
public class UserService  {
    private UserApi userApi;
    private SharedPreferencesHelper sharedPreferencesHelper;
    
    @Inject
    public UserService(UserApi userApi, SharedPreferencesHelper sharedPreferencesHelper) {
        this.userApi = userApi;
        this.sharedPreferencesHelper=sharedPreferencesHelper;
    }

    public Observable<ApiResult> registerCode(String mobilePhone) {
        return userApi.getRegisterCode(mobilePhone);
    }


    public Observable<ApiResult> register(String userName, String pwd, String code, String registerCode) {
        return userApi.register(userName, pwd, code, registerCode);
    }

    public Observable<ApiResult> login(String userName, String pwd) {
        return userApi.login(userName, pwd);
    }


    public Observable<ApiResult> getUserInfo() {
        return userApi.getUserInfo();
    }


    public void cacheUserInfo(String accessToken, String userName, String pwd) {
     sharedPreferencesHelper.cacheUserInfo(accessToken, userName, pwd);
    }


    public void removeUserInfo() {
        sharedPreferencesHelper.removeUserInfo();
    }


    public void refreshToken(String accessToken) {
        sharedPreferencesHelper.addToken(accessToken);
    }


}
