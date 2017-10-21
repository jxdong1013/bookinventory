package com.jxd.android.bookinventtory.bean;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Administrator on 2017/10/17.
 */
@RealmClass
public class ShelfScanBean implements RealmModel{
    @PrimaryKey
    private String id;
    private String shelfCode;
    private String shelfName;
    private String userId;
    private String userName;
    private String description;
    private Date scanDatetime;
    private RealmList<ShelfBookScanBean> books;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShelfCode() {
        return shelfCode;
    }


    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }


    public String getShelfName() {
        return shelfName;
    }


    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }


    public RealmList<ShelfBookScanBean> getBooks() {
        return books;
    }

    public void setBooks(RealmList<ShelfBookScanBean> books) {
        this.books = books;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getScanDatetime() {
        return scanDatetime;
    }

    public void setScanDatetime(Date scanDatetime) {
        this.scanDatetime = scanDatetime;
    }
}
