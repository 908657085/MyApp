package com.gaoxh.data.net.api;



import com.gaoxh.data.entity.llpay.BankCard;
import com.gaoxh.data.entity.llpay.CardBin;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 高雄辉 on 2016/5/9.
 *
 * 支付后台API请求接口
 */
public interface PayApi {


    @GET("prepositPay/queryCardBin")
    Observable<CardBin> queryCardBin(@Query("type") String type, @Query("card_no") String cardNo);

    @GET("prepositPay/queryBankcardList")
    Observable<List<BankCard>> queryBankcardList(@Query("type") String type);


}
