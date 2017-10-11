package com.jxd.android.bookinventtory.shelfsearch;

import com.jxd.android.bookinventtory.bean.ShelfCondition;
import com.jxd.android.bookinventtory.http.ApiService;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observer;

/**
 * Created by jinxiangdong on 2017/9/30.
 */

public class ShelfSearchModel implements IShelfSearchModel {
     ApiService apiService;
    LifecycleProvider lifecycleProvider;

    public ShelfSearchModel(ApiService apiService , LifecycleProvider lifecycleProvider) {
        this.apiService=apiService;
        this.lifecycleProvider=lifecycleProvider;
    }

    @Override
    public void getShelfList(ShelfCondition condition, Observer subscriber) {

    }

    @Override
    public void onDestory() {

    }
}
