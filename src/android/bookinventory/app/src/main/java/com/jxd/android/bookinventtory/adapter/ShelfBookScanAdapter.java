package com.jxd.android.bookinventtory.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.bean.BookBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ShelfBookScanAdapter extends BaseQuickAdapter<BookBean , BaseViewHolder> {

    public ShelfBookScanAdapter(@Nullable List<BookBean> data) {
        super(R.layout.layout_shelfarragescan_item , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookBean item) {
        helper.setText(R.id.shelfarragescan_item_bookcode , item.getBookcode());
        helper.setText(R.id.shelfarragescan_item_bookname , item.getBookName());
        helper.setText(R.id.shelfarragescan_item_status , item.getStatus());
    }
}
