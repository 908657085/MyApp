package com.gaoxh.data.entity.llpay;

/**
 * Created by 高雄辉 on 2016/4/21.
 *
 * 用户已绑定银行卡信息
 */
public class BankCard {

    private String bankCode;
    private String bankName;
    private String cardNo;
    private String cardType;
    private String noAgree;

    public BankCard() {
    }

    public BankCard(String bankCode, String bankName, String cardNo, String cardType, String noAgree) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.cardNo = cardNo;
        this.cardType = cardType;
        this.noAgree = noAgree;
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getNoAgree() {
        return noAgree;
    }

    public void setNoAgree(String noAgree) {
        this.noAgree = noAgree;
    }
}
