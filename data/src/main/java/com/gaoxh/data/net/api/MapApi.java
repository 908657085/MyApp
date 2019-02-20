package com.gaoxh.data.net.api;

import com.gaoxh.data.entity.map.Location;
import com.gaoxh.data.net.ApiResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author 高雄辉
 * @createDate 19-1-31
 * @description 地图相关API
 */
public interface MapApi {

    /**
     * 上传用户位置信息
     * @param userId 用户ID
     * @param radius 精度
     * @param direction 方向
     * @param longitude 经度
     * @param latitude 纬度
     * @param addr 具体地址
     * @return 上传结果
     */
    @FormUrlEncoded
    @POST("uploadLocation")
    Observable<ApiResult> uploadLocation(@Field("userId") String userId,@Field("radius") float  radius,@Field("direction")int direction,@Field("longitude")double longitude,@Field("latitude")double latitude,@Field("addr")String addr);
}
