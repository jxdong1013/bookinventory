package com.jxd.android.bookinventtory.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.bean.BookShelfAdptBean;
import com.jxd.android.bookinventtory.utils.DateUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ShelfAdaptAdapter extends BaseQuickAdapter<BookShelfAdptBean,BaseViewHolder> {

    public ShelfAdaptAdapter(@Nullable List<BookShelfAdptBean> data) {
        super( R.layout.layout_shelfadapt_item ,data );
    }

    @Override
    protected void convert(BaseViewHolder helper, BookShelfAdptBean item) {
        if( !item.isValid() ) return;
        helper.setText( R.id.shelfadapte_barcode , item.getBarcode() );
        helper.setText(R.id.shelfadapte_shelfno , item.getShelfno());
        helper.setText( R.id.shelfadapte_adapttime , DateUtils.formatDate( item.getAdaptTime().getTime() , DateUtils.TIME_FORMAT ));

        helper.addOnClickListener(R.id.shelfadapte_delete);
    }
}
