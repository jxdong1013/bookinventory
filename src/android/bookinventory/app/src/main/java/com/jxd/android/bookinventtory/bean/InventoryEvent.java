package com.jxd.android.bookinventtory.bean;

import com.rfid.api.ISO15693Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinxiangdong on 2017/10/22.
 */

public class InventoryEvent {
    private List<ISO15693Tag> tags;
    private int code = 0;
    private int errorCode=0;

    public InventoryEvent(List<ISO15693Tag> tags , int code ){
        this.tags= tags;
        this.code= code;
    }
    public InventoryEvent(int code, int errorCode ){
        this.code= code;
        this.errorCode=errorCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ISO15693Tag> getTags() {
        return tags;
    }

    public void setTags(List<ISO15693Tag> tags) {
        this.tags = tags;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
