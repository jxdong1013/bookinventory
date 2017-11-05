package com.jxd.android.bookinventtory.bean;


/**
 * 图书信息
 * Created by jinxiangdong on 2017/9/30.
 */
public class BookBean {
    private String title;
    private String callno;
    private String shelfno;
    private String updatetime;
    private String uid;
    private String barcode;
    private int inshelf;
    private String status;
    private String machine_mac;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
    }

    public String getShelfno() {
        return shelfno;
    }

    public void setShelfno(String shelfno) {
        this.shelfno = shelfno;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getInshelf() {
        return inshelf;
    }

    public void setInshelf(int inshelf) {
        this.inshelf = inshelf;
    }

    public String getMachine_mac() {
        return machine_mac;
    }

    public void setMachine_mac(String machine_mac) {
        this.machine_mac = machine_mac;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
