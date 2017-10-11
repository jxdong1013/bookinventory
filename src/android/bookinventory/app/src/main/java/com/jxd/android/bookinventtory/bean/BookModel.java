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
}
