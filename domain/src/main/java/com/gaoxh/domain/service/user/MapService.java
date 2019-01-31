package com.gaoxh.domain.service.user;

import android.text.TextUtils;

import com.gaoxh.data.cache.SharedPreferencesHelper;
import com.gaoxh.data.contstants.Constants_SharedPreferences;
import com.gaoxh.data.entity.map.Location;
import com.gaoxh.data.net.ApiResult;
import com.gaoxh.data.net.api.MapApi;
import com.gaoxh.data.utils.GsonUtils;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author 高雄辉
 * @createDate 19-1-31
 * @description 地图相关服务
 */
public class MapService {

    private MapApi mapApi;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Inject
    public MapService(MapApi mapApi, SharedPreferencesHelper sharedPreferencesHelper) {
        this.mapApi = mapApi;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    public Location getCurrentLocation() {
        Location location = null;
        try {
            String locationInfo = sharedPreferencesHelper.getString(Constants_SharedPreferences.LOCATION);
            if (locationInfo != null && !TextUtils.isEmpty(locationInfo)) {
                location = GsonUtils.fromJson(locationInfo, Location.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public void saveLocation(Location location) {
        String locationInfo = null;
        try {
            locationInfo = GsonUtils.toJson(location);
            if (locationInfo != null && !TextUtils.isEmpty(locationInfo)) {
                sharedPreferencesHelper.setString(Constants_SharedPreferences.LOCATION, locationInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Observable<ApiResult> uploadLocation(Location location) {
        return mapApi.uploadLocation(sharedPreferencesHelper.getString(Constants_SharedPreferences.USER_ID),location);
    }
}
