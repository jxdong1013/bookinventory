package com.jxd.android.bookinventtory.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jxd.android.bookinventtory.adapter.ShelfArrageAdapter;

/**
 * Created by jinxiangdong on 2017/10/21.
 */

public class ShelfLevelItem extends AbstractExpandableItem<BookLevelItem> implements MultiItemEntity{

    private ShelfScanBean shelf;
    /**
     * 标记是否勾选
     */
    private boolean isChecked;


    public ShelfScanBean getShelf() {
        return shelf;
    }

    public ShelfLevelItem(ShelfScanBean shelf) {
        this.shelf = shelf;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return ShelfArrageAdapter.ITEM_TYPE_SHELF;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
