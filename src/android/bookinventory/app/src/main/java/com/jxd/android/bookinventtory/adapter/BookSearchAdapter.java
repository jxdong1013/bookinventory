package com.jxd.android.bookinventtory.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.bean.BookBean;

import java.util.List;

/**
 * Created by jinxiangdong on 2017/9/30.
 */
public class BookSearchAdapter extends BaseQuickAdapter<BookBean, BaseViewHolder> {
    public BookSearchAdapter(@Nullable List<BookBean> data) {
        super(R.layout.layout_book_item , data );
    }

    @Override
    protected void convert(BaseViewHolder helper, BookBean item) {
        helper.setText(R.id.bookitem_barcode , "条码:"+ item.getBarcode());
        helper.setText(R.id.bookitem_title, "名称:"+item.getTitle());
        //helper.setText(R.id.bookitem_status, "状态:" + item.getStatus());
        helper.setText(R.id.bookitem_shelfno , "架位:"+ item.getShelfno());
        helper.setText(R.id.bookitem_updatetime , "更新时间:"+ item.getUpdatetime());
        //helper.setText(R.id.bookitem_status , item.getStatus());
    }
}
