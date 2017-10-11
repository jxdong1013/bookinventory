package com.jxd.android.bookinventtory.shelfsearch;

import com.jxd.android.bookinventtory.bean.ShelfCondition;
import com.jxd.android.bookinventtory.mvp.IModel;

import io.reactivex.Observer;

/**
 * Created by jinxiangdong on 2017/9/30.
 */

public interface IShelfSearchModel extends IModel {

    void getShelfList(ShelfCondition condition , Observer subscriber) ;


}
