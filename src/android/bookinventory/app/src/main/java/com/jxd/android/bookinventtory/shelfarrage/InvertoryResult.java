package com.jxd.android.bookinventtory.shelfarrage;

import com.jxd.android.bookinventtory.bean.UpdateInventory;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4.
 */

public class InvertoryResult {
    private int index;
    private List<UpdateInventory> data;

    public InvertoryResult(int index , List<UpdateInventory> data){
        this.index= index;
        this.data=data;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<UpdateInventory> getData() {
        return data;
    }

    public void setData(List<UpdateInventory> data) {
        this.data = data;
    }
}
