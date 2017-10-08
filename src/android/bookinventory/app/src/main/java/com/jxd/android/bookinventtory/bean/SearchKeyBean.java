package com.jxd.android.bookinventtory.bean;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 搜索关键字 表
 * Created by Administrator on 2017/9/29.
 */
public class SearchKeyBean  extends RealmObject implements Serializable{
    @PrimaryKey
    private String key;
    /**
     * 热度
     */
    private int hot;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }
}
