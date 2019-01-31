package com.gaoxh.data.net.api;

import com.gaoxh.data.entity.map.Location;
import com.gaoxh.data.net.ApiResult;

import retrofit2.http.POST;
import rx.Observable;

/**
 * @author 高雄辉
 * @createDate 19-1-31
 * @description 地图相关API
 */
public interface MapApi {

    /**
     * @param userId   用户标识
     * @param location 当前用户定位信息
     * @return 上传服务器结果
     */
    @POST("api/map/location/upload")
    Observable<ApiResult> uploadLocation(String userId, Location location);
}
