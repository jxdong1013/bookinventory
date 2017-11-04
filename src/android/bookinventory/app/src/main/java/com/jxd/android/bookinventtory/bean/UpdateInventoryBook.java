package com.jxd.android.bookinventtory.bean;

/**
 * Created by Administrator on 2017/11/4.
 */

public class UpdateInventoryBook {
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
