package com.jxd.android.bookinventtory.shelfsearch;

import com.jxd.android.bookinventtory.bean.ShelfCondition;
import com.jxd.android.bookinventtory.mvp.IPresenter;

/**
 * Created by jinxiangdong on 2017/9/30.
 */

public interface IShelfSearchPresenter extends IPresenter{

    void getShelfList(ShelfCondition condition);

}
