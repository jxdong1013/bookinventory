package com.jxd.android.bookinventtory.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ShelfAdaptAdapter extends BaseQuickAdapter<BookShelfAdptBean,BaseViewHolder> {

    public ShelfAdaptAdapter(@Nullable List<BookShelfAdptBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookShelfAdptBean item) {

    }
}
