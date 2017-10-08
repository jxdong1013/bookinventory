package com.jxd.android.bookinventtory.bean;

/**
 * Created by jinxiangdong on 2017/9/30.
 */

public enum OperateTypeEnum {

    None(0,"none"),
    Insert(1,"insert"),
    Update(2,"update"),
    Delete(3,"delete"),
    Query(4,"query");

    private int code;
    private String name;
    OperateTypeEnum(int code , String name){
        this.code = code;
        this.name = name;
    }
}
