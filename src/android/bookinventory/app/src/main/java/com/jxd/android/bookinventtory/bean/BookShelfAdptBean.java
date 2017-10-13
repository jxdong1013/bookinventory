package com.jxd.android.bookinventtory.bean;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 图书架位调整bean
 * Created by Administrator on 2017/10/12.
 */
public class BookShelfAdptBean extends RealmObject{
    @PrimaryKey
    private String id;
    /***
     * 图书编号
     */
    private String bookCode;
    /**
     * 图书名称
     */
    private String bookName;
    /**
     * 架编号
     */
    private String shelfCode;
    /**
     * 架名称
     */
    private String shelfName;
    /**
     * 调整时间
     */
    private Date adaptTime;
    /**
     * 操作人
     */
    private String userName;
    /**
     *操作人
     */
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public Date getAdaptTime() {
        return adaptTime;
    }

    public void setAdaptTime(Date adaptTime) {
        this.adaptTime = adaptTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
