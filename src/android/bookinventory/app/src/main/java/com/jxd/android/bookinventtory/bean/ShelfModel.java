package com.jxd.android.bookinventtory.bean;

import android.system.StructStat;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jxd.android.bookinventtory.adapter.ShelfSearchAdapter;

import java.security.PublicKey;

/**
 * Created by Administrator on 2017/10/11.
 */

public class ShelfModel extends ShelfBean implements MultiItemEntity  {

    @Override
    public int getItemType() {
        return ShelfSearchAdapter.ITEM_TYPE_SHELF;
    }


    public void transfor(ShelfBean shelfBean){
        //this.setShelfName( shelfBean.getShelfName() );
        this.setShelfno(shelfBean.getShelfno());
        this.setBooks( shelfBean.getBooks() );
    }

}
