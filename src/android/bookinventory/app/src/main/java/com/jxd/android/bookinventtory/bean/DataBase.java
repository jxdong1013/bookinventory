package com.jxd.android.bookinventtory.bean;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/9/29.
 */

public class DataBase<T> {


    private String message;
    private int code;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
