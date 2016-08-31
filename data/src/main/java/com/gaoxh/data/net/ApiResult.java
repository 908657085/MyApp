package com.gaoxh.data.net;

import java.io.Serializable;

/**
 * Created by 高雄辉 on 2016/5/24 15:22
 * 服务器返回数据
 */
public class ApiResult<T> implements Serializable{

    private int code;
    private String msg;
    private boolean success;
    private T data;

    public ApiResult() {
    }

    public ApiResult(int code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public ApiResult(int code, String msg, boolean success, T data) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
