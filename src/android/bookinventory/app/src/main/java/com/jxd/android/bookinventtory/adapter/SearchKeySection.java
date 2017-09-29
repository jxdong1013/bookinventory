package com.jxd.android.bookinventtory.adapter;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.jxd.android.bookinventtory.bean.SearchKeyBean;

/**
 * Created by Administrator on 2017/9/29.
 */

public class SearchKeySection extends SectionEntity<SearchKeyBean> {
    public SearchKeySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SearchKeySection(SearchKeyBean s) {
        super(s);
    }
}
