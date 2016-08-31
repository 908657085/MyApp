package com.gaoxh.data.entity.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高雄辉 on 2016/5/25 16:14
 */
public class TokenModel {
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
