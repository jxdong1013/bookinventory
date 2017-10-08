package com.jxd.android.bookinventtory.search;

import com.jxd.android.bookinventtory.bean.SearchKeyBean;
import com.jxd.android.bookinventtory.mvp.IPresenter;

/**
 * Created by jinxiangdong on 2017/9/28.
 */
public interface ISearchPresenter extends IPresenter{

    void getSearchKeysListAsync(String key);

    /**
     * 添加关键字
     * @param key
     */
    void addSearchKey(SearchKeyBean key);

    void deleteSearchKey();

}
