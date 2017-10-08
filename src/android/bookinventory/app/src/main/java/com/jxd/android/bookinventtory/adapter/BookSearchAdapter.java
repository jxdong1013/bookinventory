package com.jxd.android.bookinventtory.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxd.android.bookinventtory.bean.BookBean;

import java.util.List;

/**
 * Created by jinxiangdong on 2017/9/30.
 */

public class BookSearchAdapter extends BaseQuickAdapter<BookBean, BaseViewHolder> {
    public BookSearchAdapter(@Nullable List<BookBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookBean item) {

    }
}
