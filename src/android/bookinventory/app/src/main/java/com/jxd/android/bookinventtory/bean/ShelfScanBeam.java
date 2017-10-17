package com.jxd.android.bookinventtory.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/17.
 */

public class ShelfScanBeam {
    private String shelfCode;
    private String shelfName;
    private List<ShelfBookScanBean> books;


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


    public List<ShelfBookScanBean> getBooks() {
        return books;
    }

    public void setBooks(List<ShelfBookScanBean> books) {
        this.books = books;
    }
}
