package com.jxd.android.bookinventtory.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */

public class ShelfArrageAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static int ITEM_TYPE_SHELF=1;
    public static int ITEM_TYPE_BOOK=2;

    public ShelfArrageAdapter(List<MultiItemEntity> data) {
        super(data);

        this.addItemType(ITEM_TYPE_SHELF , );
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {

    }
}
