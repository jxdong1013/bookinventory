package com.jxd.android.bookinventtory.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jxd.android.bookinventtory.adapter.ShelfSearchAdapter;

/**
 * Created by Administrator on 2017/10/11.
 */

public class BookModel extends BookBean implements MultiItemEntity {
    @Override
    public int getItemType() {
        return ShelfSearchAdapter.ITEM_TYPE_BOOK;
    }

    public void transfor(BookBean bookBean){
        this.setUpdatetime( bookBean.getUpdatetime() );
        this.setShelfno(bookBean.getShelfno());
        this.setUid(bookBean.getUid());
        this.setTitle(bookBean.getTitle());
        this.setBarcode(bookBean.getBarcode());
        this.setCallno(bookBean.getCallno());
        this.setInshelf(bookBean.getInshelf());
        this.setStatus(bookBean.getStatus());
        this.setMachine_mac(bookBean.getMachine_mac());
    }
}
