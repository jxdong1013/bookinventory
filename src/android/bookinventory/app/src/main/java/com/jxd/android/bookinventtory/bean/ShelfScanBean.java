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
    private String shelfno;
    private int userId;
    private String userName;
    private String description;
    private Date scanDatetime;
    private RealmList<ShelfBookScanBean> books;
    private int updateStatus;
    /**
     * 上传失败原因
     */
    private String uploadFailReason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShelfno() {
        return shelfno;
    }

    public void setShelfno(String shelfno) {
        this.shelfno = shelfno;
    }

    public RealmList<ShelfBookScanBean> getBooks() {
        return books;
    }

    public void setBooks(RealmList<ShelfBookScanBean> books) {
        this.books = books;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public int getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(int updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getUploadFailReason() {
        return uploadFailReason;
    }

    public void setUploadFailReason(String uploadFailReason) {
        this.uploadFailReason = uploadFailReason;
    }
}
