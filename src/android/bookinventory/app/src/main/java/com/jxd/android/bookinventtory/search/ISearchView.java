package com.jxd.android.bookinventtory.search;

import com.jxd.android.bookinventtory.R;
import com.jxd.android.bookinventtory.bean.SearchKeyBean;
import com.jxd.android.bookinventtory.mvp.IView;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by jinxiangdong on 2017/9/28.
 */

public interface ISearchView extends IView , RealmChangeListener<RealmResults<SearchKeyBean>>{

    void onRefresh();

    void onClear();

}
