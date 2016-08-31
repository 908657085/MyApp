package com.gaoxh.data.net;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by 高雄辉 on 2016/5/24.
 * 网络请求默认处理
 */
public abstract class ApiSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        if(e.getClass().isInstance(HttpException.class)){
            HttpException httpException= (HttpException) e;
            parseErrorCode(httpException.code(),httpException.message(),e);
        }

    }

    /**
     * 处理网络请求错误返回
     * @param code
     */
    public abstract void parseErrorCode(int code, String msg,Throwable e);


}
