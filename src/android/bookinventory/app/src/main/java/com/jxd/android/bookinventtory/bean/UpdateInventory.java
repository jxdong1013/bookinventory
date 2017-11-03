package com.jxd.android.bookinventtory.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 */
public class UpdateInventory {
    private String shelfno;
    private List<UpdateBook> books;
    private String userId;
    private String userName;

    public String getShelfno() {
        return shelfno;
    }

    public void setShelfno(String shelfno) {
        this.shelfno = shelfno;
    }

    public List<UpdateBook> getBooks() {
        return books;
    }

    public void setBooks(List<UpdateBook> books) {
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

    public class UpdateBook{
        private String  barcode;
        private String scanStatus;

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getScanStatus() {
            return scanStatus;
        }

        public void setScanStatus(String scanStatus) {
            this.scanStatus = scanStatus;
        }
    }

}
