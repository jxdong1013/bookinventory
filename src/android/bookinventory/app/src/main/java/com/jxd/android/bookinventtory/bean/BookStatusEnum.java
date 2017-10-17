package com.jxd.android.bookinventtory.bean;

/**
 * Created by Administrator on 2017/10/17.
 */

public enum BookStatusEnum {
    IN("in","in"),
    OUT("out","out"),
    NEW("new","new");

    private String code;
    private String name;
    BookStatusEnum(String code , String name){
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
