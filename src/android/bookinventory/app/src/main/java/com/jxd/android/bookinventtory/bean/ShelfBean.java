package com.jxd.android.bookinventtory.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * 架位信息
 * Created by jinxiangdong on 2017/9/30.
 */
//// TODO: 2017/9/30
public class ShelfBean  {

    private String shelfCode;
    private String shelfName;
    private List<BookBean> books;


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

    public List<BookBean> getBooks() {
        return books;
    }

    public void setBooks(List<BookBean> books) {
        this.books = books;
    }
}
