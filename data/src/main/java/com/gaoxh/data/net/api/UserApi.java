package com.gaoxh.data.net.api;

import com.gaoxh.data.net.ApiResult;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 高雄辉 on 2016/5/24 15:18
 * 用户相关接口
 */
public interface UserApi {
    /**
     * 用户登录
     *
     * @param userName
     * @param pwd
     * @return
     */
    @POST("api/login/mobile")
    Observable<ApiResult> login(@Query("userName") String userName, @Query("pwd") String pwd);

    /**
     * 用户注册
     *
     * @param userName
     * @param pwd
     * @param code
     * @param registerCode
     * @return
     */
    @POST("api/register/mobile")
    Observable<ApiResult> register(@Query("userName") String userName, @Query("pwd") String pwd, @Query("code") String code, @Query("registerCode") String registerCode);

    /**
     * 获取注册手机验证码
     *
     * @param mobilePhone
     * @return
     */
    @GET("api/register/registerCode")
    Observable<ApiResult> getRegisterCode(@Query("mobilePhone") String mobilePhone);

    /**
     * 获取用户信息
     *
     * @return
     */
    @GET("api/user/info/info")
    Observable<ApiResult> getUserInfo();

    /**
     * 获取用户状态信息
     *
     * @return
     */
    @GET("api/user/status/info")
    Observable<ApiResult> getUserStatusInfo();

    @GET("api/user/account/info")
    Observable<ApiResult> getUserAccountInfo();

}
