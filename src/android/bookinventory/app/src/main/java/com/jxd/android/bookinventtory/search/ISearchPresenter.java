package com.jxd.android.bookinventtory.search;

import com.jxd.android.bookinventtory.mvp.IPresenter;
import com.jxd.android.bookinventtory.mvp.PreActivity;

import java.util.List;

/**
 * Created by jinxiangdong on 2017/9/28.
 */
public interface ISearchPresenter extends IPresenter{

    List<String> getSearchKeysList();

}
