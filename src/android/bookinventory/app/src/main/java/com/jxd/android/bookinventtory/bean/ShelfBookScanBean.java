package com.jxd.android.bookinventtory.bean;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * 图书盘点
 * Created by Administrator on 2017/10/17.
 */
@RealmClass
public class ShelfBookScanBean implements RealmModel {

    private String bookName;
    private String author;
    private String publish;
    private String publishDate;
    private String bookId;
    private String bookcode;
    private String position;
    private String status;

    /**
     * 盘点状态
     */
    private String scanStatus;


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookcode() {
        return bookcode;
    }

    public void setBookcode(String bookcode) {
        this.bookcode = bookcode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
