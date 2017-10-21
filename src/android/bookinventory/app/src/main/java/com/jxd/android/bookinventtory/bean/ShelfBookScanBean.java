package com.jxd.android.bookinventtory.bean;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * 图书盘点
 * Created by Administrator on 2017/10/17.
 */
public class ShelfBookScanBean extends BookBean {
    /**
     * 盘点状态
     */
    private String scanStatus;

    public String getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(String scanStatus) {
        this.scanStatus = scanStatus;
    }


    public void transfor(BookBean bookBean){
        this.setPublishDate( bookBean.getPublishDate() );
        this.setPublish(bookBean.getPublish());
        this.setBookName(bookBean.getBookName());
        this.setBookId(bookBean.getBookId());
        this.setAuthor(bookBean.getAuthor());
        this.setBookcode(bookBean.getBookcode());
        this.setPosition(bookBean.getPosition());
        this.setStatus( bookBean.getStatus() );
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
