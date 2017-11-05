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

    private String title;
    private String barcode;
    private String shelfno;
    private String updatetime;
    private String uid;
    private String callno;
    private int inshelf;
    private String status;
    private String machine_mac;

    /**
     * 盘点状态
     */
    private String scanStatus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
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

    public String getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(String scanStatus) {
        this.scanStatus = scanStatus;
    }

    public void transfor(BookBean bookBean){
        this.setBarcode( bookBean.getBarcode() );
        this.setCallno(bookBean.getCallno());
        this.setInshelf(bookBean.getInshelf());
        this.setMachine_mac(bookBean.getMachine_mac());
        this.setTitle(bookBean.getTitle());
        this.setUid(bookBean.getUid());
        this.setShelfno(bookBean.getShelfno());
        this.setStatus( bookBean.getStatus() );
        this.setUpdatetime(bookBean.getUpdatetime());
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
