package com.jxd.android.bookinventtory.search;

import com.jxd.android.bookinventtory.bean.SearchKeyBean;
import com.jxd.android.bookinventtory.mvp.IPresenter;
import com.jxd.android.bookinventtory.mvp.PreActivity;

import java.util.List;

/**
 * Created by jinxiangdong on 2017/9/28.
 */
public interface ISearchPresenter extends IPresenter{

    void getSearchKeysListAsync(String key);

    void addSearchKey(SearchKeyBean key);

    void deleteSearkey();

}
