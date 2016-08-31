package com.gaoxh.data.net;

import com.gaoxh.data.cache.SharedPreferencesHelper;
import com.gaoxh.data.contstants.Constants_Api;
import com.gaoxh.data.contstants.Constants_SharedPreferences;
import com.gaoxh.data.entity.user.TokenModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by 高雄辉 on 2016/5/26 10:56
 * API请求添加TOKEN以及TOKEN失效刷新
 */
public class ApiTokenIntercepter implements Interceptor {
    public SharedPreferencesHelper sharedPreferencesHelper;
    public static Gson gson=new Gson();
    /**
     * token失效重新请求次数
     */
    private  int  reGetToken=0;

    @Inject
    public ApiTokenIntercepter(SharedPreferencesHelper sharedPreferencesHelper) {
        this.sharedPreferencesHelper=sharedPreferencesHelper;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        String url=request.url().toString();
        Response response;
        if(sharedPreferencesHelper.readToken()!=null){
            response=chain.proceed(createTokenRequest(request,sharedPreferencesHelper.readToken()));
        }else{
            response=chain.proceed(request);
        }
        if(response.code()==604) {
            if (reGetToken == 0){
                if( sharedPreferencesHelper.getString(Constants_SharedPreferences.USER_NAME) != null && sharedPreferencesHelper.getString(Constants_SharedPreferences.USER_PWD) != null){
                Response tokenResponse = chain.proceed(createLoginRequest());
                String result = tokenResponse.body().string();
                    tokenResponse.body().close();
                ApiResult apiResult = gson.fromJson(result, ApiResult.class);
                if (apiResult != null && apiResult.isSuccess()) {
                    String token = apiResult.getData().toString();
                    sharedPreferencesHelper.addToken(token);
                    response = chain.proceed(createTokenRequest(request, token));
                    if(response.code()!=200){
                        reGetToken=1;
                    }
                }else{
                    reGetToken = 1;
                }

            }

            }
        }
        return response;
    }

    private Request createTokenRequest(Request request,String token){
        Request tokenRequest= request.newBuilder()
                .header(Constants_SharedPreferences.TOKEN_NAME,token)
                .method(request.method(),request.body())
                .build();
        return tokenRequest;
    }

    private Request createLoginRequest(){
        RequestBody requestBody= new FormBody.Builder().addEncoded("userName",sharedPreferencesHelper.getString(Constants_SharedPreferences.USER_NAME)).addEncoded("pwd",sharedPreferencesHelper.getString(Constants_SharedPreferences.USER_PWD)).build();
        Request request=new Request.Builder().url(Constants_Api.LOGIN_URL).post(requestBody).build();
        return request;
    }


}
