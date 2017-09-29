package com.jxd.android.bookinventtory.search;

import com.jxd.android.bookinventtory.mvp.PreActivity;

import java.util.List;

/**
 * Created by jinxiangdong on 2017/9/28.
 */
@PreActivity
public class SearchPresenter<V extends ISearchView>
        implements ISearchPresenter {

    @Override
    public List<String> getSearchKeysList() {
        return null;
    }

    @Override
    public void onDestory() {

    }
}
