package com.jxd.android.bookinventtory.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.bean.SearchKeyBean;

import java.util.List;

import static android.R.attr.data;

/**
 * Created by Administrator on 2017/9/29.
 */

public class SearchKeyAdapter extends BaseSectionQuickAdapter<SearchKeySection , BaseViewHolder> {

    public SearchKeyAdapter( List data) {
        this( R.layout.layout_search_item , R.layout.layout_search_section , data);
    }

    public SearchKeyAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId  , data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SearchKeySection item) {
        helper.setText(R.id.search_item_section , item.header);

        helper.addOnClickListener(R.id.ivDelete);


    }

    @Override
    protected void convert(BaseViewHolder helper, SearchKeySection item) {
        helper.setText(R.id.search_item_key , item.t.getKey() );

        helper.addOnClickListener(R.id.search_item_key);


    }



}
