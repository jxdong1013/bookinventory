package com.jxd.android.bookinventtory.bean;

/**
 * Created by jinxiangdong on 2017/10/8.
 */

public enum ResultCodeEnum {

    SUCCESS(1,"success"),
    ERROR(400,"ERROR");


    private int code;
    private String name;
    ResultCodeEnum(int code , String name){
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
