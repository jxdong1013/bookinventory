package com.jxd.android.bookinventtory.bean;

import io.realm.RealmObject;

/**
 * 图书信息
 * Created by jinxiangdong on 2017/9/30.
 */
//// TODO: 2017/9/30
public class BookBean extends RealmObject{
    private String bookName;
    private String author;
    private String publish;
    private String publishDate;
    private String bookId;
    private String bookcode;
    private String position;

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
}
