package com.jxd.android.bookinventtory.bean;

/**
 * Created by Administrator on 2017/11/2.
 */

public enum UpdateStatusEnum {
    NoUpdate(0,"未上传"),
    UpdateSuccess(1,"上传成功"),
    UpdateFail(2,"上传失败");

    private int code;
    private String name;
    private UpdateStatusEnum(int code , String name){
        this.code=code;
        this.name=name;
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
