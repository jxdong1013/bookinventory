package com.jxd.android.bookinventtory.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */
public class UpdateInventory {
    private String shelfno;
    private List<UpdateInventoryBook> books;


    public String getShelfno() {
        return shelfno;
    }

    public void setShelfno(String shelfno) {
        this.shelfno = shelfno;
    }

    public List<UpdateInventoryBook> getBooks() {
        return books;
    }

    public void setBooks(List<UpdateInventoryBook> books) {
        this.books = books;
    }

}
