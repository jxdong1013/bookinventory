package com.jxd.android.bookinventtory.bean;

/**
 * Created by Administrator on 2017/10/10.
 */

public class BookCondition {
    private int pageIdx;
    private String title;
    //private String shelfno;
    private String barcode;

    public int getPageIdx() {
        return pageIdx;
    }

    public void setPageIdx(int pageIdx) {
        this.pageIdx = pageIdx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getShelfno() {
//        return shelfno;
//    }
//
//    public void setShelfno(String shelfno) {
//        this.shelfno = shelfno;
//    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
