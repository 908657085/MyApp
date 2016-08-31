package com.gaoxh.data.entity.llpay;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高雄辉 on 2016/4/21.
 *
 * 银行卡 卡bin
 */
public class CardBin {

    @SerializedName("bank_code")
    private String bankCode;
    @SerializedName("bank_name")
    private String bankName;
    @SerializedName("card_type")
    private String cardType;

    public CardBin() {
    }

    public CardBin(String bankCode, String bankName, String cardType) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.cardType = cardType;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "bankcode:"+bankCode+"bankname:"+bankName+"cardType:"+cardType;
    }
}
