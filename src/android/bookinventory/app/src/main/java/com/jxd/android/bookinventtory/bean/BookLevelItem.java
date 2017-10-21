package com.jxd.android.bookinventtory.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jxd.android.bookinventtory.adapter.ShelfArrageAdapter;

/**
 * Created by jinxiangdong on 2017/10/21.
 */

public class BookLevelItem  implements MultiItemEntity {
    private ShelfBookScanBean book;

    public ShelfBookScanBean getBook() {
        return book;
    }

    public BookLevelItem(ShelfBookScanBean book) {
        this.book = book;
    }

    @Override
    public int getItemType() {
        return ShelfArrageAdapter.ITEM_TYPE_BOOK;
    }
}
